package shizuka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    private static final String ITEM_SEPARATOR = " | ";
    static void save(String filePath, String[] text) throws IOException {
        File f = new File(filePath);
        FileWriter fw = new FileWriter(filePath);
        if(f.createNewFile()){
            Printer.fileCreated();
        } else{
            Printer.fileExists();
        }
        for (String item : text) {
            fw.write(item + ITEM_SEPARATOR);
        }
        fw.close();
    }

    static void load(String filePath) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
    }

}