package entities;

import java.util.arraylist;

public class WarehousePicker {
	public static final String INITIAL_PATH = "./resources/traversal_table.csv";
	private int[] SKU;
	private ArrayList<String[]> locations;
	private ArrayList<String[]> results;

	public WarehousePicker(int[] SKU) {
		this.SKU = SKU;
		this.locations = readFile();
	}

	public readFile(){
		String csvFile = INITIAL_PATH;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<String[]> temp = new ArrayList<String[]>(); 
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				temp.add(line.split(cvsSplitBy));
			}
				
			}catch (FileNotFoundException e) {
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
			return temp;
	}

	public ArrayList<String[]> optimize() {
		for (int i = 0; i < this.locations.size(); i++) {
			for (int j = 0; j < this.SKU.length; j++) {
				if (locations.get(i) == this.SKU[j]) {
					this.result.add(locations.get(i));
				}
			}
		}
	}

}
