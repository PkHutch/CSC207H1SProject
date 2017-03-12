package floor_assets;

import java.util.ArrayList;

/*
 * Shelves of Aisles
 */
public class Shelf {
	private ArrayList<Rack> rack;

	/*
	 * Initiaze A flexable shelf
	 */
	public Shelf() {
		this.rack = new ArrayList<Rack>();
	}

	/*
	 * Part of the auto complete, can be called if needed
	 */
	public Shelf(int a, int b) {
		for (int i = 0; i < a; i++) {
			Rack x = new Rack(b);
			this.rack.add(x);
		}
	}

	/*
	 * Return the Arraylist rack
	 */
	public ArrayList<Rack> get_racks() {
		return this.rack;
	}

	/*
	 * Add a Rack r to the Arraylist rack
	 */
	public void add_rack(Rack r) {
		this.rack.add(r);
	}

	/*
	 * Remove a Rack r from the ArrayList rack
	 */
	public void remove_rack(Rack r) {
		for (int i = 0; i < this.rack.size(); i++) {
			if (this.rack.get(i) == r) {
				this.rack.remove(r);
			}
		}
	}
}