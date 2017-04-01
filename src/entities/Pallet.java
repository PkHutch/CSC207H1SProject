// Defines the package.
package entities;

// Defines the imports.
import entities.Stock;
import java.lang.IllegalArgumentException;

/**
 * The Pallet class is responsible for carrying four fascia.
 */
public class Pallet {
    // Defines the instance variables.
    private final static int DEFAULT_PALLET_SIZE = 4;
    private final Stock[] content;

    /**
     * The default and only constructor of a Pallet.
     *
     * @param content the Stock[] that is to be added to / create the Pallet.
     */
    public Pallet(Stock[] content) {
        System.out.println("Constructing Pallet " + this.toString() + " with argument content " +
            " as " + content.toString() + ".");
        if(content.length != DEFAULT_PALLET_SIZE) {
            throw new IllegalArgumentException("Attempted construction of Pallet using content " +
                          " of size " + content.length + " but required " + DEFAULT_PALLET_SIZE +
                          ".");
        }
        this.content = content;
    }
}
