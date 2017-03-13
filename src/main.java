import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import workers.*;

public class main {

	public static final String INITIAL_PATH = "./resources/initial.csv";
	private static Scanner input;

	public static Warehouse init() {
		String csvFile = INITIAL_PATH;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] init = line.split(cvsSplitBy);

				System.out.println("Zone[" + init[0] + "] Aisle:" + init[1] + " Rack[" + init[2] + "] Level:" + init[3]
						+ " with " + init[4] + " items");
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
		return null;
	}

	public static String getInput() {
		input = new Scanner(System.in);
		return input.next();
	}

	public static void run(Warehouse w) {
		boolean b = false;
		while (b == false) {
			String command = getInput();
			String[] par = command.split(" ");
			switch (par[0]) {
			case "Order":
				ArrayList<FaxMachine> faxes = w.getFaxMachines();
				FaxMachine fax = faxes.get(0);
				fax.addOrder(new Order(par[1] + par[2]));

			case "Quit":
				b = true;
			case "Picker":
				if (par[2] == "ready") {
					ArrayList<Worker> works = w.getWorkers();
					for (int i = 0; i < works.size(); i++) {
						if (works.get(i).getName() == par[1]) {
							Server s = w.getServer();
							s.inactivePickers.add(works.get(i));
						} else {
							w.addWorker(new Picker(par[1]));
						}
					}
				}
			case "Sequencer":
				if (par[2] == "ready") {
					ArrayList<Worker> works = w.getWorkers();
					for (int i = 0; i < works.size(); i++) {
						if (works.get(i).getName() == par[1]) {
							Server s = w.getServer();
							s.inactiveSequencer.add(works.get(i));
						} else {
							w.addWorker(new Sequencer(par[1]));
						}
					}
				}
			case "Loader":
				if (par[2] == "ready") {
					ArrayList<Worker> works = w.getWorkers();
					for (int i = 0; i < works.size(); i++) {
						if (works.get(i).getName() == par[1]) {
							Server s = w.getServer();
							s.inactiveLoaders.add(works.get(i));
						} else {
							w.addWorker(new Loader(par[1]));
						}
					}
				}
			case "Replenisher":
				if (par[2] == "ready") {
					ArrayList<Worker> works = w.getWorkers();
					for (int i = 0; i < works.size(); i++) {
						if (works.get(i).getName() == par[1]) {
							Server s = w.getServer();
							s.inactiveResupplier.add(works.get(i));
						} else {
							w.addWorker(new Replenisher(par[1]));
						}
					}
				}

			default:
				System.out.println("Could not find a matching action");
			}
		}
	}

	public static void main(String[] args) {
		Warehouse wh = main.init();
		main.run(wh);
	}
}
