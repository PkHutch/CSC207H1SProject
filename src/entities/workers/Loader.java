//Defines the package
package entities.workers;

//Defines the imports
import entities.Warehouse;
import entities.taskentities.TaskGiver;

public class Loader extends Worker implements TaskGiver {
    private boolean isActive;

    public Loader(String name, Warehouse warehouse) {
        super(name, warehouse);
        this.isActive = false;
    }

    public void doTask() {
        if (isActive == false) {
            this.getWarehouse().getMarshalling().clearPallets();
            System.out.println("Pallets were successfully loaded onto the truck");
            this.isActive = true;
        } else {
            // raise exception
        }
    }

    public void setReady() {
        this.isActive = false;
    }
}
