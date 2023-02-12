package duke.command;

import duke.error.DukeException;
import duke.error.ErrorTypes;
import duke.error.ThrowError;
import duke.output.Printer;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Command {
    public static void evaluate(String input, Task[] tasks) {
        try {
            String[] arrayOfInput = input.split(" ");
            if (input.equals(CommandWords.LIST.COMMAND)) {
                Printer.listTasks(tasks);
            } else if (CheckMarkUnmark.isMark(arrayOfInput)) {
                // if command is "mark <int>"
                markTask(tasks, arrayOfInput);
            } else if (CheckMarkUnmark.isUnmark(arrayOfInput)) {
                // if command is "unmark <int>"
                unmarkTask(tasks, arrayOfInput);
            } else {
                // command is to add task
                decideTaskGroup(input, tasks, arrayOfInput);
            }
        } catch (DukeException e) {
            Printer.endLine();
        }
    }

    public static void markTask(Task[] tasks, String[] arrayOfInput) {
        tasks[Integer.parseInt(arrayOfInput[1]) - 1].markAsDone();
        Printer.markOrUnmark(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
    }

    public static void unmarkTask(Task[] tasks, String[] arrayOfInput) {
        tasks[Integer.parseInt(arrayOfInput[1]) - 1].markAsNotDone();
        Printer.markOrUnmark(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
    }

    public static void decideTaskGroup(String input, Task[] tasks, String[] arrayOfInput) throws DukeException {
        boolean isInputTodo = arrayOfInput[0].equals(CommandWords.TODO.COMMAND);
        boolean isInputDeadline = arrayOfInput[0].equals(CommandWords.DEADLINE.COMMAND);
        boolean isInputEvent = arrayOfInput[0].equals(CommandWords.EVENT.COMMAND);
        if (isInputTodo) {
            String[] todoTaskNameArray = input.split(" ", 2);
            CheckTodo.checkValidTodo(todoTaskNameArray);
            addTodoTask(tasks, todoTaskNameArray);
        } else if (isInputDeadline) {
            CheckDeadline.checkValidDeadline(input, arrayOfInput);
            addDeadlineTask(tasks, input);
        } else if (isInputEvent) {
            CheckEvent.checkValidEvent(input, arrayOfInput);
            addEventTask(tasks, input);
        } else {
            // if input doesn't contain any keywords
            ThrowError.throwError(ErrorTypes.INVALID_INPUT.ERROR_TYPE);
        }
        Printer.echoAddTasks(tasks);
        Task.incrementTotalTasks();
    }

    public static void addTodoTask(Task[] tasks, String[] input) {
        tasks[Task.totalTasks] = new Todo(input[1]);
    }

    public static void addDeadlineTask(Task[] tasks, String input) {
        String[] commandInformation = input.split(" ", 2);
        String[] taskNameAndDate = commandInformation[1].split("/by", 2);
        tasks[Task.totalTasks] = new Deadline(taskNameAndDate[0].trim(), taskNameAndDate[1].trim());
    }

    public static void addEventTask(Task[] tasks, String input) {
        String taskNameInformation = input.split(" ", 2)[1];
        String[] taskNameAndDate = taskNameInformation.split("/from", 2); // name fromTo
        String[] fromAndTo = taskNameAndDate[1].split("/to", 2); // from to
        tasks[Task.totalTasks] = new Event(taskNameAndDate[0].trim(), fromAndTo[0].trim(), fromAndTo[1].trim());
    }
}