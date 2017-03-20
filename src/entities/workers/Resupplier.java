package entities.workers;

import entities.arraylistcontainers.Level;
import entities.Server;
import entities.Stock;
import entities.taskentities.TaskExecutor;
import entities.Warehouse;
import entities.Worker;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;

public class Resupplier extends Worker implements TaskExecutor<String> {
    private ArrayList<Level> lowLevels;
    private final static int MAX_LEVEL_CAPACITY = 30;

    public Resupplier(String name, Warehouse warehouse) {
        super(name, warehouse);
    }

    public void addLowLevels(ArrayList<Level> newLevels) {
        this.lowLevels.addAll(newLevels);
    }

    public void doTask(String argument) {
        if(argument == "replenish") {
            this.warehouse.getServer().issueTask(this);

            for(int index = 0; index < this.lowLevels.size(); index++) {
                int stockSKU = this.lowLevels.get(index).getItem(0).getSKU();

                for (int stock = this.lowLevels.get(index).getSize(); 
                    stock >= MAX_LEVEL_CAPACITY; stock++) {
                    this.lowLevels.get(index).addItem(new Stock(stockSKU));
                }
            }

            this.lowLevels.clear();
        } else {
            throw new IllegalArgumentException("The command " + argument + " does not exist for" +
                          " Resupplier " + this.getName() + ".");
        }
    }
}
