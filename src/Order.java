
public class Order {
	private String model;
	private String colour;

	public Order(String input) {
		String[] parsed = input.split(" ");
		this.model = parsed[0];
		this.colour = parsed[1];
	}

	public String getModel() {
		return model;
	}

	public String getColour() {
		return colour;
	}
}
