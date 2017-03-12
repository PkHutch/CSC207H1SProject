
public class Order {
	private String size;
	private String colour;
	private int SKU;

	public Order(String input) {
		String[] parsed = input.split(" ");
		this.size = parsed[0];
		this.colour = parsed[1];
		this.SKU = Integer.parseInt((parsed[2]));
	}

	public String getSize() {
		return size;
	}

	public String getColour() {
		return colour;
	}

	public int getSKU() {
		return SKU;
	}

}
