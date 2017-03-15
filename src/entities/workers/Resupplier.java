package entities.workers;

<<<<<<< HEAD
import entities.*;
import entities.Warehouse;
import entities.Worker;
import entities.stocking.*;
=======
import entities.Floor;
import entities.Aisle;
import entities.Zone;
import entities.Rack;
import entities.Level;
import entities.Warehouse;
import entities.stocking.Fascia;
import entities.Worker;
>>>>>>> e93ae04cfbc58c7c92f37d97fa76d608267d10a2

public class Resupplier extends Worker {

	public Resupplier(String name, Warehouse warehouse) {
		super(name, warehouse);
	}

	public void doTask() {
		for (int i = 0; i < warehouse.getFloor().getZones().size(); i++) {
			Zone z = warehouse.getFloor().getZone(i);
			for (int j = 0; j < z.getAisle().size(); j++) {
				Aisle a = z.getAisle().get(j);
				for (int k = 0; k < a.getRacks().size(); k++) {
					Rack r = a.getRacks().get(k);
					for (int l = 0; l < r.getLevel().size(); l++) {
						Level e = r.getLevel().get(l);
						for (int m = 0; m < e.numItem(); m++) {
							e.addItem(new Fascia(e.getItems()[0].getSKU()));
						}
					}
				}
			}
		}
	}
}
