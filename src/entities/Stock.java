// Defines the package.
package entities;

/**
 * A Stock object is an item of Stock in the Warehouse, it's only job it to keep track of the SKU.
 */
public class Stock {
    // Defines instance variables.
    private String sku;

    // Defines the constructors.
    /**
     * The default and only constructor, Integer.
     *
     * @param sku the Integer that represents the SKU of the Stock.
     */
    public Stock(String sku) {
        this.sku = sku;
    }

    // Defines the functional methods.
    /**
     * The getSKU function of Stock returns the SKU of that stock.
     *
     * @return the Integer which represents the SKU of the Stock.
     */
    public String getSKU() {
        return this.sku;
    }
}
