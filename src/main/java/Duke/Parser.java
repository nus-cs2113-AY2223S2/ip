package Duke;

import Duke.*;

import java.util.Objects;

import static Duke.TaskList.tasks;
import static Duke.Ui.invalidInputReponse;
import static InputCheckingPackage.InputChecking.*;
import static InputCheckingPackage.InputChecking.checkTodoInput;

public class Parser {

    public static String command;

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
                TaskList.DeleteTask(command);
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


        } else {
            invalidInputReponse();
        }

    }


}
