import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import floor_assets.*;
import workers.*;

public class main {
	public static final String INITIAL_PATH = "./resources/initial.csv";
	private static Scanner input;

	public static void main(String[] args) {
		Warehouse wh = main.init();
		main.run(wh);
	}

	public static Warehouse init() {
		String csvFile = INITIAL_PATH;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		Warehouse wh = new Warehouse(2, 2, 3, 4, 30);

		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] init = line.split(cvsSplitBy);

				System.out.println("Zone[" + init[0] + "] Aisle:" + init[1] + " Rack[" + init[2] + "] Level:" + init[3]
						+ " with " + init[4] + " items");
				Floor f = wh.getFloor();
				int zone = init[0].charAt(0) - 'A';
				Zone z = f.getZones().get(zone);
				Aisle aisle = z.getAisle().get(Integer.parseInt(init[1]));
				Rack rack = aisle.getRacks().get(Integer.parseInt(init[2]));

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return wh;
	}

	public static String getInput() {
		input = new Scanner(System.in);
		return input.next();
	}

	public static void run(Warehouse w) {
		boolean b = false;
		while (b == false) {
			Server s = w.getServer();
			String command = getInput();
			String[] par = command.split(" ");
			switch (par[0].toLowerCase()) {
			case "order":
				ArrayList<FaxMachine> faxes = w.getFaxMachines();
				FaxMachine fax = faxes.get(0);
				fax.addOrder(new Order(par[1] + par[2]));
				break;
			case "quit":
				b = true;
			case "picker":
				if (par[2] == "ready") {
					ArrayList<Worker> worksP = w.getWorkers();
					for (int i = 0; i < worksP.size(); i++) {
						if (worksP.get(i).getName() == par[1]) {
							s.inactivePickers.add(worksP.get(i));
						} else {
							Picker p = new Picker(par[1]);
							w.addWorker(p);
							s.inactivePickers.add(p);
						}
					}
					break;
				} else if (par[2] == "pick") {
					Server s = w.getServer();
					if (s.inactivePicker.contains(par[1])) {
						for (int i = 0; i < inactivePicker.size(); i++) {
							if (inactivePicker.get(i).getName() == par[1]) {
								Picker p = inactivePicker.get(i);
								p.doTask();
							}
						}
					}
				}
				break;
			case "sequencer":
				if (par[2] == "sequences") {
					ArrayList<Worker> worksS = w.getWorkers();
					for (int i = 0; i < worksS.size(); i++) {
						if (worksS.get(i).getName() == par[1]) {
							s.inactiveSequencer.add(worksS.get(i));
						} else {
							Sequencer seq = new Sequencer(par[1]);
							w.addWorker(seq);
							s.inactiveSequencer.add(seq);
							seq.doTask();
						}
					}
				}
				break;
			case "loader":
				if (par[2] == "loads") {
					ArrayList<Worker> worksL = w.getWorkers();
					for (int i = 0; i < worksL.size(); i++) {
						if (worksL.get(i).getName() == par[1]) {
							Server s = w.getServer();
							s.inactiveLoader.add(worksL.get(i));
						} else {
							Loader load = new Loader(par[1]);
							w.addWorker(load);
							load.doTask();
						}
					}
				}
				break;
			case "replenisher":
				if (par[2] == "replenish") {
					ArrayList<Worker> worksR = w.getWorkers();
					for (int i = 0; i < worksR.size(); i++) {
						if (worksR.get(i).getName() == par[1]) {
							s.inactiveReplenisher.add(worksR.get(i));
						} else {
							Resupplier re = new Resupplier(par[1]);
							w.addWorker(re);
							s.inactiveReplenisher.add(re);
							re.doTask();
						}
					}
				}
				break;

			default:
				System.out.println("Could not find a matching action");
			}
		}
	}
}
