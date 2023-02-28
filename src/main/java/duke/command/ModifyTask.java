package duke.command;

import duke.Duke;
import duke.Parser;
import duke.task.Task;

import java.util.ArrayList;

public class ModifyTask {

    public static void markTask(String userCommand, ArrayList<Task> storedUserTasks) {
        int taskIndex = Parser.getIndex(userCommand, "task");
        storedUserTasks.get(taskIndex).isDone = true;
        Display.displayTaskProgress("mark", taskIndex, storedUserTasks);
    }

    public static void unmarkTask(String userCommand, ArrayList<Task> storedUserTasks) {
        int taskIndex = Parser.getIndex(userCommand, "task");
        storedUserTasks.get(taskIndex).isDone = false;
        Display.displayTaskProgress("unmark", taskIndex, storedUserTasks);
    }

    public static void deleteTask(String userCommand, ArrayList<Task> storedUserTasks) {
        int taskIndex = Parser.getIndex(userCommand, "task");
        Display.displayDeleteTask(taskIndex, storedUserTasks);
        Duke.userTextCount--;
        Display.printNumberOfTasks();
    }

}
