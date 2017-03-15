package entities;

import java.util.ArrayList;

/*
 * The aisles from a zone
 */
public class Aisle {
	private ArrayList<Rack> rack;

	/*
	 * Initialze a flexable shelf for the Aisle
	 */
	public Aisle() {
		this.rack = new ArrayList<Rack>();
	}

	/*
	 * Part of the auto-complete function from Floor Can be called for specific
	 * specs
	 */
	public Aisle(int a, int b, int c) {
		for (int i = 0; i < a; i++) {
			Rack x = new Rack(b, c);
			this.rack.add(x);
		}
	}

	/*
	 * Return an ArrayList, the shelf.
	 */
	public ArrayList<Rack> getRacks() {
		return this.rack;
	}

	/*
	 * Add a Shelf s to the arrayList Shelves.
	 */
	public void addRack(Rack r) {
		this.rack.add(r);
	}

	/*
	 * Remove a Shelf s from the arraylist Shelves.
	 */
	public void removeRack(Rack r) {
		for (int i = 0; i < this.rack.size(); i++) {
			if (this.rack.get(i) == r) {
				this.rack.remove(r);
			}
		}
	}
}