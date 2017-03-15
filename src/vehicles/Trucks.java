package vehicles;

import java.util.LinkedList;

import stocking.Stock;

public class Trucks extends Vehicles {
	public Trucks() {
		super(80);
	}
	
	public LinkedList<Pallet> getInventory() {
		return inventory;
		
	}
	
	public void addItem(Pallet item) {
		if (currentInventorySize < inventorySpace) {
			inventory.add(item);
			currentInventorySize++;
		} else {System.out.println("The vehicle is full");}
	}
	
	public void removeItem(Pallet item) {
		inventory.remove(item);
		}


}
