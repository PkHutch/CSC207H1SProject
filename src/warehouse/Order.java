package warehouse;

public class Order {
	private String model;
	private String colour;

	public Order(String input1, String input2) {
		this.model = input1;
		this.colour = input2;
	}

	public String getModel() {
		return model;
	}

	public String getColour() {
		return colour;
	}
}
