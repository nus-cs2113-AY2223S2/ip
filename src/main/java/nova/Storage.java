package nova;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Storage {

    private static final String directoryPath = "nova_data";
    private static final String filePath = "nova_data/nova_inputs.txt";

    /**
     * Creates a new data file and folder if necessary.
     */
    public static void findFile() throws IOException {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File saveFile = new File(filePath);
        if (!saveFile.exists()) {
            saveFile.createNewFile();
        }
    }

    public static void checkFile() throws FileNotFoundException {
        try {
            findFile();
        } catch (IOException e) {
            System.out.println("Error saving file!");
        }
    }
}
