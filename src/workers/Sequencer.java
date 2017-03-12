package workers;

import stocking.*;
import java.util.*;

/*
 * The Sequencer class, a worker in the warehouse
 */

public class Sequencer extends Worker {
	
	/*
	 * Initialize a Sequencer object
	 */

	public Sequencer(String name, Warehouse warehouse) {
		super(name);
	}
	
	/*
	 * Complete the designated task of sequencing Stock items 
	 */

	public void doTask(Stock item) {
		;
	}
}
