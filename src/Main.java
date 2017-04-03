// Defines imports.
import entities.arraycontainers.Floor;
import entities.Level;
import entities.Warehouse;
import entitycommands.EntityCommand;
import entitycommands.OrderCommand;
import entitycommands.workercommands.LoaderCommand;
import entitycommands.workercommands.PickerCommand;
import entitycommands.workercommands.ReplenisherCommand;
import entitycommands.workercommands.SequencerCommand;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.StringIndexOutOfBoundsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The main running class.
 */
public class Main {
    // Defines instance variables.
    private static final String QUIT_COMMAND = "Quit";
    private static final String SAVE_FILE = "./resources/final.csv";
    private static final String INITIAL_FILE = "./resources/initial.csv";
    private static final String ORDER_FILE = "./resources/16orders.txt";

    /**
     * The main method, if the project is finished, this will have the text
     * input, and the actions taken upon input. It should loop until the quit
     * command is given, defined by the instance variable, otherwise it should
     * execute the given command in the console.
     *
     * @param args Unused parameter required in main.
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        Warehouse warehouse = new Warehouse();
        EntityCommand[] commands = new EntityCommand[]{
                                           new OrderCommand(warehouse.getFaxMachine()),
                                           new PickerCommand(warehouse), 
                                           new LoaderCommand(warehouse),
                                           new SequencerCommand(warehouse),
                                           new ReplenisherCommand(warehouse)};
        loadInitialState(warehouse.getFloor());

        Level[] levels = warehouse.getFloor().getLevels();
        // Checks low levels.
        for(int index = 0; index < levels.length; index++) {
            warehouse.getServer().checkLevel(levels[index]);
        }

       if (args.length == 1 ) {
            try {
                System.out.println("Started execution");
                String line;
                FileReader fr = new FileReader(ORDER_FILE);
                BufferedReader br = new BufferedReader(fr);
                boolean commandFound = false;

                while ((line = br.readLine()) != null) {
                    for (int index = 0; (!(commandFound) && index < commands.length); index++) {
                        System.out.println("    Currently checking commands[" + Integer.toString(index) + "].");
                        String commandString = commands[index].getCommand();

                        System.out.println("    Checking if " + line + " starts with " + commandString);
                        // If the command is valid, execute the command.
                        if (line.startsWith(commandString)) {
                            commandFound = true;
                            String argument = line.substring(commandString.length() + 1);
                            commands[index].executeCommand(argument);
                        } else {
                            index++;
                        }
                    }

                    commandFound = false;
                }

                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to open file '" + ORDER_FILE + "'");
            } catch (IOException ex) {
                System.out.println("Error reading file '" + ORDER_FILE + "'");
                ex.printStackTrace();
            }
            try {
                saveFinalState(warehouse.getFloor());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (args.length == 0 ) {
            String currentInput = "";
            Scanner inputScanner = new Scanner(System.in);
            while (!(currentInput.equals(QUIT_COMMAND))) {
                // Gets user input.
                System.out.print("Input: ");
                currentInput = inputScanner.nextLine();

                // Sets up for checking commands.
                boolean commandFound = false;

                // Keep checking until there is a valid command or all commands have been checked.
                for (int index = 0; (!(commandFound) && index < commands.length); index++) {
                    // If the command is valid, execute the command.
                    if (currentInput.startsWith(commands[index].getCommand())) {
                        commandFound = true;
                        try {
                            commands[index].executeCommand(currentInput.substring(commands[index].getCommand().length() + 1));
                        } catch(StringIndexOutOfBoundsException exception) {
                            System.err.println("    Error: An argument should follow the command \"" + commands[index].getCommand() + "\".");
                        } catch(IllegalArgumentException|IllegalStateException exception) {
                            System.err.println("    Error: " + exception.getMessage());
                        }
                    }
                }
            }

            inputScanner.close();

            try {
                saveFinalState(warehouse.getFloor());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            IOException e = new IOException();
            throw e;
        }
    }

    /**
     * The method for saving final.csv, . . . . .
     */
    private static void saveFinalState(Floor floor) throws IOException {
        createFile();
        System.out.println("The current state of warehouse has been saved");
        Level[] levels = floor.getLevels();
        // goes through each possible location in the warehouse
        for (int i = 0; i < levels.length; i++) {
            if (!levels[i].atMaxCapacity()) {
                // writes to the file
                writeFile(levels[i].getLocation() + "," + levels[i].getStock());
            }
        }
    }

