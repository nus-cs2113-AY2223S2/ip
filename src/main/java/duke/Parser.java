package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkTaskCommand;
import duke.command.UnmarkTaskCommand;
import duke.exception.EmptyDescriptionException;

public class Parser {
    public static Command getCommand(String input, UI ui) {
        String[] taskDetails = input.split(" ", 2);

        try {
            return parseTaskDetails(taskDetails[0], taskDetails, ui);
        } catch (EmptyDescriptionException e) {
            ui.printEmptyDescriptionError();
        }
        return null;
    }


    public static Command parseTaskDetails(String keyword, String[] taskDetails, UI ui) throws EmptyDescriptionException {
        switch (keyword) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        default:
        }

        if (taskDetails.length != 2) {
            throw new EmptyDescriptionException();
        }

        switch (keyword) {
        case "mark":
        case "unmark":
            return parseMarkTaskStatus(taskDetails[1], keyword, ui);
        case "delete":
            return parseDeleteTask(taskDetails[1], ui);
        case "todo":
            return parseTodo(taskDetails[1], ui);
        case "deadline":
            return parseDeadline(taskDetails[1], ui);
        case "event":
            return parseEvent(taskDetails[1], ui);
        case "find":
            return parseFind(taskDetails[1], ui);
        default:
            ui.printInvalidMessage();
        }
        return null;
    }

    public static Command parseMarkTaskStatus(String taskDetail, String keyword, UI ui) {
        try {
            int taskNumber = Integer.parseInt(taskDetail);
            if (keyword.equals("mark")) {
                return new MarkTaskCommand((taskNumber));
            } else if (keyword.equals("unmark")) {
                return new UnmarkTaskCommand((taskNumber));
            }
        } catch (NumberFormatException e) {
            ui.printTaskNumberWarning();

        }
        return null;
    }

    public static DeleteCommand parseDeleteTask(String taskDetail, UI ui) {
        try {
            int taskNumberToDelete = Integer.parseInt(taskDetail);
            return new DeleteCommand((taskNumberToDelete));
        } catch (NumberFormatException e) {
            ui.printTaskNumberWarning();
            return null;
        }
    }

    public static AddTodoCommand parseTodo(String taskTodo, UI ui) {
        String taskTodoTrimmed = taskTodo.trim();
        if (!taskTodoTrimmed.isEmpty()) {
            return new AddTodoCommand((taskTodoTrimmed));
        }
        ui.printEmptyTodo();
        return null;
    }

    public static AddDeadlineCommand parseDeadline(String details, UI ui) {
        try {
            String[] components = details.split(" /by");
            return new AddDeadlineCommand(components[0], components[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printInvalidDeadline();
            return null;
        }
    }

    public static AddEventCommand parseEvent(String details, UI ui) {
        try {
            String[] components = details.split(" /from | /to ");
            return new AddEventCommand(components[0], components[1], components[2]);

        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printInvalidEvent();
            return null;
        }
    }

    public static FindCommand parseFind(String details, UI ui) {
        String wordToFind = details.trim();

        if (!wordToFind.isEmpty()) {
            return new FindCommand(wordToFind);
        }

        ui.printMissingWord();
        return null;
    }
}
