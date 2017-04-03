// Defines the package.
package entities;

// Defines the input.
import java.util.ArrayList;
import entities.arraycontainers.Floor;
import entities.workers.Worker;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The warehouse class, which is where the simulation takes place, as the Warehouse effectively
 * acts as a container for the other entities.
 */
public class Warehouse {
    // Defines the instance variables.
    private final FaxMachine faxMachine;
    private final Floor floor;
    private final Marshalling marshalling;
    private final Server server;
    private final WarehousePicking warehousePicking;
    private final ArrayList<Worker> workers;


    // Defines the constructors.
    /**
     * The default constructor for a Warehouse.
     */
    public Warehouse() {
        System.out.println("Constructing Warehouse" + this.toString() + ".");
        this.floor = new Floor(this, parseTraversalTableFile());
        this.marshalling = new Marshalling();
        this.server = new Server(this);
        this.workers = new ArrayList<>();

        // These instance variables are assigned after, because they require the creation of the
        // instance variables prior to them.
	this.faxMachine = new FaxMachine(this.server);
        this.warehousePicking = new WarehousePicking(this.floor.getLevels());
    }

    // Defines the functional methods.
    /**
     * The getFaxMachine method returns the FaxMachine of the Warehouse.
     *
     * @return the FaxMachine of this Warehouse.
     */
    public FaxMachine getFaxMachine() {
        return this.faxMachine;
    }

    /**
     * The getWarehousePicking method of Warehouse returns the WarehousePicking class of this
     * Warehouse, which contains the optimize method.
     *
     * @return the WarehousePicking of this Warehouse.
     */
    public WarehousePicking getWarehousePicking() {
        return this.warehousePicking;
    }

