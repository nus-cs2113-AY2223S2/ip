package grandduke.task;

import java.util.ArrayList;

import grandduke.command.Io;

public class TaskList {
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
    public static void addTask(String input) {
        Task newTask = new Task(input);
        tasks.add(newTask);
        Io.printOutput("added: " + input);
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
     * mark a task at a index specified by the user in the tasklist as done
     * 
     * @param input
     *            the input by the user that specifies the index
     */
    public static void markTask(String input) {
        String[] inputList = input.split(" ");
        int taskNum = Integer.parseInt(inputList[1]) - 1;

        if (taskNum < tasks.size()) {
            tasks.get(taskNum).markDone();
        } else {
            Io.printOutput("Task number exceeds list size");
        }
    }

    /**
     * mark a task at a index specified by the user in the tasklist as not done
     * 
     * @param input
     *            the input by the user that specifies the index
     */
    public static void unmarkTask(String input) {
        String[] inputList = input.split(" ");
        int taskNum = Integer.parseInt(inputList[1]) - 1;

        if (taskNum < tasks.size()) {
            tasks.get(taskNum).markNotDone();
        } else {
            Io.printOutput("Task number exceeds list size");
        }
    }

}
