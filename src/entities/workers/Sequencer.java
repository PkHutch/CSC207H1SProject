// Defines the package.
package entities.workers;

import entities.PickingRequest;
import entities.Pallet;
import entities.Warehouse;
import java.util.ArrayList;
import entities.taskentities.TaskGiver;
import entities.Stock;

public class Sequencer extends Worker implements TaskGiver {
    public Sequencer(String name, Warehouse warehouse) {
        super(name, warehouse);
    }

    public void doTask() {
        ArrayList<PickingRequest> pickingRequests = 
            this.getWarehouse().getServer().getPickingRequests();
        ArrayList<Stock> stock = this.getWarehouse().getMarshalling().getMarshallingStock();
        for (int i = 0; i < pickingRequests.size(); i++) {
            if (pickingRequests.get(i).getStatus() == 2) {
                int keep = 0;
                for (int j = 0; j < pickingRequests.get(i).getSKUs().length; j++) {
                    for (int k = 0; k < stock.size(); k++) {
                        if (stock.get(k).getSKU() == pickingRequests.get(i).getSKUs()[j]) {
                            keep += 1;
                        } else if (keep == pickingRequests.get(i).getSKUs().length) {
                            pickingRequests.get(i).setStatus(3);
                        }
                    }
                }
                pickingRequests.get(i).setStatus(0);
            }

            int l = 0;
            while (pickingRequests.get(i).getStatus() == 3) {
                Integer[] sequenced = pickingRequests.get(l).getSKUs();
                l++;
                Stock[] front = {new Stock(sequenced[0]), new Stock(sequenced[2]),
                                 new Stock(sequenced[4]), new Stock(sequenced[6])};
                Pallet pallet = new Pallet(front);
                this.getWarehouse().getMarshalling().addPallet(pallet);
                Stock[] back = {new Stock(sequenced[1]), new Stock(sequenced[3]),
                                new Stock(sequenced[5]), new Stock(sequenced[7]) };
                Pallet pallet2 = new Pallet(back);
                this.getWarehouse().getMarshalling().addPallet(pallet2);
            }
            this.getWarehouse().getMarshalling().clearStock();
        }
    }
}
