package duke.storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static final String home = System.getProperty("user.dir");
    private static final Path SAVE_DIR = Paths.get(home, "data");
    private static final Path SAVE_FILE = Paths.get(SAVE_DIR.toString(), "save.txt");

    private static void checkSaveDir() {
        // check if file/directory exists
        File dir = new File(SAVE_DIR.toUri());
        if (!dir.exists()) { // create directory if required
            dir.mkdir();
        }
    }

    // https://nus-cs2113-ay2223s2.github.io/website/schedule/week6/topics.html#w6-3-java-file-access
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        checkSaveDir();
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static String read() {
        checkSaveDir();
        try {
            String data = Files.readString(SAVE_FILE);
            return data;
        } catch (IOException err) {
            return "";
        }
    }

    public static void save(String saveData) throws IOException {
        writeToFile(SAVE_FILE.toString(), saveData.toString());
    }
}
