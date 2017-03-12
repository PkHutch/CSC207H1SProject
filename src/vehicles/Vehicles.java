package vehicles;

import stocking.*;

public class Vehicles {
	private int inventorySpace;
	private Fascia[] inventory;

	public Vehicles(int inv) {
		this.inventorySpace = inv;
		this.inventory = new Fascia[inv];

	}

	public int getInventorySize() {
		return this.inventorySpace;
	}

	public Fascia[] getInventory() {
		return inventory;
	}

	public void addItem(Fascia item) {
		for (int i = 0; i < this.inventorySpace; i++) {
			if (this.inventory[i] == null) {
				this.inventory[i] = item;
				this.inventorySpace += 1;
			} else if (this.inventory != null && i == this.inventory.length) {
				System.out.println("This is full");
			}
		}
	}

	public void removeItem(Fascia item) {
		for (int i = 0; i < this.inventorySpace; i++) {
			if (this.inventory[i] == item)
				this.inventory[i] = null;
		}
	}
}
