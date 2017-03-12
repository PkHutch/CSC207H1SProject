
public class Order {
	private String size;
	private String colour;

	public Order(String input) {
		String[] parsed = input.split(" ");
		this.size = parsed[0];
		this.colour = parsed[1];
	}

	public String getSize() {
		return size;
	}

	public String getColour() {
		return colour;
	}
}
