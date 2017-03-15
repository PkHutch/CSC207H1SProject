package entities;

import java.util.ArrayList;

public class Marshalling {
	public ArrayList<Stock> marshalling;
	public ArrayList<Pallet> marshallingPallet;

	public Marshalling() {
		this.marshalling = new ArrayList<Stock>();
		this.marshallingPallet = new ArrayList<Pallet>();
	}

	public void addStock(Stock s) {
		this.marshalling.add(s);
	}

	public void addPallet(Pallet p) {
		this.marshallingPallet.add(p);
	}

	public Stock removeStock(Stock s) {
		this.marshalling.remove(s);
		return s;
	}

	public void removePallets() {
		this.marshallingPallet.clear();
	}

	public boolean checkInv(int SKU) {
		for (int i = 0; i < this.marshalling.size(); i++) {
			if (this.marshalling.get(i).getSKU() == SKU) {
				return true;
			}
		}
		return false;
	}
}
