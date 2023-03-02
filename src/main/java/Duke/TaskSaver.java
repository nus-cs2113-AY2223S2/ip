package Duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *  Responsible for writing and reading data from the duke.txt text file, where all tasks are saved.
 *  Uses java.io classes and Scanner to read/write on the text file.
 *  Responsible for keeping the text file up to date with the Task List.
 */
public class TaskSaver {
    static File txtFile = new File("duke.txt");
    static FileWriter FW;
    static Scanner SC;

    /**
     * Sets up reader and writer for the class.
     * Ran initially when loading the tasks from the text file.
     */
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

    /**
     * Loads tasks from the text file.
     * Uses scanner to read from the file, then generate Task classes accordingly.
     *
     * @return LinkedList<Task> that contains tasks loaded from the text file.
     */
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

    /**
     * Adds new task to the text file to keep it up to date.
     *
     * @param newTask Task to be added to the text file.
     */
    static void addTask(Task newTask) {
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

    /**
     * Takes in list of current tasks as String form
     * Updates the text file according to the most recent version of tasks.
     *
     * @param taskList List of current tasks, in String form.
     */
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
