package floor_assets;

/*
 * Levels of a Rack
 */
public class Level {
	private int[] items;

	/*
	 * Initiaze a set 30 size limit level
	 */
	public Level() {
		this.items = new int[30];
	}

	/*
	 * Initiaze a set amount of items on this level
	 */
	public Level(int num) {
		this.items = new int[num];
	}

	/*
	 * Puts an array of items on the shelf planned to use when intiazing the
	 * warehouse
	 */
	public void fill_level(int[] objects) {
		for (int i = 0; i < this.items.length; i++) {
			items[i] = objects[i];
		}
	}

	/*
	 * Puts single items on the shelf fails if shelf is full
	 */
	public void add_item(int object) {
		for (int i = 0; i < this.items.length; i++) {
			if (this.items[i] == 0) {
				items[i] = object;
			}
			if (i == this.items.length && items[i] != 0) {
				System.out.println("This Level is full");
			}
		}
	}

	/*
	 * Return the list of items.
	 */
	public int[] get_items() {
		return this.items;
	}

	/*
	 * Remove an item from the Array, items
	 */
	public void remove_item(int item) {
		for (int i = 0; i < this.items.length; i++) {
			if (this.items[i] == item) {
				this.items[i] = 0;
			}
		}
	}
}
