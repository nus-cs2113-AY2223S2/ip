package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a file storing data for duke.
 * Manages the file
 */
public class TaskData {
    private File data;
    private String fileName;

    public TaskData (String fileName) {
        this.fileName = fileName;
        data = new File(fileName);
    }

    /**
     * Reads data from a file and stores them inside ArrayList tasks
     *
     * @param tasks the ArrayList storing all the tasks
     * @return tasks
     * @throws FileNotFoundException when the file cannot be found
     */
    public ArrayList<Task> readData (ArrayList<Task> tasks) throws FileNotFoundException {
        if (!data.exists()) {
            return tasks;
        }
        Scanner s = new Scanner(data);
        String task;
        String taskDescription;
        while (s.hasNext()) {
            task = s.nextLine();
            if (task.charAt(1) == 'T') {
                taskDescription = task.substring(6);
                Task tempTask = new Task(taskDescription);
                if (task.charAt(4) == 'X') {
                    tempTask.markAsDone();
                } else {
                    tempTask.markAsUndone();
                }
                tasks.add(tempTask);
            } else if (task.charAt(1) == 'D') {
                //extracts info
                taskDescription = task.substring(6, task.indexOf("(by") - 1);
                String by = task.substring(task.indexOf("(by") + 5, task.length() - 1);
                Task tempTask = new Deadline(taskDescription, by);
                if (task.charAt(4) == 'X') {
                    tempTask.markAsDone();
                } else {
                    tempTask.markAsUndone();
                }
                tasks.add(tempTask);
            } else {
                //extracts info
                taskDescription = task.substring(6, task.indexOf("(from") - 1);
                String from = task.substring(task.indexOf("(from") + 7, task.indexOf("to") -1);
                String to = task.substring(task.indexOf("to") + 4, task.length() - 1);
                Task tempTask = new Event(taskDescription, from, to);
                if (task.charAt(4) == 'X') {
                    tempTask.markAsDone();
                } else {
                    tempTask.markAsUndone();
                }
                tasks.add(tempTask);
            }
        }
        return tasks;
    }

    /**
     * Appends data of a task to the file
     * @param tasks storing all the tasks
     * @param task the task to be appended
     * @return tasks
     * @throws IOException when the file writer fails
     */
    public ArrayList<Task> writeToFile (ArrayList<Task> tasks,String task) throws IOException {
        FileWriter fw = new FileWriter(fileName, true);
        fw.write(task + System.lineSeparator());
        fw.close();
        return tasks;
    }

    /**
     * updates the file entirely based on the current tasks
     *
     * @param tasks the ArrayList storing all the tasks
     * @return tasks
     * @throws IOException when file writer fails
     */
    public ArrayList<Task> updateFile (ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        fw.write("");
        fw.close();
        FileWriter fwAppend = new FileWriter(fileName, true);
        for (Task t : tasks) {
            fwAppend.write(t.toString() + System.lineSeparator());
        }
        fwAppend.close();
        return tasks;
    }

    /**
     * Returns updated tasks from file "/duke.txt"
     * tasks is unchanged if there is no such file already existing and creates a file
     *
     * @param tasks all the tasks added
     * @param file the file storing the tasks
     * @return tasks
     */
    public static ArrayList<Task> loadData(ArrayList<Task> tasks, TaskData file) {
        try {
            file.readData(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
        return tasks;
    }

    /**
     * Appends a task to the file and returns the updated tasks
     *
     * @param tasks all the tasks added
     * @param taskToAdd the task to append to the text file
     * @param file the txt file storing the tasks
     * @return tasks
     */
    public static ArrayList<Task> addTaskToFile(ArrayList<Task> tasks, Task taskToAdd, TaskData file) {
        try {
            tasks = file.writeToFile(tasks, taskToAdd.toString());
        } catch (IOException e) {
            System.out.println("IOException!");
        }
        return tasks;
    }

    /**
     * updates the file entirely to the current tasks
     *
     * @param tasks all the tasks added
     * @param file the txt file storing the tasks
     * @return tasks
     */
    public static ArrayList<Task> updateFile(ArrayList<Task> tasks, TaskData file) {
        try {
            tasks = file.updateFile(tasks);
        } catch (IOException e) {
            System.out.println("IOException!");
        }
        return tasks;
    }

}
