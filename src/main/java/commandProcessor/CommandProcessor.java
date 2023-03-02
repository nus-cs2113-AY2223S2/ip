package commandProcessor;

import exceptions.DukeException;
import task.TaskList;
import static storage.TaskStorage.writeSaveData;
import static task.TaskList.*;
import static ui.UI.bye;

/**
 * This class processes all the command inputs from the user
 */
public class CommandProcessor {
    private static String ERROR_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static boolean isRunning = true;

    public static void setIsRunning(boolean running) {
        isRunning = running;
    }

    public static boolean getIsRunningStatus() {
        return isRunning;
    }

    /**
     * This method reads the command given by the user and perform its respective command tasks.
     * The input parameter is the task description given by the user and it is used to create the new task.
     * The tasks parameter is the task list used for storing the added tasks.
     * @param input
     * @param command
     * @param tasks
     * @throws DukeException on wrong input of command
     */
    public static void runCommand(String input, String command, TaskList tasks) throws DukeException {
        if (command.equalsIgnoreCase("bye")) {
            bye();
            setIsRunning(false);
        } else if (command.equalsIgnoreCase("todo")) {
            addTodo(input);
        } else if (command.equalsIgnoreCase("deadline")) {
            addDeadline(input);
        } else if (command.equalsIgnoreCase("event")) {
            addEvent(input);
        } else if (command.equalsIgnoreCase("list")) {
            printList();
        } else if (command.equalsIgnoreCase("mark")) {
            int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;
            markTask(taskIdx);
        } else if (command.equalsIgnoreCase("unmark")) {
            int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;
            unmarkTask(taskIdx);
        } else if (command.equalsIgnoreCase("find")) {
            findTask(input.split(" ")[1]);
        } else if (command.equalsIgnoreCase("delete")) {
            int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;
            deleteTask(taskIdx);
        } else {
            throw new DukeException(ERROR_MESSAGE);
        }
        writeSaveData(tasks);
    }
}
