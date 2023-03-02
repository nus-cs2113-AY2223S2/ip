package duke;

import duke.command.*;
import duke.exception.EmptyDescriptionException;

public class Parser {
    public static Command getCommand(String input, UI ui) {
        String[] taskDetails = input.split(" ", 2);

        try {
            return parseTaskDetails(taskDetails[0], taskDetails, ui);
        } catch (EmptyDescriptionException e) {
            System.out.println("Please specify more details about the task");
        }

        return null;
    }


    public static Command parseTaskDetails(String keyword, String[] taskDetails, UI ui) throws EmptyDescriptionException {
        switch (keyword) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        }
        if (taskDetails.length != 2) {
            throw new EmptyDescriptionException();
        }
        switch (keyword) {
        case "mark":
        case "unmark":
                return parseMarkTaskStatus(taskDetails[1], keyword);
        case "delete":
             return parseDeleteTask(taskDetails[1]);
        case "todo":
            return parseTodo(taskDetails[1]);
        case "deadline":
        return parseDeadline(taskDetails[1]);
        case "event":
           return parseEvent(taskDetails[1]);
        case "find":
            return parseFind(taskDetails[1]);
        default:
            ui.printInvalidMessage();
        }
        return null;
    }

    public static Command parseMarkTaskStatus(String taskDetail, String keyword) {
        try {
            int taskNumber = Integer.parseInt(taskDetail);
            if (keyword.equals("mark")) {
                return new MarkTaskCommand((taskNumber));
            }

            else if (keyword.equals("unmark")) {
                return new UnmarkTaskCommand((taskNumber));
            }
        } catch (NumberFormatException e) { //add error for marking OOB number
            System.out.println("Please indicate the task number to be marked as done IN PTD.");

        }
        return null;
    }

    public static DeleteCommand parseDeleteTask(String taskDetail) {
        try {
            int taskNumberToDelete = Integer.parseInt(taskDetail);
            return new DeleteCommand((taskNumberToDelete));
        } catch (NumberFormatException e) {
            System.out.println("Please indicate the task number to be deleted.");
            return null;
        }

    }

    public static AddTodoCommand parseTodo(String taskTodo) {
        String taskTodoTrimmed = taskTodo.trim();
        if (taskTodoTrimmed.isEmpty()) {
            System.out.println("The description of a todo cannot be empty.");
        }
        else {
            return new AddTodoCommand((taskTodoTrimmed));
        }
        return null;
    }

    public static AddDeadlineCommand parseDeadline(String details) {
            try {
                String[] components = details.split(" /by");
                return new AddDeadlineCommand(components[0], components[1]);
            } catch (ArrayIndexOutOfBoundsException e){ //might not even be needed
                System.out.println( "Invalid format. Remember to use '/by' to indicate the time.");
                return null;
            }
    }

    public static AddEventCommand parseEvent(String details) {
           try {
               String[] components = details.split(" /from | /to ");
               return new AddEventCommand(components[0], components[1], components[2]);

           } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect format. Specify events in the format 'EVENT /from A /to B'");
            return null;
        }

    }
    public static FindCommand parseFind(String details) {
        String wordToFind = details.trim();

        if (!wordToFind.isEmpty()) {
            return new FindCommand(wordToFind);

        }

        System.out.println("Please specify the word to be found");
        return null;
    }

}
