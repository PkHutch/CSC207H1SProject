
// Defines imports.
import entities.FaxMachine;
import entities.Level;
import entities.Server;
import entities.Warehouse;
import entities.arraycontainers.Floor;
import entitycommands.EntityCommand;
import entitycommands.OrderCommand;
import entitycommands.workercommands.PickerCommand;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main running class.
 */
public class Main {
	// Defines instance variables.
	private static final String QUIT_COMMAND = "Quit";
	private static final String SAVE_FILE = "./resources/final.csv";
	private static final String INITIAL_FILE = "./resources/traversal_table.csv";

	/**
	 * The main method, if the project is finished, this will have the text
	 * input, and the actions taken upon input. It should loop until the quit
	 * command is given, defined by the instance variable, otherwise it should
	 * execute the given command in the console.
	 *
	 * @param args
	 *            Unused parameter required in main.
	 */
	public static void main(String[] args) {
		Warehouse warehouse = new Warehouse();
		Server server = new Server(warehouse);
		FaxMachine faxmachine = new FaxMachine(server);
		warehouse.AddFaxMachine(faxmachine);
		EntityCommand[] commands = new EntityCommand[] { new OrderCommand(faxmachine), new PickerCommand(warehouse) };
		loadInitialState(warehouse.getFloor());
		// Defines the variables that need to be used for user input.
		String currentInput = "";
		Scanner inputScanner = new Scanner(System.in);
		// Keeps getting user input and inputting the commands until the quit
		// command is given.
		while (!(currentInput.equals(QUIT_COMMAND))) {
			// Gets user input.
			System.out.print("Input: ");
			currentInput = inputScanner.nextLine();

			// Sets up for checking commands.
			boolean commandFound = false;

			System.out.println("    Checking if the input is a valid command.");
			// Keep checking until there is a valid command or all commands have
			// been checked.
			for (int index = 0; (!(commandFound) && index < commands.length); index++) {
				System.out.println("    Currently checking commands[" + Integer.toString(index) + "].");
				String commandString = commands[index].getCommand();

				System.out.println("    Checking if " + currentInput + " starts with " + commandString);
				// If the command is valid, execute the command.
				if (currentInput.startsWith(commandString)) {
					try {
						saveFinalState(warehouse.getFloor());
					} catch (IOException e) {
						e.printStackTrace();
					}
					commandFound = true;
					String argument = currentInput.substring(commandString.length() + 1);
					commands[index].executeCommand(argument);
					try {
						saveFinalState(warehouse.getFloor());
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					index++;
				}
			}
		}
		inputScanner.close();

		// Would want to save.
		// saveFinalState(warehouse);
	}

	/**
	 * The method for saving final.csv, . . . . .
	 */
	private static void saveFinalState(Floor floor) throws IOException {
        	// Check every level, calling, floor.getItems() to get the floor.
		// Then get each level, by calling getLevel() on floor.
		// Then check if the Level is at max capacity, if it is
		// Then get each path to the level by calling, getLocation() on the
		// level.
		// Then write the resulting new line to the file if the line exists.
		System.out.println("The current state of warehouse has been saved");
		Level[] levels = floor.getLevels();
		//goes through each possible location in the warehouse
		for (int i = 0; i < levels.length; i++) {
			if (!levels[i].atMaxCapacity()) {
				//writes to the file
				writeFile(levels[i].getLocation());
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

	private static String[] checkForDup(){
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
			}catch (FileNotFoundException ex) {
				System.out.println("Unable to open file '" + INITIAL_FILE + "'");
			} catch (IOException ex) {
				System.out.println("Error reading file '" + INITIAL_FILE + "'");
				ex.printStackTrace();
			}
			//converts the Arraylist to an array to provide efficiency. 
			String[] sortedInitFile = initFile.toArray(new String[initFile.size()]);
			//sorts the array for maximum search efficiency
			Arrays.sort(sortedInitFile);
			for(int i=0;i<sortedInitFile.length-1;i++){
				//compares the i'th index location to the i+1'th index location
				//to check for duplicates since they are sorted
				//if they are duplicates they must be adjacent to each other.
				if(sortedInitFile[i] == sortedInitFile[i+1]){
					System.out.println("There exists a duplicate in Floor location");
					//fails the dup test and should return null and notify the user
					//this initializing has failed.
					bool = false;
				}
			}
			//returns the array of locations if no duplicates
			if (bool==true){
				return sortedInitFile;
			//returns null for now, should raise exception later.
			}else{
				return null;
			}

	}

	private static void loadInitialState(Floor floor) {
		System.out.println("Loading the initial state of the warehouse");
		// Parse initial.csv into an ArrayList, with each line being an element.
		// This will reference one line at a time
		String[] init = checkForDup();
		Level[] levels = floor.getLevels().clone();
		//HINT: Change these to non-final variables if we do not pop them
		int pointerA = 0;
		int pointerB = 0;
		if (init != null){
			//HINT: use floors and not floor.getLevels()
			//HINT: Change the whileloop condition relative to the pointers and not the length of the arrays.
			while (init.length != 0 || levels.length != 0){
				//the case where both array are still active, we would
				//compare to two and decide how to fill it
				if(init.length!=0 && levels.length!=0){
					//if The current pointed Floor location is lexicographically 
					//greater than the init location, we fill it to the MAX
					if(init[pointerA].compareTo(levels[pointerB].getLocation())<0){
						levels[pointerB].addStock(levels[pointerB].getMaxCapacity());
						pointerA += 1;
						
					//if the current location is equal to the init location
					//lexicographically, we add the appropriate amount to it
					//which should be stored at location.substring(8,9)
					}else if(init[pointerA].compareTo(levels[pointerB].getLocation())==0){
						String location = init[pointerA];
						levels[pointerB].addStock(Integer.parseInt(location.substring(8, 9)));
						pointerA += 1 ;
						pointerB += 1 ;
					
					 //if current pointed Floor location is lexicographically
					 //less than the init location, it shouldn't happen
					 //So we give an Error message and essentially quit.
					}else{
						System.out.println("ERROR: This Poisition does not exist on the floor");
						System.out.println("Please check the files for duplicates or non-existant");
						System.out.println("locations");
						//Raise an exception here
						break;
					}
				//if init.csv runs out of lines. We fill the rest to max Stock
				}else if(init.length==0 && levels.length!=0){
						levels[pointerB].addStock(levels[pointerB].getMaxCapacity());
						pointerB += 1;
				}
			}
		}
	}
}