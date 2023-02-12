package inu.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import inu.task.Task;
import inu.task.TaskList;
import inu.parser.Parser;


public class StorageFile {

    public static final String DEFAULT_STORAGE_FILEPATH = "./src/inu.txt";

    private static File f = new File(DEFAULT_STORAGE_FILEPATH);

    public static void loadTaskListFromFile(TaskList taskList) {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                 String fileString = s.nextLine();
                 Task task = Parser.decodeString(fileString);
                 taskList.addTask(task);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveTaskListToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(DEFAULT_STORAGE_FILEPATH);
            for (int i = 0; i < taskList.getTaskListSize(); i++) {
                Task currentTask = taskList.getTask(i);
                String fileEntry = currentTask.encodeTask() + System.lineSeparator();
                fw.write(fileEntry);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
