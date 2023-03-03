package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private static final String filepath = "./data/duke.txt";

    /**
     * Identify the type of task in the file, split the line into the
     * correct variables of description, status and start, end date and return
     * the Task with the required information
     *
     * @param description name of task
     * @return taskToAdd the Task identified to be put into the ArrayList of Tasks
     */
    public static Task addTask(String description) {
        String[] taskDescription;
        taskDescription = description.split("|", 3);
        Task taskToAdd;
        switch (taskDescription[0]) {
        case "T":
            taskToAdd = new Todo(taskDescription[2]);
            if (taskDescription[1].equals("1")) {
                taskToAdd.setDone();
            } else {
                taskToAdd.setUndone();
            }
            break;
        case "D":
            String[] deadlineDescription = taskDescription[2].split("|", 2);
            taskToAdd = new Deadline(deadlineDescription[0], deadlineDescription[1]);
            if (taskDescription[1].equals("1")) {
                taskToAdd.setDone();
            } else {
                taskToAdd.setUndone();
            }
            break;
        case "E":
            String[] eventDescription = taskDescription[2].split("|", 2);
            String dates[] = eventDescription[1].split("-");
            String start = dates[0];
            String end = dates[1];
            taskToAdd = new Event(eventDescription[0], start, end);
            if (taskDescription[1].equals("1")) {
                taskToAdd.setDone();
            } else {
                taskToAdd.setUndone();
            }
            break;
        default:
            taskToAdd = null;
            break;
        }
        return taskToAdd;
    }

    /**
     * Read a file from the filepath and creates a new file if a current file does not exist
     *
     * @return an array of Tasks that stores the tasks in the file
     * @throws IOException when file does not exist
     */
    public static ArrayList<Task> getData() throws IOException {
        ArrayList<Task> currentList = new ArrayList<>();
        File f = new File(filepath);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdir();
        }
        if (f.exists()) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String description = s.nextLine();
                currentList.add(addTask(description));
            }
        }
        return currentList;
    }

    /**
     * Identifies whether the task is done or undone
     * If the task is done, return "1" but if the task is not done, return "0"
     *
     * @param task the task that we are currently interested in
     * @return a string of either a "0" or "1"
     */
    public static String getNumberIcon(Task task) {
        if (task.getStatusIcon().equals("X")) {
            return "1";
        }
        return "0";
    }

    /**
     * Convert the information of the task into the correct format that will be stored in the file
     * The format tells us the type of task, the status of it and the name of the task, split with "|"
     *
     * @param task
     * @return a String of the task in the correct format
     * @throws NullPointerException
     */
    public static String formatTask(Task task) throws NullPointerException {
        String text = null;
        if (task.getType().equals("todo")) {
            text = "T" + " | " + getNumberIcon(task) + " | " + task.getDescription();
        }
        if (task.getType().equals("deadline")) {
            text = "D" + " | " + getNumberIcon(task) + " | " + task.getDescription() + " | " + task.getEnd();
        }
        if (task.getType().equals("event")) {
            text = "E" + " | " + getNumberIcon(task) + " | " + task.getDescription() + " | " + task.getStart() + "-" + task.getEnd();
        }
        return text;
    }

    /**
     * Write to the file specified by the filepath when there are changes made to the list of tasks
     *
     * @param tasks an array of tasks that will be added to the file
     * @throws IOException when file does not exist
     * @throws NullPointerException
     */
    public static void updateFile(ArrayList<Task> tasks) throws IOException, NullPointerException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filepath);
            for (Task i : tasks) {
                String textToAdd = formatTask(i);
                fw.write(textToAdd);
            }
        } finally {
            fw.close();
        }
    }
}
