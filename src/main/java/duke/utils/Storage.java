package duke.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that serves interaction between txt and CLI app
 */
public class Storage {
    /**
     * The base filepath of the saved txt
     */
    String filePath;


    /**
     * Constructor that initializes the Storage object
     *
     * @param filePath The filepath of the txt file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method to write to a file
     *
     * @param text Text to be written to a file
     */
    public void writeToFile(String text) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, false));
            this.emptyFile();
            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
        }
    }

    /**
     * Method to empty the file
     */
    public void emptyFile() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, false));
            bufferedWriter.write("");
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot empty the file: " + filePath);
            ;
        }
    }

}
