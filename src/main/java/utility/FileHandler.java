package utility;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import task.Task;

public class FileHandler {
    final static String DIR_PATH = "./storage";
    final static String FILE_PATH = "./storage/genesis_data.txt";

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
}
