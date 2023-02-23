package duke.parser;

import duke.command.*;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidInputIDException;
import duke.exceptions.InvalidTaskFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskEnum;
import duke.tasks.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private static final String CHAR_SPACE = " ";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_SAVE = "save";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_UNMARK = "unmark";

    private static int getID(Scanner scanner) throws InvalidInputIDException {
        if (!scanner.hasNextInt()) {
            throw new InvalidInputIDException();
        }
        return scanner.nextInt();
    }

    private static Task getTaskFromInput(Scanner input, TaskEnum type) throws Exception {
        // validate input
        if (!input.hasNextLine()) {
            throw new InvalidTaskFormatException(type);
        }
        String taskDetails = input.nextLine().trim();
        if (!ToDo.isValidInput(taskDetails) || taskDetails.isEmpty()) {
            throw new InvalidTaskFormatException(type);
        }

        Task task;
        ArrayList<String> details;
        switch (type) {
        case TODO:
            details = ToDo.convertInputIntoDetails(taskDetails);
            task = new ToDo(details);
            break;
        case EVENT:
            details = Event.convertInputIntoDetails(taskDetails);
            task = new Event(details);
            break;
        case DEADLINE:
            details = Deadline.convertInputIntoDetails(taskDetails);
            task = new Deadline(details);
            break;
        default:
            details = Task.convertInputIntoDetails(taskDetails);
            task = new Task(details);
        }

        return task;
    }

    public Command parse(String input) throws Exception {
        boolean isExit = input.split(CHAR_SPACE)[0].equals(COMMAND_EXIT);
        if (isExit) {
            return new ExitCommand();
        }

        if (input.isEmpty()) { // do nothing
            return new Command();
        }

        Scanner scanner = new Scanner(input);
        String command = scanner.next().toLowerCase();

        Command result;
        switch (command) {
        case COMMAND_LIST:
            result = new ListCommand();
            break;
        case COMMAND_MARK:
            result = new MarkCommand(getID(scanner));
            break;
        case COMMAND_UNMARK:
            result = new UnmarkCommand(getID(scanner));
            break;
        case COMMAND_TODO:
            result = new AddTaskCommand(getTaskFromInput(scanner, TaskEnum.TODO));
            break;
        case COMMAND_DEADLINE:
            result = new AddTaskCommand(getTaskFromInput(scanner, TaskEnum.DEADLINE));
            break;
        case COMMAND_EVENT:
            result = new AddTaskCommand(getTaskFromInput(scanner, TaskEnum.EVENT));
            break;
        case COMMAND_DELETE:
            result = new DeleteCommand(getID(scanner));
            break;
        case COMMAND_FIND:
            result = new FindCommand(scanner.nextLine());
            break;
        default:
            throw new InvalidCommandException();
        }

        return result;
    }
}
