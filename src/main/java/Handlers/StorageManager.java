package Handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StorageManager {

    private static final String FILE_PATH = "./data/duke.txt";

    public static void loadFileContents() {

        // Create data folder if it does not exist
        new File("./data").mkdir();

        File f = new File(FILE_PATH);

        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file");
            }
        }

        try {
            writeToTaskManager(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void saveFileContents() {

        try {
            writeToFile();
        } catch (Exception e) {
            System.out.println("Error writing to file");
        }
    }

    private static void writeToTaskManager(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String nextLine = s.nextLine();
            TaskManager.readTaskFromFile(nextLine);
        }
        s.close();
    }

    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < TaskManager.getTaskCount(); i++) {
            fw.write(TaskManager.getTaskList().get(i).describeTaskForFile() + "\n");
        }
        fw.close();
    }
}
