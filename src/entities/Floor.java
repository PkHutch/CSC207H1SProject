package entities;

import java.util.ArrayList;

/*
 * The floor of a warehouse
 */
public class Floor {
	private ArrayList<Zone> zones;

	/*
	 * Initialize according to job specifications;
	 */
	public Floor() {
		this.zones = new ArrayList<Zone>();
	}

	/*
	 * Calls for a Floor with a certain number of Zones
	 */
	public Floor(int num) {
		this.zones = new ArrayList<Zone>();
		for (int i = 0; i < num; i++) {
			Zone a = new Zone();
			this.zones.add(a);
		}
	}

	/*
	 * Auto Completes the Entire area with one array containing the values of
	 * each sub zones
	 */
	public Floor(int a, int b, int c, int d, int e) {

		this.zones = new ArrayList<Zone>();
		for (int i = 0; i < a; i++) {
			Zone z = new Zone(b, c, d, e);
			this.zones.add(z);
		}
	}

	/*
	 * Gets a list of zones
	 */
	public ArrayList<Zone> getZones() {
		return this.zones;
	}

	/*
	 * Adds a zone to the floor
	 */
	public void addZone(Zone z) {
		this.zones.add(z);
	}

    /**
     * getLevel returns the Level at the given indices, it is for convenience instead of having
     * to type a long chain of get functions.
     *
     * @param zoneCharacter the character of the zone which the level is located in.
     * @param aisleInt the integer index of the aisle.
     * @param rackInt the integer index of the aisle.
     * @param levelInt the integer index of the aisle.
     * @return the level object at the desired location.
     */
    public Level getLevel(char zoneCharacter, int aisleInt, int rackInt, int levelInt) {
       return this.zones.get(zoneCharacter - 'A').getLevel(aisleInt, rackInt, levelInt);
    }

	public Zone getZone(char c) {
		return this.zones.get(c - 'A');
	}

	public Zone getZone(int i) {
		return this.zones.get(i);
	}

	/*
	 * Remove a Zone z from the floor
	 */
	public void removeZone(Zone z) {
		for (int i = 0; i < this.zones.size(); i++) {
			if (this.zones.get(i) == z) {
				this.zones.remove(z);
			}
		}
	}
}
