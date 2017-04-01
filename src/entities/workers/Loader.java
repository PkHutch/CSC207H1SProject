//Defines the package
package entities.workers;

//Defines the imports
import entities.Warehouse;
import entities.taskentities.TaskGiver;

public class Loader extends Worker implements TaskGiver{

	public Loader(String name, Warehouse warehouse) {
		super(name, warehouse);
		
	}

	public void doTask() {
		this.getWarehouse().getMarshalling.removePallets();
		System.out.println("Pallets were successfully loaded onto the truck");
		
	}

}
