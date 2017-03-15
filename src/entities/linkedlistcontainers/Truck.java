package entities.linkedlistcontainers;

import entities.Pallet;
import entities.LinkedListContainer;
import java.util.LinkedList;

public class Truck extends LinkedListContainer<Pallet> {
        private final static int DEFAULT_INVENTORY_SIZE = 40;

	public Truck() {
		super(40);
	}
}
