package entities;

import java.util.ArrayList;
import entities.Stock;
import entities.Pallet;
import entities.linkedlistcontainers.*;

public class Marshalling {
	public ArrayList<Stock> marshallingStock;
	public ArrayList<Pallet> marshallingPallet;

	public Marshalling() {
		this.marshallingStock = new ArrayList<Stock>();
		this.marshallingPallet = new ArrayList<Pallet>();
	}

	public void addStock(LinkedListContainer<Stock> stock) {
		while (!(stock.isEmpty())) {
			this.marshallingStock.add(stock.removeItem());
		}
	}

	public void addPallet(Pallet p) {
		this.marshallingPallet.add(p);
	}
	
	public void clearStock() {
		this.marshallingStock.clear();
	}

	public ArrayList<Stock> getMarshallingStock() {
		return this.marshallingStock;
	}

	public void clearPallets() {
		this.marshallingPallet.clear();
	}
}
