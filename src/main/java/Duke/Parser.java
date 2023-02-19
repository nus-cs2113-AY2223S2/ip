package Duke;

import static Duke.TaskList.tasks;
import static Duke.Ui.invalidInputReponse;
import static Duke.InputCheckingPackage.InputChecking.*;
import static Duke.InputCheckingPackage.InputChecking.checkTodoInput;

/**
 * Parser parses the input from the user to determine what method to run next if input is valid
 */
public class Parser {

    public static String command;

    /**
     * To determine the next method to run based on the user input
     *
     * @param command User input command that determines what function Duke will run
     */

    public static void respondToInput(String command) {
        if (command.contains("list")) {
            TaskList.printTaskList();

        } else if (command.contains("unmark")) {
            boolean isValidUnmarkInput = checkUnmarkInput(command, tasks.size());
            if (isValidUnmarkInput) {
                TaskList.unmarkTask(command, tasks.size());
            }


        } else if (command.contains("mark")) {
            boolean isValidMarkInput = checkMarkInput(command, tasks.size());
            if (isValidMarkInput) {
                TaskList.markTask(command, tasks.size());
            }

        } else if (command.contains("delete")) {
            boolean isValidDeleteInput = checkDeleteInput(command, tasks.size());

            if (isValidDeleteInput) {
                TaskList.deleteTask(command);
            }

        } else if (command.contains("deadline")) {
            boolean isValidDeadlineInput = checkDeadlineInput(command);
            if (isValidDeadlineInput) {
                TaskList.createDeadline(command);
            }


        } else if (command.contains("event")) {
            boolean isValidEventInput = checkEventInput(command);
            if (isValidEventInput) {
                TaskList.createEvent(command);
            }


        } else if (command.contains("todo")) {
            boolean isValidTodoInput = checkTodoInput(command);
            if (isValidTodoInput) {
                TaskList.createTodo(command);
            }


        } else if (command.contains("find")) {
            boolean isValidFindInput = checkFindInput(command);
            if (isValidFindInput) {
                TaskList.findTask(command);
            }

        } else if (command.contains("bye")) {
            System.out.println("Saving your tasks now!");
        } else {
            invalidInputReponse();
        }

    }


}
