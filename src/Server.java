// *NOTE* All commented out things are because of lack of proper implementation.
// Defines the package.
package entities;

// Imports necessary packages.
//import entities.Warehouse;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatException;

/**
 * This class defines the Server, which makes the majority of the logical decisions for a
 * warehouse. The specifics of the logical decisions that it makes are located in the
 * issueTask methods.
 */
public class Server {
//    private Warehouse warehouse;

    // Defines the constructor methods.
    /**
     * The constructor for Server which doesn't any values to it's attributes this should only be
     * called by the Warehouse that it belongs to.
     *
     * @param warehouse the warehouse which this server belongs to.
     */
//    public void Server(Warehouse warehouse) {
//        this.warehouse = warehouse;
//        parseTranslation();
//    }

    // Defines the helper methods.
    /**
     * Parses the translation.csv file so that the server has a more readily useable form
     * for the sake of looking up SKU numbers of units, this is more efficient than parsing
     * the file every single time an SKU lookup is required.
     *
     * @throws IllegalFormatException this is called when there are repeated SKUs in the
     *         translation.csv file.
     */
    private void parseTranslation() {
        BufferedReader bufferedReader = null;
        String line;
        final String translationFile = "./resources/translation.csv";
        final String splitBy = ",";

        try {
            bufferedReader = new BufferedReader(new FileReader(translationFile));

            // Skips the first line.
            line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] translatedLine = line.split(splitBy);

                for(int index = 0; index < this.translationArray.size(); index++) {
                    String[] currentTranslation = translationArray.get(index);

                    if(translatedLine[2] == currentTranslation[2] ||
                        translatedLine[2] == currentTranslation[3] ||
                        translatedLine[3] == currentTranslation[3] ||
                        translatedLine[3] == currentTranslation[2] ||
                        translatedLine[2] == translatedLine[3]) {
                        throw new IllegalArgumentException("The SKU " + translatedLine[2] + " or "
                            + translatedLine[3] + " already exists in the translation source.");
                    } else {
                        this.translationArray.add(new String[]{translatedLine[0],
                            translatedLine[1], translatedLine[2], translatedLine[3]});
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
		}
            }
        }
    }
}