    private static void writeFile(String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SAVE_FILE, true))) {
            bw.write(content);
            bw.newLine();
            System.out.println("Wrote current state to to final.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] checkForDup() {
        String line;
        ArrayList<String> initFile = new ArrayList<String>();
        boolean bool = true;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(INITIAL_FILE);

            // Always wrap FileReader in BufferedReader.
            BufferedReader br = new BufferedReader(fileReader);
            // Appends the line of location into an Arraylist.
            while ((line = br.readLine()) != null) {
                initFile.add(line);
            }
            // closes the file.
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + INITIAL_FILE + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + INITIAL_FILE + "'");
            ex.printStackTrace();
        }
        // converts the Arraylist to an array to provide efficiency.
        String[] sortedInitFile = initFile.toArray(new String[initFile.size()]);
        // sorts the array for maximum search efficiency
        Arrays.sort(sortedInitFile);
        for (int i = 0; i < sortedInitFile.length - 1; i++) {
            // compares the i'th index location to the i+1'th index location
            // to check for duplicates since they are sorted
            // if they are duplicates they must be adjacent to each other.
            if (sortedInitFile[i].equals(sortedInitFile[i + 1])) {
                System.out.println("There exists a duplicate in Floor location");
                // fails the dup test and should return null and notify the user
                // this initializing has failed.
                bool = false;
                break;
            }
        }
        // returns the array of locations if no duplicates
        if (bool == true) {
            return sortedInitFile;
            // returns null for now, should raise exception later.
        } else {
            return null;
        }
    }

    private static void loadInitialState(Floor floor) {
        System.out.println("Loading the initial state of the warehouse");
        // Parse initial.csv into an ArrayList, with each line being an element.
        // This will reference one line at a time
        String[] init = checkForDup();
        Level[] levels = floor.getLevels();
        // HINT: Change these to non-final variables if we do not pop them
        int pointerA = 0;
        int pointerB = 0;
        if (init != null) {
            // HINT: use floors and not floor.getLevels()
            // HINT: Change the whileloop condition relative to the pointers and
            // not the length of the arrays.
            while (init.length > pointerA || levels.length > pointerB) {
                // the case where both array are still active, we would
                // compare to two and decide how to fill it
                if (init.length > pointerA && levels.length > pointerB) {
                    String compare = init[pointerA].substring(0, 7);
                    // if The current pointed Floor location is
                    // lexicographically
                    // greater than the init location, we fill it to the MAX
                    if (compare.compareTo(levels[pointerB].getLocation()) > 0) {
                        levels[pointerB].addStock(levels[pointerB].getMaxCapacity());
                        pointerB += 1;
                        // if the current location is equal to the init location
                        // lexicographically, we add the appropriate amount to
                        // it
                        // which should be stored at location.substring(8,9)
                    } else if (compare.compareTo(levels[pointerB].getLocation()) == 0) {
                        String[] location = init[pointerA].split(",");
                        levels[pointerB].addStock(Integer.parseInt(location[4]));
                        pointerA += 1;
                        pointerB += 1;
                        // if current pointed Floor location is
                        // lexicographically
                        // less than the init location, it shouldn't happen
                        // So we give an Error message and essentially quit.
                    } else {
                        System.out.println("ERROR: This Poisition does not exist on the floor");
                        System.out.println("Please check the files for duplicates or non-existant");
                        System.out.println("locations");
                        break;
                    }

                // if init.csv runs out of lines. We fill the rest to max
                // Stock
                } else if (init.length == pointerA && levels.length > pointerB) {
                    levels[pointerB].addStock(levels[pointerB].getMaxCapacity());
                    pointerB += 1;
                }
            }
        }
    }

    /**
     * The method for saving final.csv, . . . . .
     */
    private static void createFile(){
        PrintWriter writer;
        try {
            System.out.println("Creating final.csv");
            writer = new PrintWriter(SAVE_FILE,"UTF-8");
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("Failed to create final.txt");
            e.printStackTrace();
        }
    }
}