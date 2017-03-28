/**
 * The main running class.
 */
public class Main {
    // Defines instance variables.
    private static final String QUIT_COMMAND = "Quit";

    // Defines the main method.
    /**
     * The main method, if the project is finished, this will have the text input, and the actions
     * taken upon input. It should loop until the quit command is given, defined by the constant,
     * otherwise it should execute the given command if it is valid.
     *
     * @param args Unused parameter required in main.
     */
    public static void main(String[] args) {
        System.out.println("Calling main.");
        // This is already implemented, and will be included in a different pull request, do not
        // impelement this.
    }

    // Defines the helper methods.
    /**
     * The method for saving final.csv, which checks every level in a warehouse and writes the
     * levels into the function that do not include those at max capacity. The format will be as
     * follows: each level will be written once to final.csv, each level will exist on a new line,
     * in the format of "zone,aisle,rack,level,quantity", where "zone" is a character, and the
     * remaining variables are the respective integers.
     *
     * @param floor the Floor which should be utilized in the creation of final.csv.
     */
    private static void saveFinalState(Floor floor) {
        System.out.println("Calling saveFinalState of Main.");
        // Check every level, calling, floor.getItems() to get the floor.
        // Then get each level, by calling getLevel() on floor.
        // Then check if the Level is at max capacity, if it is
        // Then get each path to the level by calling, getLocation() on the level.
        // Then write the resulting new line to the file if the line exists.
    }

    /**
     * The method for loading from initial.csv, which takes every level from the file, and loads
     * it with the appropriate quantity of stock, according to the input of initial.csv, which
     * should be written in the following format: each level is written in once, and on a new line
     * in the format of "zone,aisle,rack,level,quantity", where "zone" is a character, and the
     * remaining variables are the respective integers.
     *
     * @param floor the Floor which should have each Level filled with the correct amount of
     *        Stock.
     */
    private static void loadInitialState(Floor floor) {
        System.out.println("Calling loadInitialState of Main.");
        // Parse initial.csv into an ArrayList, with each line being an element.
        // Ensure that there are no repeats of levels, that is that each line doesn't
        // start with the same String excluding the quantity, if it does, throw an exception.
        // If it doesn't then add it inot the parsed ArrayList.
        // Then take the parsed ArrayList and turn it into an array.
        // Then use sort from the Arrays class to turn it into a sorted array.
        // Then create an int nonMaxLevelIndex, and set it to zero.
        // Then for each Level of the floor, check if the path is the same as that given by
        // nonMaxLevelIndex and the sorted array, if it is, use that quantity and add one to
        // nonMaxLevelIndex, if it is not, then set the quantity to the max.
        // make sure the quantity given to the level is not larger than the max.
        // Go until all Level are filled.
    }
}