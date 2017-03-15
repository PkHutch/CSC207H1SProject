package floor_assets;

import stocking.Stock;

/*
 * Levels of a Rack
 */
public class Level {
	private Stock[] items;

	/*
	 * Initiaze a set 30 size limit level
	 */
	public Level() {
		this.items = new Stock[30];

	}

	/*
	 * Initiaze a set amount of items on this level
	 */
	public Level(int num) {
		this.items = new Stock[num];
	}

	/*
	 * Puts an array of items on the shelf planned to use when intiazing the
	 * warehouse
	 */
	public void fillLevel(Stock[] objects) {
		for (int i = 0; i < this.items.length; i++) {
			items[i] = objects[i];
		}
	}

	/*
	 * Puts single items on the shelf fails if shelf is full
	 */
	public void addItem(Stock object) {
		for (int i = 0; i < this.items.length; i++) {
			if (this.items[i] == null) {
				items[i] = object;
			}
			if (i == this.items.length && items[i] != null) {
				System.out.println("This Level is full");
			}
		}
	}

	/*
	 * Return the list of items.
	 */
	public Stock[] getItems() {
		return this.items;
	}

	/*
	 * Remove an item from the Array, items
	 */
	public void removeItem(Stock item) {
		for (int i = 0; i < this.items.length; i++) {
			if (this.items[i] == item) {
				this.items[i] = null;
			}
		}
	}

	public int numItem() {
		int count = 0;
		for (int i = 0; i < this.items.length; i++) {
			if (this.items[i] != null) {
				count += 1;
			}
		}
		return count;
	}
}
