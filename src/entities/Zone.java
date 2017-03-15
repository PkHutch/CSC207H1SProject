package entities;

import java.util.ArrayList;

/*
 * The zones of a Floor
 */
public class Zone {
	private ArrayList<Aisle> aisles;

	/*
	 * This initize flexable amount of aisles to a zone
	 */
	public Zone() {
		this.aisles = new ArrayList<Aisle>();
	}

	/*
	 * Part of the auto-complete function, or can be used for specific specs if
	 * required.
	 */
	public Zone(int a, int b, int c, int d) {
		this.aisles = new ArrayList<Aisle>();
		for (int i = 0; i < a; i++) {
			Aisle y = new Aisle(b, c, d);
			this.aisles.add(y);
		}
	}

	/*
	 * Returns a ArrayList of aisles
	 */
	public ArrayList<Aisle> getAisles() {
		return this.aisles;
	}

        public Aisle getAisle(int index) {
                return this.aisles.get(index);
        }

    /**
     * getLevel returns the Level at the given indices, it is for convenience instead of having
     * to type a long chain of get functions.
     *
     * @param aisleInt the integer index of the aisle.
     * @param rackInt the integer index of the aisle.
     * @param levelInt the integer index of the aisle.
     * @return the level object at the desired location.
     */
    public Level getLevel(int aisleInt, int rackInt, int levelInt) {
       return this.aisles.get(aisleInt).getLevel(rackInt, levelInt);
    }

	/*
	 * Add an aisle a into the ArrayList
	 */
	public void addAisle(Aisle a) {
		this.aisles.add(a);
	}

	/*
	 * Remove an Aisle a from the
	 */
	public void removeAisles(Aisle a) {
		for (int i = 0; i < this.aisles.size(); i++) {
			if (this.aisles.get(i) == a) {
				this.aisles.remove(a);
			}
		}
	}
}
