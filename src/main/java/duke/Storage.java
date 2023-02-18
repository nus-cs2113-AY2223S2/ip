package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads previous tasks fom file into the task list.
 * Saves the tasks to the file and updates the file on deletion
 * or completion of tasks.
 */
public class Storage {
    private static final String FILE_PATH = "duke.txt";
    public static ArrayList<Task> tasks;

    public Storage(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Calls loadTasks function to load the tasks from file into
     * the task list.
     * Creates new file, if file not found and catches any encountered
     * exceptions.
     *
     * @param tasks Arraylist used to store the tasks.
     */
    public static void loadData(ArrayList<Task> tasks) {
        Ui ui = new Ui();
        try {
            File file = new File(FILE_PATH);
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                loadTasks(in.nextLine(), tasks);
            }
        } catch (FileNotFoundException e) {
            File file = new File(FILE_PATH);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ui.printLine();
                System.out.println("New file cannot be created");
                ui.printLine();
            }
        }
    }

    /**
     * Loads the tasks from duke.txt file into the task list.
     *
     * @param input Stores the contents of the file line by line.
     * @param tasks Arraylist used to store the tasks.
     */
    public static void loadTasks(String input, ArrayList<Task> tasks) {
        String[] splitInput = input.split("->");
        String taskType = splitInput[0].trim();
        String status = splitInput[1].trim();
        String taskDescription = splitInput[2].trim();

        switch (taskType) {
        case "T":
            tasks.add(new Todo(taskDescription));
            break;
        case "D":
            String by = splitInput[3].trim();
            tasks.add(new Deadline(taskDescription, by));
            break;
        case "E":
            String from = splitInput[3].trim();
            String to = splitInput[4].trim();
            tasks.add(new Event(taskDescription, from, to));
            break;
        }
        if (status.equals("1")) {
            tasks.get(tasks.size() - 1).markDone();
        }
    }

    /**
     * Writes the tasks from the task list to duke.txt file.
     *
     * @param tasks Arraylist used to store the tasks.
     */
    public static void saveTask(ArrayList<Task> tasks) {
        Ui ui = new Ui();
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                String status;
                String lineToAdd = "";
                if (task.getStatusIcon().equals("X")) {
                    status = "1";
                } else {
                    status = "0";
                }
                if (task instanceof Todo) {
                    lineToAdd = "T" + " -> " + status + " -> " + task.description + "\n";
                }
                if (task instanceof Deadline) {
                    lineToAdd = "D" + " -> " + status + " -> " + task.description + " -> " + ((Deadline) task).by + "\n";
                }
                if (task instanceof Event) {
                    String time = ((Event) task).from + " -> " + ((Event) task).to;
                    lineToAdd = "E" + " -> " + status + " -> " + task.description + " -> " + time + "\n";
                }
                fw.write(lineToAdd);
            }
            fw.close();
        } catch (IOException e) {
            ui.printLine();
            System.out.println("Unable to save data");
            ui.printLine();
        }
    }
}
