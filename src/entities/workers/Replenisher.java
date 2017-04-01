package entities.workers;

import entities.arraylistcontainers.Level;
import entities.Server;
import entities.Stock;
import entities.taskentities.TaskExecutor;
import entities.Warehouse;
import entities.Worker;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import entities.Level;

public class Resupplier extends Worker implements TaskExecutor<String> {
    private final static int MAX_LEVEL_CAPACITY = 30;

    public Resupplier(String name, Warehouse warehouse) {
        super(name, warehouse);
    }

    public void doTask(String location) {
        String[] level = location.split(" ");
        Level nextLevel = this.getWarehouse().getFloor().getLevel(
                level[0].charAt(0), Integer.parseInt(level[1]),
                Integer.parseInt(level[2]),
                Integer.parseInt(level[3]));
        nextLevel.addStock(nextLevel.getMaxCapacity() - nextLevel.getStock());
    }
}