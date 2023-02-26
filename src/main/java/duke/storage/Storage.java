package duke.storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Used for read/write operations to the default save directory: {projectDir}/data/save.txt
 */
public class Storage {
    private static final String home = System.getProperty("user.dir");
    private static final Path SAVE_DIR = Paths.get(home, "data");
    private static final Path SAVE_FILE = Paths.get(SAVE_DIR.toString(), "save.txt");

    /**
     * Checks if the save data directory exists. Creates the directory if is does not exist.
     */
    private static void checkSaveDir() {
        // check if file/directory exists
        File dir = new File(SAVE_DIR.toUri());
        if (!dir.exists()) { // create directory if required
            dir.mkdir();
        }
    }

    /**
     * Reads the save data from the default save directory
     *
     * @return String containing JSON data of the saved items. Empty string if no data is saved.
     */
    public String read() {
        checkSaveDir();
        try {
            return Files.readString(SAVE_FILE);
        } catch (IOException err) {
            return "";
        }
    }

    /**
     * Saves the given String to the default save directory.
     *
     * @param saveData String to be saved
     * @throws IOException If the save operation fails
     */
    public void save(String saveData) throws IOException {
        writeToFile(SAVE_FILE.toString(), saveData);
    }

    /**
     * Writes the given string to the path provided. Overwrites the file if already existing.
     *
     * @param filePath  Path of the destination file
     * @param textToAdd String to be written in the file
     * @throws IOException If the write operation fails
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        checkSaveDir();
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
