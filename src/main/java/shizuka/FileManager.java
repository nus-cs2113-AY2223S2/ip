package shizuka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    static void save(String filePath, String[] text) throws IOException {
        File f = new File(filePath);
        FileWriter fw = new FileWriter(filePath);
        f.createNewFile();
        for (String line : text) {
            fw.write(line + System.lineSeparator());
        }
        fw.close();
    }

}