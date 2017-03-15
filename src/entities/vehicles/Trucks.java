package entities.vehicles;

import entities.Pallet;
import java.util.LinkedList;
import entities.Stock;
import entities.Vehicle;

public class Trucks extends Vehicle {
	public Trucks() {
		super(10000);
	}

	public LinkedList<Pallet> getInventory() {
		return inventory;

	}

	public void addItem(Pallet item) {
		if (currentInventorySize < inventorySpace) {
			inventory.add(item);
			currentInventorySize++;
		} else {
			System.out.println("The vehicle is full");
		}
	}

	public void removeItem(Pallet item) {
		inventory.remove(item);
	}

}
