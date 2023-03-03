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
    /**
     * Reads in input from the user and returns Command class that matches the user's input
     *
     * @param input Input from the user
     * @param ui    Prints error messages if the command cannot be parsed
     * @return Command class that matches the input from the user, null if there is no matching class
     */
    public static Command getCommand(String input, UI ui) {
        String[] taskDetails = input.split(" ", 2);

        try {
            return parseTaskDetails(taskDetails[0], taskDetails, ui);
        } catch (EmptyDescriptionException e) {
            ui.printEmptyDescriptionError();
        }
        return null;
    }


    /**
     * Parses the input from the user and returns the Command class that corresponds to the user's input
     *
     * @param keyword     First word in user's input
     * @param taskDetails Rest of the user's input, which contains all other details and instructions.
     * @param ui          Prints error message if the input does not match any class.
     * @return Command class that matches the input from the user, null if there is no matching class
     * @throws EmptyDescriptionException If task description is empty or command word is invalid.
     */
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

    /**
     * Parses user's command to mark a task as done or not done.
     * If the command cannot be parsed, null is returned.
     *
     * @param taskDetail The task number to be marked as done or not done.
     * @param keyword    The command to mark or unmark a task as done.
     * @param ui         Prints warning message if invalid task number is provided.
     * @return Instance of MarkTaskCommand class if the user marks a task as done,
     * Instance of UnmarkTaskCommand class if the user marks a task as not done
     */
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

    /**
     * Parses user's command to delete a task.
     * If the command cannot be parsed, null is returned.
     *
     * @param taskDetail The task number to be marked as done or not done.
     * @param ui         Prints warning message if invalid task number is provided.
     * @return Instance of DeleteCommand class when a task is deleted from the task list.
     */
    public static DeleteCommand parseDeleteTask(String taskDetail, UI ui) {
        try {
            int taskNumberToDelete = Integer.parseInt(taskDetail);
            return new DeleteCommand((taskNumberToDelete));
        } catch (NumberFormatException e) {
            ui.printTaskNumberWarning();

        }
        return null;
    }

    /**
     * Parses user's command to add a todo task.
     * If the command cannot be parsed, null is returned.
     *
     * @param taskTodo The todo task
     * @param ui       Prints warning message if task to be done is not specified.
     * @return Instance of AddTodoCommand class when a todo task is successfully added.
     */
    public static AddTodoCommand parseTodo(String taskTodo, UI ui) {
        String taskTodoTrimmed = taskTodo.trim();
        if (!taskTodoTrimmed.isEmpty()) {
            return new AddTodoCommand((taskTodoTrimmed));
        }
        ui.printEmptyTodo();
        return null;
    }

    /**
     * Parses user's command to add a deadline.
     * If the command cannot be parsed, null is returned.
     *
     * @param details The task to be completed and the timing of the deadline
     * @param ui      Prints warning message when the format of the command is invalid.
     * @return Instance of AddDeadlineCommand class when a deadline is successfully added.
     */
    public static AddDeadlineCommand parseDeadline(String details, UI ui) {
        try {
            String[] components = details.split(" /by");
            return new AddDeadlineCommand(components[0], components[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printInvalidDeadline();
            return null;
        }
    }

    /**
     * Parses user's command to add an event.
     * If the command cannot be parsed, null is returned.
     *
     * @param details The task to be completed and the duration of the event
     * @param ui      Prints warning message when the format of the command is invalid.
     * @return Instance of AddEventCommand class when an event is successfully added.
     */
    public static AddEventCommand parseEvent(String details, UI ui) {
        try {
            String[] components = details.split(" /from | /to ");
            return new AddEventCommand(components[0], components[1], components[2]);

        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printInvalidEvent();
            return null;
        }
    }

    /**
     * Parses user's command to find a list of tasks that include a certain word.
     * If the command cannot be parsed, null is returned.
     *
     * @param details The word to search the task list for.
     * @param ui      Prints warning message when no word to find is specified.
     * @return Instance of FindCommand class when a word is successfully found.
     */
    public static FindCommand parseFind(String details, UI ui) {
        String wordToFind = details.trim();

        if (!wordToFind.isEmpty()) {
            return new FindCommand(wordToFind);
        }

        ui.printMissingWord();
        return null;
    }
}
