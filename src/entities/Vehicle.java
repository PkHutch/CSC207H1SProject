package entities;

import java.util.LinkedList;

public abstract class Vehicle<T> {
	protected int currentInventorySize;
	protected int inventorySpace;
	protected LinkedList<T> inventory;

	public Vehicle(int inv) {
		this.inventorySpace = inv;
		this.inventory = new LinkedList<T>();
		this.currentInventorySize = 0;

	}

	public int getInventorySize() {
		return this.inventorySpace;
	}

	public abstract LinkedList<T> getInventory();

	public int getCurrentIntentorySize() {
		return currentInventorySize;
	}

	public abstract void addItem(T item);

	public abstract void removeItem(T item);
}
