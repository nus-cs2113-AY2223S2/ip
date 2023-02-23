package utility;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
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
    final static String CONFIG_FILE_PATH = "src/config.properties";

    public static void saveToFile(ArrayList<Task> tasks) throws IOException {

        FileInputStream propsInput = new FileInputStream(CONFIG_FILE_PATH);
        Properties prop = new Properties();
        prop.load(propsInput);

        String filePath = prop.getProperty("GENESIS_STORE_FILE");
        String dirPath = prop.getProperty("GENESIS_STORE_DIR");

        Files.createDirectories(Paths.get(dirPath));
        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);

        for (Task task : tasks) {
            fw.write(task.toString() + '\n');
        }

        fw.flush();
        fw.close();
    }

    public static ArrayList<Task> loadFromFile() throws IOException, IndexOutOfBoundsException, GenesisException {

        ArrayList<Task> tasks = new ArrayList<>();

        FileInputStream propsInput = new FileInputStream(CONFIG_FILE_PATH);
        Properties prop = new Properties();
        prop.load(propsInput);

        String filePath = prop.getProperty("GENESIS_STORE_FILE");

        File file = new File(filePath);
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
