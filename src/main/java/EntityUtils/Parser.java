package EntityUtils;

import Command.Command;
import Command.AddCommand;
import Command.DeleteCommand;
import Command.ExitCommand;
import Command.ListCommand;
import Command.MarkCommand;
import Command.UnmarkCommand;
import Entities.Task;
import Entities.Deadline;
import Entities.Event;
import Entities.Todo;
import Exceptions.DukeException;
import Exceptions.EmptyArgumentException;
import Exceptions.InsufficientArgumentsException;
import Exceptions.NoDescriptionException;
import Exceptions.NonPositiveNumberException;

public class Parser {
    public static Command parse(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        String command = inputArray[0].toLowerCase();
        Task task = null;
        int startDateIdx = -1, endDateIdx = -1, taskIdx = -1;
        String startDate = null, endDate = null;
        String taskDescription;

        Command c = null;
        switch (command) 
        {
        case "bye":
            c = new ExitCommand();
            break;

        case "list":
            c = new ListCommand();
            break;

        case "todo":
            if (input.length() == command.length()) {
                throw new NoDescriptionException(command);
            }
            taskDescription = input.substring(command.length() + 1);
            task = new Todo(taskDescription, false);
            c = new AddCommand(task);
            break;

        case "deadline":
            if (input.length() == command.length()) {
                throw new NoDescriptionException(command);
            }
        
            startDateIdx = input.indexOf("/by ");
            if (startDateIdx == -1) {
                throw new InsufficientArgumentsException(command, "deadline [task] /by [date]");
            }

            taskDescription = input.substring(command.length() + 1, startDateIdx);
            startDate = input.substring(startDateIdx + 4);
            if (taskDescription.length() == 0 || startDate.length() == 0) {
                String emptyArgument = 
                    taskDescription.length() == 0 
                    ? "Task Name" 
                    : "Start Date";
                throw new EmptyArgumentException(emptyArgument);
            }

            task = new Deadline(taskDescription, false, startDate);
            c = new AddCommand(task);
            break;

        case "event":
            if (input.length() == command.length()) {
                throw new NoDescriptionException(command);
            }

            startDateIdx = input.indexOf("/from ");
            endDateIdx = input.indexOf("/to ");
            if (startDateIdx == -1 || endDateIdx == -1) {
                throw new InsufficientArgumentsException(command, "event [task] /from [startDate] /to [startDate]");
            }

            taskDescription = input.substring(command.length() + 1, startDateIdx);
            startDate = input.substring(startDateIdx + 6, endDateIdx-1);
            endDate = input.substring(endDateIdx + 4);

            if (taskDescription.length() == 0 || startDate.length() == 0 || endDate.length() == 0) {
                String emptyArgument = 
                    taskDescription.length() == 0 
                    ? "Task Name" 
                    : startDate.length() == 0 
                    ? "Start Date" 
                    : "End Date";
                throw new EmptyArgumentException(emptyArgument);
            }

            task = new Event(taskDescription, false, startDate, endDate);
            c = new AddCommand(task);
            break;

        case "mark":
            if (input.length() == command.length()) {
                throw new NoDescriptionException(command);
            }

            try {
                taskIdx = Integer.parseInt(input.substring(command.length() + 1)) - 1;
            } catch (NumberFormatException e) {
                throw new NonPositiveNumberException(e);
            }

            c = new MarkCommand(taskIdx);
            break;
        
        case "unmark":
            if (input.length() == command.length()) {
                throw new NoDescriptionException(command);
            }

            try {
                taskIdx = Integer.parseInt(input.substring(command.length() + 1)) - 1;
            } catch (NumberFormatException e) {
                throw new NonPositiveNumberException(e);
            }

            c = new UnmarkCommand(taskIdx);
            break;
        
        case "delete":
            if (input.length() == command.length()) {
                throw new NoDescriptionException(command);
            }

            try {
                taskIdx = Integer.parseInt(input.substring(command.length() + 1)) - 1;
            } catch (NumberFormatException e) {
                throw new NonPositiveNumberException(e);
            }

            c = new DeleteCommand(taskIdx);
            break;

        default:
            throw new DukeException("Apologies, I do not understand your request :-(");
        }
        return c;
    }
}
