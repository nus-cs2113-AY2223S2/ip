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
    private static ArrayList<Task> tasks;

    public static void createFile() throws IOException {
        File newFolder = new File("data");
        if (!newFolder.exists()) {
            newFolder.createNewFile();
        } else {
            newFolder.delete();
            newFolder.createNewFile();
        }
        newFolder.mkdirs();
        File file = new File("data/duke.txt");
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
    }

    public static void getFileContents() throws FileNotFoundException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(f);
        System.out.println("File name: " + f.getName());
        System.out.println("Absolute Path: " + f.getAbsolutePath());
        System.out.println("Writeable: " + f.canWrite());
        System.out.println("Readable: " + f.canRead());

        try {
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
    }

    public static void deleteFileContents(String filePath) throws IOException {
        File f = new File(filePath);
        try {
            f.delete();
            createFile();
        } catch (IOException e) {
            System.out.println("An error has occurred :( ");
        }
    }

    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        deleteFileContents(filePath);
        tasks = TaskList.getTasks();
        for (Task t : tasks) {
            fw.write(t.toString() + "\n");
        }
        fw.close();
    }
}
