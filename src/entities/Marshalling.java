package entities;

import java.util.ArrayList;
import entities.LinkedListContainer;
import entities.Stock;
import entities.Pallet;

public class Marshalling {
	public ArrayList<Stock> marshallingStock;
	public ArrayList<Pallet> marshallingPallet;

	public Marshalling() {
		this.marshallingStock = new ArrayList<Stock>();
		this.marshallingPallet = new ArrayList<Pallet>();
	}

	public void addStock(ArrayList<Stock> stock) {
		while (stock.isEmpty()) {
			this.marshallingStock.add(stock.remove(0));
		}
	}

	public void addPallet(Pallet p) {
		this.marshallingPallet.add(p);
	}
	
	public void removeStock() {
		this.marshallingStock.clear();
	}

	public ArrayList<Stock> getMarshallingStock() {
		return this.marshallingStock;
	}

	public void removePallets() {
		this.marshallingPallet.clear();
	}

	public boolean checkInv(int SKU) {
		for (int i = 0; i < this.marshallingStock.size(); i++) {
			if (this.marshallingStock.get(i).getSKU() == SKU) {
				return true;
			}
		}
		return false;
	}
}