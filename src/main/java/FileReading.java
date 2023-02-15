import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReading {
    private static String filePath = "data/Duke.txt";

    public static void createFile(String filePath) throws IOException {
        try {
            File newFolder = new File("data");
            if (newFolder.createNewFile()) {
                System.out.println("File created: " + newFolder.getName());
            } else {
                //newFolder.delete();
                newFolder.mkdirs();
                System.out.println("File already exists.");
            }
            File myObj = new File("data/duke.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void getFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        if (f.exists()) {
            Scanner s = new Scanner(f);
            while(s.hasNext()) {
                System.out.println(s.nextLine());
            }
        }
    }

    public static void writeToFile(String filePath, ArrayList<Task> Tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        //write to file the arraylist?
        fw.write(Tasks.toString());
        fw.close();
    }

    public static void main(ArrayList<Task> Tasks) {
        try {
            createFile(filePath);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
