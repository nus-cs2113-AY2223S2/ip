package grandduke.task;

import java.util.ArrayList;

import grandduke.command.Io;
import grandduke.command.Parser;
import grandduke.exception.GrandException;
import grandduke.exception.OutOfBoundsException;
import grandduke.exception.delete.DeleteFormatException;
import grandduke.exception.find.FindEmptyDescException;
import grandduke.exception.mark.MarkFormatException;
import grandduke.exception.mark.MarkMissingDescriptionException;

public abstract class TaskList {
    // ArrayList used as a list for tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Getter for ArrayList
     * @return
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Creates and adds a new task into the tasks ArrayList
     * @param input the description of the new task to be added
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
     * Deletes a task at a index specified by the user in the tasklist
     * @param input the input by the user that specifies the index
     */
    public static void deleteTask(String index) throws OutOfBoundsException, DeleteFormatException {
        if (index.equals("")) {
            throw new DeleteFormatException();
        }

        if (!index.matches("\\d+")) {
            throw new DeleteFormatException();
        }

        int taskNum = Integer.parseInt(index) - 1;

        if (taskNum >= tasks.size()) {
            throw new OutOfBoundsException();
        }

        Io.printOutput("Alright. I've removed this task:");
        Io.printOutput("  " + tasks.get(taskNum).getTaskPrint());
        tasks.remove(taskNum);
        Io.printOutput("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Finds tasks in the task list that matches the keyword specified by the user
     * @param input the input by the user that specifies the keyword
     */
    public static void findTasks(String input) throws FindEmptyDescException {
        if (input.equals(Io.EMPTY_COMMAND)) {
            throw new FindEmptyDescException();
        }

        String lowerCaseInput = input.toLowerCase();
        boolean isFound = false;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskDesc().toLowerCase().contains(lowerCaseInput)) {
                if (!isFound) {
                    Io.printOutput("Here are the matching tasks in your list:");
                }

                Io.printOutput(Integer.toString(i + 1) + ". " + tasks.get(i).getTaskPrint());
                isFound = true;
            }
        }

        if (!isFound) {
            Io.printOutput("No matching tasks found!");
        }
    }

    /**
     * loads a task from the save file into the task list
     * @param loadString the string to be loaded
     * @throws GrandException if the string is not in the correct format
     */
    public static void loadTask(String loadString) throws GrandException {
        Task newTask;

        String[] loadStringArray = loadString.split("\\|");
        String type = loadStringArray[0].trim();
        Boolean isDone = loadStringArray[1].trim().equals("1");
        String input = loadStringArray[2].trim();

        try {
            newTask = Parser.parseNewTask(input, type);
        } catch (GrandException e) {
            throw e;
        }

        newTask.markDone(isDone, true);
        tasks.add(newTask);
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
     * @param input the input by the user that specifies the index
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
        tasks.get(taskNum).markDone(isDone, false);
    }

    /**
     * Returns the size of the task list
     * @return the size of the task list
     */
    public static int getTaskListSize() {
        return tasks.size();
    }
}
