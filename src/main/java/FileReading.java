import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReading {
    private static String filePath = "data/Duke.txt";

    public static void createFile(String filePath) throws IOException {
        File newFolder = new File("data");
        if (newFolder.createNewFile()) {
            System.out.println("File created: " + newFolder.getName());
            newFolder.mkdirs();
        } else {
            //newFolder.delete();
            //newFolder.mkdirs();
            System.out.println(newFolder + "file already exists.");
        }
        File file = new File("data/duke.txt");
        if (file.createNewFile()) {
            System.out.println("File created: " + file.getName());
        } else {
            System.out.println(file + "file already exists.");
        }
    }

    public static void getFileContents(String filePath, ArrayList<Task> tasksList) throws FileNotFoundException {
        File f = new File(filePath);
        if (f.exists()) {
            Scanner s = new Scanner(f);
            while(s.hasNext()) {
                //System.out.println(s.nextLine());
            }
        }
    }

    public static void deleteFileContents(String filePath) throws IOException {
        File f = new File(filePath);
        f.delete();
        try {
            createFile(filePath);
        } catch (IOException e) {
            System.out.println("An error has occurred :( ");
        //e.printStackTrace();
        }
    }

    public static void writeToFile(String filePath, ArrayList<Task> tasksList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        //write to file the arraylist?
        deleteFileContents(filePath);
        fw.write(tasksList.toString());
        fw.close();
    }

    public static void main(ArrayList<Task> Tasks) {
        try {
            writeToFile(filePath, Tasks);
            System.out.println("File edited");
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
