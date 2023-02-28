import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReading {
    private static final String filePath = "data/Duke.txt";
    private static Task t;

    public static void createFile() throws IOException {
        File newFolder = new File("data");
        if (!newFolder.exists()) { //if file does not exist
            //System.out.println("File created: " + newFolder.getName());
            //newFolder.mkdirs();
            newFolder.createNewFile();
        } else {
            newFolder.delete();
            newFolder.createNewFile();
//            newFolder.mkdirs();
            //System.out.println(newFolder + " file already exists.");
        }
        newFolder.mkdirs();
        //System.out.println("make data a directory?" + newFolder.mkdirs());
        File file = new File("data/duke.txt");
        if (!file.exists()) {
            //System.out.println("File created: " + file.getName());
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
            //System.out.println(file + " file already exists.");
        }
    }

    public static void getFileContents(String filePath, ArrayList<Task> tasksList) throws FileNotFoundException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(f);
        System.out.println("File name: " + f.getName());
        System.out.println("Absolute Path: " + f.getAbsolutePath());
        System.out.println("Writeable: " + f.canWrite());
        System.out.println("Readable: " + f.canRead());

        //while(s.hasNext()) {
            //System.out.println(s.nextLine());
            try {
                //System.out.println(s.nextLine());
                //t.getTask(s.nextLine());
                String taskA = Files.readString(Path.of(filePath));
                System.out.println(taskA);

            } catch (NullPointerException e) {
                System.out.println("Can't remember what was saved :(");
            } catch (IOException e) {
                System.out.println("IOException :(");
                throw new RuntimeException(e);
            }
            //tasksList.add(t);
            //System.out.println(t.toString() + " has been added to tasks list.");
        //}
    }

    public static void deleteFileContents(String filePath) throws IOException {
        File f = new File(filePath);
        f.delete();
        try {
            createFile();
        } catch (IOException e) {
            System.out.println("An error has occurred :( ");
        //e.printStackTrace();
        }
    }

    public static void writeToFile(String filePath, ArrayList<Task> tasksList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        //write to file the arraylist?
        deleteFileContents(filePath);
        for (Task t: tasksList) {
            fw.write(t.toString() + "\n");
        }
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
