package stocking;

public class Fascia extends Stock {
	private String color;
	private String model;

	public Fascia(int SKU) {
		super(SKU);
	}

	public Fascia(String colour, String model, int SKU) {
		super(SKU);
		this.color = colour;
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public String getModel() {
		return model;
	}

}
