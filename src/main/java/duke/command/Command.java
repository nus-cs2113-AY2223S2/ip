package duke.command;

import duke.error.DukeException;
import duke.error.ErrorTypes;
import duke.error.ThrowError;
import duke.output.Printer;
import duke.task.Task;

import java.util.ArrayList;

public class Command {
    public static void evaluate(String input, ArrayList<Task> tasks) {
        try {
            String[] arrayOfInput = input.split(" ");
            if (input.equals(CommandWords.LIST.COMMAND)) {
                Printer.listTasks(tasks);
            } else if (CheckMarkUnmark.isMark(arrayOfInput)) {
                // if command is "mark <int>"
                CommandActions.markTask(tasks, arrayOfInput);
            } else if (CheckMarkUnmark.isUnmark(arrayOfInput)) {
                // if command is "unmark <int>"
                CommandActions.unmarkTask(tasks, arrayOfInput);
            } else if (CheckDelete.isDelete(arrayOfInput)) {
                // if command is "delete <int>"
                CommandActions.deleteTask(tasks, arrayOfInput);
            } else {
                // command is to add task
                decideTaskType(input, tasks, arrayOfInput);
            }
        } catch (DukeException e) {
            Printer.endLine();
        }
    }

    public static void decideTaskType(String input, ArrayList<Task> tasks, String[] arrayOfInput) throws DukeException {
        boolean isInputTodo = arrayOfInput[0].equals(CommandWords.TODO.COMMAND);
        boolean isInputDeadline = arrayOfInput[0].equals(CommandWords.DEADLINE.COMMAND);
        boolean isInputEvent = arrayOfInput[0].equals(CommandWords.EVENT.COMMAND);
        if (isInputTodo) {
            String[] todoTaskNameArray = input.split(" ", 2);
            VerifyInput.checkValidTodo(todoTaskNameArray);
            CommandActions.addTodoTask(tasks, todoTaskNameArray);
        } else if (isInputDeadline) {
            VerifyInput.checkValidDeadline(input, arrayOfInput);
            CommandActions.addDeadlineTask(tasks, input);
        } else if (isInputEvent) {
            VerifyInput.checkValidEvent(input, arrayOfInput);
            CommandActions.addEventTask(tasks, input);
        } else {
            // if input doesn't contain any keywords
            ThrowError.throwError(ErrorTypes.INVALID_INPUT.ERROR_TYPE);
        }
        Printer.echoAddTasks(tasks);
        Task.incrementTotalTasks();
    }
}