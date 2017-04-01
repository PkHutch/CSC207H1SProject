// Defines the package.
package entities.workers;

// Defines the imports.
import entities.Marshalling;
import entities.PickingRequest;
import entities.Pallet;
import entities.stocking.Fascia;
import entities.Warehouse;
import entities.Worker;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import entities.taskentities.TaskGiver;
import entities.Stock;

public class Sequencer extends Worker implements TaskGiver {

	public Sequencer(String name, Warehouse warehouse) {
        super(name, warehouse);
    }

	public void doTask() {
    	ArrayList<PickingRequest> pickingRequests = this.getWarehouse().getServer().getPickingRequests();
    	ArrayList<Stock> stock = this.getWarehouse().getMarshalling().getMarshallingStock();
    	for (int i = 0; i < pickingRequests.size(); i++) {
    		if (pickingRequests.get(i).getStatus() == 2) {
    			int keep = 0;
    			for (int j = 0; j < pickingRequests.get(i).getSkus().length; j++) {
    				for (int k = 0; k < stock.size(); k++) {
    					if (stock.get(k).getSKU() == pickingRequests.get(i).getSkus()[j]) {
    						keep += 1;
    					} else if (keep == pickingRequests.get(i).getSkus().length) {
    						pickingRequests.get(i).setStatus(3);
    					}
    				}
    			}
    			pickingRequests.get(i).setStatus(0);
    		}
    	}
    	
    	int i = 0;
    	while(pickingRequests.get(i).getStatus() == 3) {
    		Integer[] sequenced = pickingRequests.get(i).getSkus();
    		i++;
    		this.getWarehouse().getMarshalling().addPallets(new Pallet(Stock[]{sequenced[0], 
    												sequenced[2], sequenced[4], sequenced[6]));
    		this.getWarehouse().getMarshalling().addPallets(new Pallet(Stock[]{sequenced[1], 
    												sequenced[3], sequenced[5], sequenced[7]));
    		}	
    		this.getWarehouse().getMarshalling().clearStock();
    	}
}
