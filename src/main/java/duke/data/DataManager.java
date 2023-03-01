package duke.data;
import duke.task.Task;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataManager {

    protected final String filePath;
    protected final ArrayList<Task> tasks;

    public DataManager(String filePath, ArrayList<Task> tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
    }

    public void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task task : this.tasks) {
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.close();
    }

/*    public void loadFile() throws IOException {
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine())
            if (scanner.nextLine().startsWith([E]){
                list.add
            }
    }*/

    public void createFile() throws IOException {
        File file = new File(this.filePath);
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        file.createNewFile();
    }

/*
    public void writeToFileWithErrorHandler() throws IOException {
        try {
*/
/*            loadFile();*//*

        } catch (FileNotFoundException e) {
            System.out.println("Meow! Something went wrong: " + e.getMessage());
            try {
                createFile();
            } catch (IOException error){
                System.out.println("Meow! Failed to create a file: " + e.getMessage());
            }
            System.out.println("File Created!");
        }
    }
    */
}

