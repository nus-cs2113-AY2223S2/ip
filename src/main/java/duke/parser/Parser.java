package duke.parser;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * Represents the class that deals with making sense of the user command.
 */
public class Parser {


    /**
     * Get user command and process accordingly.
     */
    public static void getCommand() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while (true) {
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                Ui.printMessage(Ui.CommandType.LIST);
            } else if (userInput.startsWith("find")) {
                findTask(userInput);
            } else if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                markTask(userInput);
            } else if (userInput.startsWith("delete")) {
                deleteTask(userInput);
            } else if (userInput.startsWith("todo") || userInput.startsWith("event")
                    || userInput.startsWith("deadline")){
                addTask(userInput);
            } else {
                Ui.printMessage(Ui.CommandType.INDEXOUTOFBOUNDS);
            }
            Storage.write();
            userInput = in.nextLine();
        }
        in.close();
    }

    /**
     * Add a task to the list of tasks.
     * @param userInput The command given by user to add task.
     *                  Should include task type, description and
     *                  necessary information needed by each type of task.
     */
    private static void addTask(String userInput) {
        try {
            TaskList.addTask(userInput);
        } catch (DukeException e) {
            Ui.printMessage(Ui.CommandType.DUKEEXCEPTION);
        } catch (IndexOutOfBoundsException e) {
            Ui.printMessage(Ui.CommandType.INDEXOUTOFBOUNDS);
        }
    }

    /**
     * Deletes a task from the list of tasks.
     * @param userInput The command given by user to delete task.
     *                  Should include the index of the task.
     */
    private static void deleteTask(String userInput) {
        try {
            TaskList.deleteTask(userInput);
        } catch (IndexOutOfBoundsException e) {
            Ui.printMessage(Ui.CommandType.INDEXOUTOFBOUNDS);
        }
    }

    /**
     * Marks a task as done or undone from the list of tasks.
     * @param userInput The command given by user to mark task.
     *                  Should include the index of the task.
     */
    private static void markTask(String userInput) {
        String[] command = userInput.split(" ");
        try {
            TaskList.markValidTask(command);
            Integer taskIndex = Integer.parseInt(command[1]);
            if (command[0].equals("mark")) {
                Ui.printMessage(TaskList.getTask(taskIndex), Ui.CommandType.MARK);
            } else {
                Ui.printMessage(TaskList.getTask(taskIndex), Ui.CommandType.UNMARK);
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printMessage(Ui.CommandType.INDEXOUTOFBOUNDS);
        } catch (NumberFormatException e) {
            Ui.printMessage(Ui.CommandType.NUMBERFORMAT);
        }
    }

    /**
     * Finds tasks containing the keywords that the user inputs.
     * @param userInput The command given by user to find tasks.
     *                  Should include keywords of the task that
     *                  the user wants to search for.
     */
    public static void findTask(String userInput) {
        try {
            String query = userInput.substring(5);
            query.trim();
            Ui.printMessage(query, Ui.CommandType.FIND);
        } catch (IndexOutOfBoundsException e) {
            Ui.printMessage(Ui.CommandType.INDEXOUTOFBOUNDS);
        }
    }
}
