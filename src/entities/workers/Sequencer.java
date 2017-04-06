// Defines the package.
package entities.workers;

import entities.Marshalling;
import entities.Pallet;
import entities.PickingRequest;
import entities.Stock;
import entities.taskentities.TaskGiver;
import entities.Warehouse;
import java.lang.IllegalStateException;
import java.util.ArrayList;

public class Sequencer extends Worker implements TaskGiver {
    private boolean isActive;

    public Sequencer(String name, Warehouse warehouse) {
        super(name, warehouse);
        this.isActive = true;
    }

    public void doTask() {
        if (this.isActive == false) {
            ArrayList<PickingRequest> pickingRequests = this.getWarehouse().getServer().getPickingRequests();
            Marshalling marshalling = this.getWarehouse().getMarshalling();
            ArrayList<Stock> stock = marshalling.getMarshallingStock();
            for (int pickingRequestIndex = 0; pickingRequestIndex < pickingRequests.size(); pickingRequestIndex++) {
                if (pickingRequests.get(pickingRequestIndex).getStatus() == 2) {
                    PickingRequest potentialPickingRequest = pickingRequests.get(pickingRequestIndex);
                    Integer[] potentiallySequenceableSKUs = potentialPickingRequest.getSKUs();
                    ArrayList<Stock> potentiallySequenceableStock = new ArrayList<>();
                    for (int skuIndex = 0; skuIndex < potentiallySequenceableSKUs.length; skuIndex++) {
                        boolean stockFound = false;
                        for (int stockIndex = 0; stockIndex < stock.size() && stockFound == false; stockIndex++) {
                            if (potentiallySequenceableSKUs[skuIndex].equals(stock.get(stockIndex).getSKU())) {
                                stockFound = true;
                                potentiallySequenceableStock.add(marshalling.popStock(stockIndex));
                            }
                        }
                    }
                    if (potentiallySequenceableStock.size() == potentiallySequenceableSKUs.length) {

                        marshalling.dumpSequenceableStock(potentiallySequenceableStock);
                        potentialPickingRequest.setStatus(3);
                    } else {
                        marshalling.dumpStock(potentiallySequenceableStock);
                        potentialPickingRequest.setStatus(0);
                    }
                }
            }
            marshalling.clearStock();
            while (pickingRequests.size() > 0 && pickingRequests.get(0).getStatus() == 3) {
                ArrayList<Stock> backPalletArrayList = new ArrayList<>();
                ArrayList<Stock> frontPalletArrayList = new ArrayList<>();
                Integer[] sequenceableSKUs = pickingRequests.remove(0).getSKUs();
                boolean loadToFront = true;
                ArrayList<Stock> sequenceableStock = marshalling.getSequenceableStock();
                for (int skuIndex = 0; skuIndex < sequenceableSKUs.length; skuIndex++) {
                    boolean stockFound = false;
                    for (int stockIndex = 0; stockIndex < sequenceableStock.size()
                            && stockFound == false; stockIndex++) {
                        if (sequenceableStock.get(stockIndex).getSKU().equals(sequenceableSKUs[skuIndex])) {
                            stockFound = true;
                            if (loadToFront == true) {
                                loadToFront = false;
                                frontPalletArrayList.add(marshalling.popSequenceableStock(stockIndex));
                            } else {
                                loadToFront = true;
                                backPalletArrayList.add(marshalling.popSequenceableStock(stockIndex));
                for(int skuIndex = 0; skuIndex < sequenceableSKUs.length; skuIndex++) {
                    boolean stockFound = false;
                    for(int stockIndex = 0; stockIndex < sequenceableStock.size() &&
                        stockFound == false; stockIndex++) {
                        if(sequenceableStock.get(stockIndex).getSKU().equals(sequenceableSKUs[
                            skuIndex])) {
                            stockFound = true;
                            if(loadToFront == true) {
                                loadToFront = false;
                                frontPalletArrayList.add(marshalling.popSequenceableStock(
                                    stockIndex));
                            } else {
                                loadToFront = true;
                                backPalletArrayList.add(marshalling.popSequenceableStock(
                                    stockIndex));
                            }
                        }
                    }
                }

                marshalling.addPallet(new Pallet(frontPalletArrayList.toArray(new Stock[
                    frontPalletArrayList.size()])));
                marshalling.addPallet(new Pallet(backPalletArrayList.toArray(new Stock[
                    backPalletArrayList.size()])));
            }
            this.isActive = true;
        } else {
            throw new IllegalStateException("The Sequencer \"" + this.getName() + "\" is not " +
                          "currently checked in as ready.");
        }
    }

    public void setReady() {
        if (this.isActive == true) {
            this.isActive = false;
        } else {
            throw new IllegalStateException(
                    "The Sequencer \"" + this.getName() + "\" is " + "currently checked in as ready.");
        }
    }
}