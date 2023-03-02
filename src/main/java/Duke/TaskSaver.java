package Duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class TaskSaver {
    static File txtFile = new File("duke.txt");
    static FileWriter FW;
    static Scanner SC;

    static void setUpReadWrite() {
        try {
            txtFile = new File("duke.txt");
            FW = new FileWriter(txtFile, true);
            SC = new Scanner(txtFile);
        } catch (IOException e) {
            System.out.println("Error Occurred");
            e.printStackTrace();
        }
    }

    static LinkedList<Task> loadTasks() {
        setUpReadWrite();
        LinkedList<Task> tasks = new LinkedList<Task>();
        while (SC.hasNextLine()) {
            String[] taskInfo = SC.nextLine().split(" ");
            Task task = TaskCreator.createSavedTask(taskInfo);
            tasks.add(task);
        }
        return tasks;
    }

    static void addTask(int num, Task newTask) {
        try {
            String command = TaskToStringConverter.convertTaskToCommandString(newTask);
            FW = new FileWriter("duke.txt", true);
            FW.append(command + '\n');
            FW.close();
        } catch (IOException e) {
            System.out.println("Error Occurred");
            e.printStackTrace();
        }
    }

    static void updateTask(String taskList) {
        try {
            FW = new FileWriter("duke.txt");
            FW.write(taskList);
            FW.close();
        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        }
    }

}
