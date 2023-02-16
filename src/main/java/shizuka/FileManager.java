package shizuka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    static void save(String filePath, String[] text) throws IOException {
        File f = new File(filePath);
        FileWriter fw = new FileWriter(filePath);
        if(f.createNewFile()){
            Printer.fileCreated();
        } else{
            Printer.fileExists();
        };
        for (String line : text) {
            fw.write(line + System.lineSeparator());
        }
        fw.close();
    }

    static void load(String filePath) throws IOException {
        File f = new File(filePath);

    }

}