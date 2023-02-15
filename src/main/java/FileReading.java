import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReading {
    private static String filePath = "data/Duke.txt";

    public static void getFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        if (f.exists()) {
            Scanner s = new Scanner(f);
        }
    }

    public static void writeToFile(String filePath, ArrayList<Task> Tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        //write to file the arraylist?
        fw.write(Tasks.toString());
        fw.close();
    }

    public static void main(String[] args) {
        try {
            getFileContents(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
