// Defines the package.
package entities;

// Defines the imports.
import entities.arraycontainers.Aisle;
import entities.arraycontainers.Floor;
import entities.arraycontainers.Rack;
import entities.arraycontainers.Zone;

/**
 * The Level class contains Stock of a single SKU and is contained by a Rack.
 */
public class Level {
    // Defines the constants.
    private static final int DEFAULT_MAX_QUANTITY = 30;

    // Defines the instance variables.
    private Rack containedBy;
    private Integer sku;
    private int stock;
    private int max;
    // Defines the constructors.
    /**
     * The default Level constructor, constructs the Level according to the DEFAULT_MAX_QUANTITY
     * for assigning the currentQuantity because no quantity has been given.
     *
     * @param rack the Rack object which contains this Level.
     * @param sku the Integer which this Level contains, this should be a valid sku as defined by
     *        the Server that the Level belongs to, but this is not inforced.
     */
    public Level(Rack rack, Integer sku) {
        System.out.println("Constructing Level " + this.toString() + " with argument rack as " +
            rack.toString() + ".");
        this.containedBy = rack;
        this.sku = sku;
        this.stock = 0;
        this.max = DEFAULT_MAX_QUANTITY;
    }
    public Level(Rack rack, Integer sku, int maxSize){
    	System.out.println("Constructing Level " + this.toString() + " with argument rack as " +
                rack.toString() + ".");
        this.containedBy = rack;
        this.sku = sku;
        this.stock = 0; 
        this.max = maxSize;
    }

    // Defines the functional methods.
    /**
     * The getLocation method of Level returns the String need to access the Level.
     *
     * @return the String location of the Level in the form of "zone, aisle, rack, level" where
     * zone is a character, and aisle, rack, and level are all string integers of the Level
     * location.
     */
    public String getLocation() {
        Rack rack = this.containedBy;
        Aisle aisle = rack.getContainer();
        Zone zone = aisle.getContainer();
        Floor floor = zone.getContainer();
        return Character.toString((char)((int) 'A' + floor.getIndex(zone))) + "," + 
            Integer.toString(zone.getIndex(aisle)) + "," +
            Integer.toString(aisle.getIndex(rack)) + "," +
            Integer.toString(rack.getIndex(this));
    }

	public boolean atMaxCapacity() {
		if (this.stock == this.max){
			return true;
		}else{
			return false;
		}
	}
	public int getMaxCapacity(){
		return this.max;
	}
	
	public int getStock(){
		return this.stock;
	}

	public void addStock(int parseInt) {
		if(this.stock + parseInt < this.max){
			this.stock += parseInt;
		}else{
			System.out.println("You've added too much to the level");
		}
	}
	public Integer getSku() {
		return sku;
	}
	public void setSku(Integer sku) {
		this.sku = sku;
	}
}
