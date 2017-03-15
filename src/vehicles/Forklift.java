package vehicles;

import java.util.LinkedList;
import stocking.*;

public class Forklift extends Vehicles {

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

	@Override
	public void addItem(Object item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeItem(Object item) {
		// TODO Auto-generated method stub
		
	}

}
