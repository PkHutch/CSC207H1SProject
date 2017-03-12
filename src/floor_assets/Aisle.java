package floor_assets;

import java.util.ArrayList;

/*
 * The aisles from a zone
 */
public class Aisle {
	private ArrayList<Shelf> shelf;

	/*
	 * Initialze a flexable shelf for the Aisle
	 */
	public Aisle() {
		this.shelf = new ArrayList<Shelf>();
	}

	/*
	 * Part of the auto-complete function from Floor Can be called for specific
	 * specs
	 */
	public Aisle(int a, int b, int c) {
		for (int i = 0; i < a; i++) {
			Shelf x = new Shelf(b, c);
			this.shelf.add(x);
		}
	}

	/*
	 * Return an ArrayList, the shelf.
	 */
	public ArrayList<Shelf> get_Shelves() {
		return this.shelf;
	}

	/*
	 * Add a Shelf s to the arrayList Shelves.
	 */
	public void add_shelf(Shelf s) {
		this.shelf.add(s);
	}

	/*
	 * Remove a Shelf s from the arraylist Shelves.
	 */
	public void remove_zone(Shelf s) {
		for (int i = 0; i < this.shelf.size(); i++) {
			if (this.shelf.get(i) == s) {
				this.shelf.remove(s);
			}
		}
	}
}