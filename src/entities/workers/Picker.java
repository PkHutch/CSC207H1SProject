// Defines the package.
package entities.workers;

<<<<<<< HEAD
import java.util.*;

import entities.Stock;
import entities.Worker;
import entities.stocking.Fascia;
import entities.Warehouse;
import entities.vehicles.Forklift;

/*
 * The Picker class, a worker in the warehouse
=======
// Defines the imports.
import entities.Level;
import entities.Stock;
import entities.vehicles.Forklift;
import entities.Warehouse;
import entities.Worker;

/**
 * The Picker class extends the worker class and takes an arguement of String for it's doTask
 * method. The Pickers job to stock off the shelves and bring them to the marshalling area as
 * per the server's instructions.
>>>>>>> e93ae04cfbc58c7c92f37d97fa76d608267d10a2
 */
public class Picker extends Worker<String> {
    private Forklift forklift;

    public Picker(String name, Warehouse warehouse) {
        super(name, warehouse);
        this.forklift = new Forklift();
    }

    public void doTask(String argument) {
        if(argument.equals("ready")) {
                String[] result = location.split(",");
                char c = result[0].charAt(0);
                int[] A = new int[5];
                A[0] = 0;
                for (int j = 1; j < 5; j++) {
                    A[j] = Integer.parseInt(result[j]);
                }
                Level currentLevel = this.getWarehouse().getFloor().getZone(result[0].charAt(0)).getAisle(Integer.parseInt(result[1])).getRack(Integer.parseInt(result[2])).getLevel(Integer.parseInt(result[3]));
                Stock currentStock = currentLevel.removeItem(Integer.parseInt(result[4]));
                forklift.addItem(currentStock);
                if(currentLevel.numItem() <= 5) {
                    this.getWarehouse().getServer().issueTask(currentLevel);
                }
        }
    }
}
