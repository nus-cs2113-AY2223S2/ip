package duke.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataAccess {
    String filePath;
    FileWriter fileWriter;
    File file;

    public DataAccess(String filePath) {
        this.filePath = filePath;
        try {
            this.file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            this.fileWriter = new FileWriter(filePath);
        } catch (IOException e) {
            System.out.println("Error instantiating Data Access Object for " + filePath);
        }
    }

    public void writeToFile(String text) {
        try {
            fileWriter.write(text);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
        }
    }

    public void closeFile() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error closing file: " + filePath);
        }
    }

}
