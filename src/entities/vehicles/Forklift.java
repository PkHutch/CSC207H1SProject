package entities.vehicles;

import java.util.LinkedList;
import entities.Stock;
import entities.Vehicle;

public class Forklift extends Vehicle {

	public Forklift() {
		super(8);
	}

	public LinkedList<Stock> getInventory() {
		return inventory;

	}

	public void addItem(Stock item) {
		if (currentInventorySize < inventorySpace) {
			inventory.add(item);
			currentInventorySize++;
		} else {
			System.out.println("The vehicle is full");
		}
	}

	public void removeItem(Stock item) {
		inventory.remove(item);
	}

}
