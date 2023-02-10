package utility;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import exception.GenesisException;

public class FileHandler {
    final static String DIR_PATH = "./store";
    final static String FILE_PATH = "./store/genesis.txt";

    public static void saveToFile(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(Paths.get(DIR_PATH));
        File file = new File(FILE_PATH);
        FileWriter fw = new FileWriter(file);

        for (Task task : tasks) {
            fw.write(task.toString() + '\n');
        }

        fw.flush();
        fw.close();
    }

    public static ArrayList<Task> loadFromFile() throws IOException, IndexOutOfBoundsException, GenesisException {

        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(FILE_PATH);
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\n");

        while (sc.hasNext()) {
            String entry = sc.next();
            String[] contentArr = entry.split(" \\| ");
            switch (contentArr[0]) {
            case "T":
                Todo todo = new Todo(contentArr[2], Boolean.parseBoolean(contentArr[1]));
                tasks.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(contentArr[2], contentArr[3], Boolean.parseBoolean(contentArr[1]));
                tasks.add(deadline);
                break;
            case "E":
                Event event = new Event(contentArr[2], contentArr[3], contentArr[4], Boolean.parseBoolean(contentArr[1]));
                tasks.add(event);
                break;
            default:
                throw new GenesisException("Type does not exist");
            }
        }

        sc.close();

        return tasks;
    }
}
