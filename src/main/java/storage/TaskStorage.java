package storage;

import task.Task;
import task.TaskList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import static storage.DataParser.*;
import static task.TaskList.*;
import static ui.UI.*;

/**
 * This class manages the save file that stores tasks data into the computer disk
 */
public class TaskStorage {
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
            writeSaveDataERROR();
        }
    }

    public static void loadSaveData() {
        try {
            File saveData = new File(dataPath);
            greet();
            if (!saveData.createNewFile()) {
                Scanner readData = new Scanner(saveData);
                while (readData.hasNext()) {
                    String data = readData.nextLine();
                    String taskType = data.split("|", 5)[0];
                    if (taskType.equals("T")) {
                        parseTodo(data);
                    } else if (taskType.equals("D")) {
                        parseDeadline(data);
                    } else if (taskType.equals("E")) {
                        parseEvent(data);
                    }
                }
                loadSaveList();
            }
        } catch (IOException e) {
            loadSaveDataERROR();
        }
    }
}
