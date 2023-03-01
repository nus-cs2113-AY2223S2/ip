package commandParser;

import exceptions.DukeException;
import task.TaskList;
import static storage.TaskStorage.writeSaveData;
import static task.TaskList.*;
import static ui.UI.bye;

public class ParseCommand {
    private static String errorMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static boolean isRunning = true;

    public static void setisRunning(boolean running) {
        isRunning = running;
    }

    public static boolean getisRunningStatus() {
        return isRunning;
    }

    public static void runCommand(String input, String command, TaskList tasks) throws DukeException {
        if (command.equalsIgnoreCase("bye")) {
            bye();
            setisRunning(false);
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
        }
        else if (command.equalsIgnoreCase("delete")) {
            int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;
            deleteTask(taskIdx);
        } else {
            throw new DukeException(errorMessage);
        }
        writeSaveData(tasks);
    }
}