	// Defines the helper methods.
	/**
	 * Parses the traversal_table.csv file so that the server has a more readily
	 * useable form for the sake of looking up SKU numbers of levels, this is
	 * more efficient than parsing the file every single time an SKU lookup is
	 * required.
	 *
	 * @return the Integer[][][][] which "models" the Warehouse in the sense
	 *         that it describes SKU of each Level. The first dimension is the
	 *         zones, then the aisles, then the racks, then the levels.
	 */
	private Integer[][][][] parseTraversalTableFile() {
		System.out.println("Calling parseTraversalTableFile of Warehouse " + this.toString() + ".");
		// Defines constants.
		final String SPLIT_BY = ",";
		final char STARTING_ZONE = 'A';
		final char STARTING_AISLE = '0';
		final char STARTING_RACK = '0';
		final char STARTING_LEVEL = '0';
		final String TRANSLATION_FILE = "./resources/traversal_table.csv";

		// Defines variables for reading the file.
		BufferedReader bufferedReader = null;
		String line;

		// Defines the ArrayList that will be converted to an array.
		ArrayList<Integer[][][]> parsedTraversalFile = new ArrayList<>();

		try {
			bufferedReader = new BufferedReader(new FileReader(TRANSLATION_FILE));
			char currentZone = STARTING_ZONE;
			char currentAisle = STARTING_AISLE;
			char currentRack = STARTING_RACK;
			char currentLevel = STARTING_LEVEL;
			ArrayList<Integer> parsedRack = new ArrayList<>();
			ArrayList<Integer[]> parsedAisle = new ArrayList<>();
			ArrayList<Integer[][]> parsedZone = new ArrayList<>();

			line = bufferedReader.readLine();
			String[] translatedLine = line.split(SPLIT_BY);
			char translatedZone = translatedLine[0].charAt(0);
			char translatedAisle = translatedLine[1].charAt(0);
			char translatedRack = translatedLine[2].charAt(0);
			char translatedLevel = translatedLine[3].charAt(0);

			if (currentZone == translatedZone && currentAisle == translatedAisle && currentRack == translatedRack
					&& currentLevel == translatedLevel) {
                                parsedRack.add(Integer.valueOf(translatedLine[4]));
			} else {
				throw new IllegalArgumentException("The first line is not of the form "
						+ "\"A,0,0,0,X\" where X is the SKU that the level contains.");
			}

			// Operating on all remaining lines.
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println("    Parsing line: " + line + ".");
				translatedLine = line.split(SPLIT_BY);
				translatedZone = translatedLine[0].charAt(0);
				translatedAisle = translatedLine[1].charAt(0);
				translatedRack = translatedLine[2].charAt(0);
				translatedLevel = translatedLine[3].charAt(0);

				if (currentZone == translatedZone) {
					System.out.println("    Zone is the same as current.");
					if (currentAisle == translatedAisle) {
						System.out.println("    Aisle is the same as current.");
						if (currentRack == translatedRack) {
							System.out.println("    Rack is the same as current.");
							System.out.println("    " + Character.toString(((char) ((int) currentLevel + 1))) + ".");
							if (translatedLevel == ((char) ((int) currentLevel + 1))) {
								System.out.println("    Level is not, adding new level to " + "Rack.");
								currentLevel = translatedLevel;
								parsedRack.add(Integer.valueOf(translatedLine[4]));
							} else {
								throw new IllegalArgumentException(
										"A line in the " + "traversal_table.csv is not valid.");
							}
						} else if (translatedRack == ((char) ((int) currentRack + 1))
								&& (translatedLevel == STARTING_LEVEL)) {
							currentLevel = STARTING_LEVEL;
							currentRack = translatedRack;
							parsedRack.add(Integer.valueOf(translatedLine[4]));
							parsedAisle.add(parsedRack.toArray(new Integer[parsedRack.size()]));
							parsedRack.clear();
						} else {
							throw new IllegalArgumentException("A line in the " + "traversal_table.csv is not valid.");
						}
					} else if (translatedAisle == ((char) ((int) currentAisle + 1)) && (translatedRack == STARTING_RACK)
							&& (translatedLevel == STARTING_LEVEL)) {
						currentLevel = STARTING_LEVEL;
						currentRack = STARTING_RACK;
						currentAisle = translatedAisle;
						parsedRack.add(Integer.valueOf(translatedLine[4]));
						parsedAisle.add(parsedRack.toArray(new Integer[parsedRack.size()]));
						parsedRack.clear();
						parsedZone.add(parsedAisle.toArray(new Integer[parsedAisle.size()][]));
						parsedAisle.clear();
					} else {
						throw new IllegalArgumentException("A line in the traversal_table.csv " + "is not valid.");
					}
				} else if (translatedZone == ((char) ((int) currentZone + 1)) && (translatedAisle == STARTING_AISLE)
						&& (translatedRack == STARTING_RACK) && (translatedLevel == STARTING_LEVEL)) {
					currentLevel = STARTING_LEVEL;
					currentRack = STARTING_RACK;
					currentAisle = STARTING_AISLE;
					currentZone = translatedZone;
					parsedRack.add(Integer.valueOf(translatedLine[4]));
					parsedAisle.add(parsedRack.toArray(new Integer[parsedRack.size()]));
					parsedRack.clear();
					parsedZone.add(parsedAisle.toArray(new Integer[parsedAisle.size()][]));
					parsedAisle.clear();
					parsedTraversalFile.add(parsedZone.toArray(new Integer[parsedZone.size()][][]));
					parsedZone.clear();
				} else {
					throw new IllegalArgumentException("A line in the traversal_table.csv is " + "not valid.");
				}
			}
			parsedRack.add(Integer.valueOf(translatedLine[4]));
			parsedAisle.add(parsedRack.toArray(new Integer[parsedRack.size()]));
			parsedZone.add(parsedAisle.toArray(new Integer[parsedAisle.size()][]));
			parsedTraversalFile.add(parsedZone.toArray(new Integer[parsedZone.size()][][]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return parsedTraversalFile.toArray(new Integer[parsedTraversalFile.size()][][][]);
	}

	/**
	 * The getWorkers function of Warehouse returns the workers that belong to
	 * that Warehouse.
	 *
	 * @return the ArrayList of Workers that belong to the Warehouse.
	 */
	public ArrayList<Worker> getWorkers() {
		System.out.println("Calling getWorkers() of Warehouse " + this.toString() + ".");
		System.out.println("    Returning " + this.workers.toString() + ".");
		return this.workers;
	}

	/**
	 * The addWorker function of Warehouse adds a Worker to the Warehouse
	 * workers ArrayList.
	 *
	 * @param newWorker
	 *            the Worker to be added to the Warehouse.
	 * @throws Exception 
	 */
	public void addWorker(Worker newWorker) throws Exception {
		boolean found = false;
		System.out.println("Calling addWorker of Warehouse " + this.toString() + ", with " + "argument newWorker as "
                + newWorker.toString() + ".");
		for(int i=0;i<this.workers.size();i++){
		    if(workers.get(i).equals(newWorker.getName())){
		        found = true;
		    }
		}
	    if (found == false){
	        this.workers.add(newWorker);
		}else{
		    throw new Exception("This Worker Already exists in this warehouse");
		}
	}

	public Floor getFloor() {
		return this.floor;
	}

	public Marshalling getMarshalling() {
		return this.marshalling;
	}

	public Server getServer() {
		return this.server;
	}
}
