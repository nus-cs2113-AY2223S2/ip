import java.io.FileNotFoundException;
import java.io.IOException;

public class Storage {
    private FileReading fileReading;

    public void load() {
        try {
            fileReading.getFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("I can't find what you want :(");
        }
    }

    public void write() {
        try {
            fileReading.writeToFile();
        } catch (IOException e) {
            System.out.println("This is out of my ability to execute...");
        }
    }
}
