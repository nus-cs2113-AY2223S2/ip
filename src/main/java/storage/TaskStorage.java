package storage;

import Exceptions.DukeException;
import task.Task;
import task.TaskList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import static storage.DataParser.*;
import static task.TaskList.printList;


public class TaskStorage {
    private static final String loadSaveDataERROR = "LOAD SAVE DATA ERROR";
    private static final String writeSaveDataERROR = "WRITE SAVE DATA ERROR";
    public static String dataPath;
    private static FileWriter dataFile;

    public TaskStorage(){
        this.dataPath = dataPath;
    }

    public static void writeSaveData(TaskList tasks) {
        try {
            dataFile = new FileWriter(dataPath);
            for(Task t: tasks.list) {
                String task = t.description;
                String taskType = t.getTaskType();
                String status = t.getStatusIcon();
                String data;
                if (taskType.equals("T")) {
                    data = String.format("%s|%s|%s", taskType, status, task);
                    dataFile.write(data + System.lineSeparator());
                } else if (taskType.equals("D")) {
                    data = String.format("%s|%s|%s", taskType, status, task);
                    dataFile.write(data + System.lineSeparator());
                } else if (taskType.equals("E")) {
                    data = String.format("%s|%s|%s", taskType, status, task);
                    dataFile.write(data + System.lineSeparator());
                }
            }
            dataFile.close();
        } catch (IOException e) {
            System.out.println(writeSaveDataERROR);
        }
    }

    public static void loadSaveData() {
        try {
            File saveData = new File(dataPath);
            if (!saveData.createNewFile()) {
                Scanner readData = new Scanner(saveData);
                while (readData.hasNext()) {
                    String data = readData.nextLine();
                    String taskType = data.split("|",4)[0];
                    if (taskType.equals("T")) {
                        parseTodo(data);
                    } else if (taskType.equals("D")) {
                        parseDeadline(data);
                    } else if (taskType.equals("E")) {
                        parseEvent(data);
                    }
                }
                printList();
            }
        } catch (IOException e) {
            System.out.println(loadSaveDataERROR);
        }
    }
}
