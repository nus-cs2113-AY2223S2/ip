package grandduke.task;

import java.util.ArrayList;

import grandduke.command.Io;
import grandduke.command.Parser;
import grandduke.exception.GrandException;
import grandduke.exception.MarkFormatException;
import grandduke.exception.OutOfBoundsException;
import grandduke.exception.MarkMissingDescriptionException;

public abstract class TaskList {
    // ArrayList used as a list for tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Getter for ArrayList
     * 
     * @return
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Creates and adds a new task into the tasks ArrayList
     * 
     * @param input
     *            the description of the new task to be added
     */
    public static void addTask(String input, String type) throws GrandException {
        Task newTask;

        try {
            newTask = Parser.parseNewTask(input, type);
        } catch (GrandException e) {
            throw e;
        }

        tasks.add(newTask);
        Io.printOutput("Got it. I've added this task:");
        Io.printOutput("  " + newTask.getTaskPrint());
        Io.printOutput("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints the tasks in the task list in a numbered and orderly fashion
     */
    public static void printTaskList() {
        for (int i = 0; i < tasks.size(); i++) {
            Io.printOutput(Integer.toString(i + 1) + ". " + tasks.get(i).getTaskPrint());
        }
    }

    /**
     * mark a task at a index specified by the user in the tasklist as done/undone
     * 
     * @param input
     *            the input by the user that specifies the index
     */
    public static void markTask(String index, Boolean isDone)
            throws OutOfBoundsException, MarkMissingDescriptionException, MarkFormatException {
        if (index.equals("")) {
            throw new MarkMissingDescriptionException();
        }

        if (!index.matches("\\d+")) {
            throw new MarkFormatException();
        }

        int taskNum = Integer.parseInt(index) - 1;

        if (taskNum >= tasks.size()) {
            throw new OutOfBoundsException();
        }
        tasks.get(taskNum).markDone(isDone);
    }

}
