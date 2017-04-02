package entities;

import entities.linkedlistcontainers.LinkedListContainer;
import entities.Pallet;
import entities.Stock;
import java.util.ArrayList;

public class Marshalling {
    private final ArrayList<Pallet> marshallingPallet;
    private final ArrayList<Stock> marshallingStock;

    public Marshalling() {
        this.marshallingPallet = new ArrayList<Pallet>();
        this.marshallingStock = new ArrayList<Stock>();
    }

    public void addPallet(Pallet p) {
        this.marshallingPallet.add(p);
    }

    public void addStock(LinkedListContainer<Stock> stock) {
        while (!(stock.isEmpty())) {
            this.marshallingStock.add(stock.removeItem());
        }
    }

    public void clearPallets() {
        this.marshallingPallet.clear();
    }

    public void clearStock() {
        this.marshallingStock.clear();
    }

    public ArrayList<Stock> getMarshallingStock() {
        return this.marshallingStock;
    }
}
