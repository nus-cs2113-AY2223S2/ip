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
        String[] stringArray = command.split(" ",2);
        String keyword = stringArray[0];
        switch (keyword) {

        case "list" :
            TaskList.printTaskList();
            break;

        case "unmark":
        case "mark":
        case "delete":
            boolean isValidDeleteInput = checkNumericInput(command, tasks.size());
            if (isValidDeleteInput) {
                TaskList.numericTaskAction(command,tasks.size());
            }
            break;

        case "deadline":
            boolean isValidDeadlineInput = checkDeadlineInput(command);
            if (isValidDeadlineInput) {
                TaskList.createDeadline(command);
            }
            break;


        case "event":
            boolean isValidEventInput = checkEventInput(command);
            if (isValidEventInput) {
                TaskList.createEvent(command);
            }
            break;


        case "todo":
            boolean isValidTodoInput = checkTodoInput(command);
            if (isValidTodoInput) {
                TaskList.createTodo(command);
            }
            break;


        case "find":
            boolean isValidFindInput = checkFindInput(command);
            if (isValidFindInput) {
                TaskList.findTask(command);
            }
            break;

        case "bye":
            System.out.println("Saving your tasks now!");
            break;

        default:
            invalidInputReponse();
        }


    }


}
