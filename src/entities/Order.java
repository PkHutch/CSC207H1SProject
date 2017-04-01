// Defines the package.
package entities;

/**
 * The Order class is simply what defines an order, and as of now is only for fascia.
 */
public class Order {
    // Defines the instance variables.
	private String colour;
	private String model;

    // Defines constructors.
    /**
     * The only constructor of an Order.
     *
     * @param colour the String which is the colour of the Order.
     * @param model the String which is the model of the Order.
     */
    public Order(String colour, String model) {
        System.out.println("Constructing Order " + this.toString() + " with argument colour " +
            "as " + colour + ", and argument model as " + model + ".");
        this.colour = colour;
        this.model = model;
    }

    // Defines the functional methods.
    /**
     * The getColour methods returns the colour of the order, as of right now orders only
     * exist for fascia.
     *
     * @return the String which is the colour of the car that the fascia is intended for.
     */
    public String getColour() {
        System.out.println("Calling getColour of " + this.toString() + ".");
        System.out.println("    Returning " + this.colour + ".");
        return this.colour;
    }

    /**
     * The getModel methods returns the model of the order, as of right now orders only exist
     * for fascia.
     *
     * @return the String which is the model of the car that the fascia is intended for.
     */
    public String getModel() {
        System.out.println("Calling getModel of " + this.toString() + ".");
        System.out.println("    Returning " + this.model + ".");
        return this.model;
    }
}