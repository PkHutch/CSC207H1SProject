package entities;

import entities.linkedlistcontainers.LinkedListContainer;
import entities.Pallet;
import entities.Stock;
import java.util.ArrayList;

public class Marshalling {
    private final ArrayList<Pallet> marshallingPallet;
    private final ArrayList<Stock> marshallingStock;
    private final ArrayList<Stock> sequenceableStock;

    public Marshalling() {
        this.marshallingPallet = new ArrayList<Pallet>();
        this.marshallingStock = new ArrayList<Stock>();
        this.sequenceableStock = new ArrayList<Stock>();
    }

    public void addPallet(Pallet p) {
        this.marshallingPallet.add(p);
    }

    public void addSequenceableStock(Stock newStock) {
        this.sequenceableStock.add(newStock);
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

    public ArrayList<Stock> getSequenceableStock() {
        return this.sequenceableStock;
    }

    public Stock popSequenceableStock(int index) {
        return this.sequenceableStock.remove(index);
    }
}
