package duke.command;

import duke.Duke;
import duke.Parser;
import duke.task.Task;

import java.util.ArrayList;

/**
 * The ModifyTask class modifies
 * a listed task
 */
public class ModifyTask {

    /**
     * This method marks a task as done
     *
     * @param userCommand User's command
     * @param storedUserTasks ArrayList containing listed Tasks
     */
    public static void markTask(String userCommand, ArrayList<Task> storedUserTasks) {
        int taskIndex = Parser.extractIndex(userCommand, "task");
        storedUserTasks.get(taskIndex).isDone = true;
        Display.displayTaskProgress("mark", taskIndex, storedUserTasks);
    }

    /**
     * This method marks a task as not done
     *
     * @param userCommand User's command
     * @param storedUserTasks ArrayList containing listed Tasks
     */
    public static void unmarkTask(String userCommand, ArrayList<Task> storedUserTasks) {
        int taskIndex = Parser.extractIndex(userCommand, "task");
        storedUserTasks.get(taskIndex).isDone = false;
        Display.displayTaskProgress("unmark", taskIndex, storedUserTasks);
    }

    /**
     * This method deletes a task
     *
     * @param userCommand User's command
     * @param storedUserTasks ArrayList containing listed Tasks
     */
    public static void deleteTask(String userCommand, ArrayList<Task> storedUserTasks) {
        int taskIndex = Parser.extractIndex(userCommand, "task");
        Display.displayDeleteTask(taskIndex, storedUserTasks);
        Duke.userTextCount--;
        Display.printNumberOfTasks();
    }

}
