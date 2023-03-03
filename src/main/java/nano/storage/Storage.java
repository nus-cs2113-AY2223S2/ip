package nano.storage;

import nano.ui.Ui;
import nano.data.task.Deadline;
import nano.data.task.Event;
import nano.data.task.Task;
import nano.data.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final File defaultFile = new File("data/tasks.txt");
    private final File taskFile;

    public Storage() {
        taskFile = defaultFile;
    }

    public Storage(String taskFile) {
        this.taskFile = new File(taskFile);
    }

    /**
     * Reads froms task text datafile and adds tasks into a list.
     *
     * @param tasks List of tasks.
     */
    public void getUserData(ArrayList<Task> tasks){
        if (taskFile.exists()) {
            try {
                Scanner s = new Scanner(taskFile);
                while (s.hasNext()) {
                    readTaskFile(s.nextLine(),tasks);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error reading task file.");
            }
        } else {
            System.out.println("User task file not detected. Creating new task file.....");
            createTaskFile();
        }
    }

    /**
     * Processes task details from task text datafile and add task to a list.
     *
     * @param taskDetails Details of task to add.
     * @param tasks List of tasks.
     */
    private static void readTaskFile(String taskDetails, ArrayList<Task> tasks) {
        Task newTask;
        String taskName;
        if (taskDetails.charAt(taskDetails.length() - 1) == ')') {
            taskName = taskDetails.substring(taskDetails.indexOf(" ") + 1, taskDetails.lastIndexOf(" ("));
        } else {
            taskName = taskDetails.substring(taskDetails.indexOf(" ") + 1);
        }

        switch (taskDetails.substring(1, 2)) {
        case "D":
            newTask = new Deadline(taskName, taskDetails.substring(taskDetails.indexOf("by:") + 4,
                    taskDetails.lastIndexOf(')')));
            break;
        case "E":
            newTask = new Event(taskName, taskDetails.substring(taskDetails.indexOf("from:") + 5,
                    taskDetails.indexOf(", to:")).trim(),
                    taskDetails.substring(taskDetails.indexOf("to:") + 4, taskDetails.lastIndexOf(')')).trim());
            break;
        default:
            newTask = new Todo(taskName);
            break;
        }
        tasks.add(newTask);

        if (taskDetails.contains("[x]")) {
            tasks.get(tasks.size() - 1).setDone();
        }
    }

    /**
     * Creates directories and files to store task list.
     * Only created when task text datafile does not exist.
     */
    private void createTaskFile() {
        try {
            if (taskFile.getParentFile().mkdirs()) {
                System.out.println("Creating directories.....");
            } else {
                System.out.println("Unable to create directories. User data may not be saved.");
            }

            if (taskFile.createNewFile()) {
                System.out.println("Task file created!");
            } else {
                System.out.println("Unable to create task file. User data may not be saved.");
            }

        } catch (IOException e) {
            System.out.println("Error detected while creating user task file. User data may not be saved.");
        }
        Ui.printHorizontalLine();
    }

    /**
     * Saves task lists into a text datafile.
     *
     * @param tasks List of tasks.
     * @throws IOException If unable to write to text datafile.
     */
    public void saveTaskFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(taskFile);
        for (Task task : tasks) {
            fileWriter.write(task.toString() + System.lineSeparator());
        }
        fileWriter.close();
    }
}