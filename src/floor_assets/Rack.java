package floor_assets;

import java.util.ArrayList;

/*
 * Racks of a Shelf
 */
public class Rack {
	private ArrayList<Level> level;

	/*
	 * Initiaze a flexable Rack for the Shelf.
	 */
	public Rack() {
		this.level = new ArrayList<Level>();
	}

	/*
	 * Part of the auto-complete, can be called alone if needed.
	 */
	public Rack(int a, int b) {
		this.level = new ArrayList<Level>();
		for (int i = 0; i < a; i++) {
			Level x = new Level(b);
			this.level.add(x);
		}
	}

	/*
	 * returns an Arraylist of levels
	 */
	public ArrayList<Level> get_level() {
		return this.level;
	}

	/*
	 * Adds a level to the ArrayList,Level
	 */
	public void add_level(Level l) {
		this.level.add(l);
	}

	/*
	 * Remove a level from the Arraylist, level
	 */
	public void remove_level(Level l) {
		for (int i = 0; i < this.level.size(); i++) {
			if (this.level.get(i) == l) {
				this.level.remove(l);
			}
		}
	}
}
