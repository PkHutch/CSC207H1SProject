import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class main {
	public static final String INITIAL_PATH = "./resources/initial.csv";

	public static void main(String[] args) {
		String csvFile = INITIAL_PATH;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		File f = new File("ice.txt");
		System.out.println(f.getAbsolutePath());

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

	}
}
