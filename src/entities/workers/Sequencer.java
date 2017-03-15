package entities.workers;

import entities.Marshalling;
import entities.Pallet;
import entities.stocking.Fascia;
import entities.Warehouse;
import entities.Worker;
import java.util.ArrayList;
import java.util.LinkedList;

public class Sequencer extends Worker implements taskExecutor<String>{
    private LinkedList<Integer[]> activePickingRequests;

    public Sequencer(String name, Warehouse warehouse) {
        super(name, warehouse);
    }

    public void doTask(String argument) {
        if (argument.equals("sequence")) {
            // Call issueTask here to update activePickingRequests, then do
            // the group of eight filtering.
        } else {
            // Exception.
        }
    }
}