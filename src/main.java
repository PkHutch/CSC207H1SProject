import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class main {

	public static final String INITIAL_PATH = "./resources/initial.csv";
	private static Scanner input;

	public static void init() {
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
	}

	public static String getInput() {
		input = new Scanner(System.in);
		return input.next();
	}

	public static void main(String[] args) {
		main.init();

	}
}
