package workers;

import java.util.*;
import entities.marshalling;
import entities.stocking.*;
import entities.Pallet

public class Sequencer extends Worker {

	public Sequencer(String name, Warehouse warehouse) {
		super(name, warehouse);
	}

	public void doTask(LinkedList<ArrayList<Fascia>> orders) {
		for (int i = 0; i < orders.size(); i++) {
			ArrayList<Fascia> y = orders.removeFirst();
			Pallet p = new Pallet();
			p.addContent(y[0]);
			p.addContent(y[2]);
			p.addContent(p[4]);
			p.addContent(p[6]);
			Pallet pa = new Pallet();
			pa.addContent(y[1]);
			pa.addContent(y[3]);
			pa.addContent(p[5]);
			pa.addContent(p[7]);
			warehouse.getMarshalling().addPallet(p);
			warehouse.getMarshalling().addPallet(pa);
		}	
	}
}
