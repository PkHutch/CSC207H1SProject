package entities.workers;

import entities.Marshalling;
import entities.Pallet;
import entities.stocking.Fascia;
import entities.Warehouse;
import entities.Worker;
import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.LinkedList;
import entities.taskentities.TaskExecutor;

public class Sequencer extends Worker implements TaskExecutor<String>{
    private LinkedList<Integer[]> activePickingRequests;

    public Sequencer(String name, Warehouse warehouse) {
        super(name, warehouse);
    }

    public void addPickingRequests(LinkedList<Integer[]> newPickingRequests) {
        this.activePickingRequests.addAll(newPickingRequests);
    }

    public void doTask(String argument) {
        if (argument.equals("sequence")) {
            // only deal with the first one
            // for every one in the first one, in order
            // remove it from the
            Integer[] firstEight = new Integer[8]

            for(int index = 0; index < 8; index++) {
                firstEight[0] = this.warehouse.getMarshalling().marshalling.remove(0);
            }

            boolean canSequence = true;

            for(int index = 0; index < 8; index++) {
                if(!(Arrays.asArray(activePickingRequests.get(0)).contains(new Integer(firstEight[index].getSKU()))) {
                    canSequence = false;
                }
            }

            if(canSequence == true) {
                Integer[] poppedRequest = activePickingRequest.pop();

                Pallet palletOne = new Pallet();
                palletFront.add(poppedRequest[0]);
                palletFront.add(poppedRequest[2]);
                palletFront.add(poppedRequest[4]);
                palletFront.add(poppedRequest[6]);

                Pallet palletTwo = new Pallet();
                palletFront.add(poppedRequest[1]);
                palletFront.add(poppedRequest[3]);
                palletFront.add(poppedRequest[5]);
                palletFront.add(poppedRequest[7]);

	        this.warehouse.getMarshalling().addPallet(palletOne);
	        this.warehouse.getMarshalling().addPallet(palletTwo);
            }
        } else {
            throw new IllegalArgumentException("The command " + argument + " does not exist for" +
                          " Sequencer " + this.getName() + ".");
        }
    }
}