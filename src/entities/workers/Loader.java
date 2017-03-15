package entities.workers;

import entities.linkedlistcontainers.Truck;
import entities.Pallet;
import entities.taskentities.TaskGiver;
import entities.Warehouse;
import entities.Worker;

public class Loader extends Worker implements TaskGiver {
        private Truck truck;

        public Loader(String name, Warehouse warehouse) {
            super(name, warehouse);
        }

        public void doTask() {
            this.getWarehouse().getMarshalling().removePallets();
            this.getWarehouse().getServer().issueTask(this);
        }
}
