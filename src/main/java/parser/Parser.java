package parser;
import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.Handler;
import commands.DeleteCommand;
import commands.GoodbyeCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;
import UI.TextUI;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
public class Parser {
    TextUI ui = new TextUI();
    public static Handler parseCommand(String userInput) {
        String[] words = userInput.trim().split(" ", 2);
        if (words.length == 0) {
            return (Handler) new IncorrectCommand();
        }

        final String commandWord = words[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {

        case AddDeadlineCommand.COMMAND_WORD:
            return (Handler) new AddDeadlineCommand(arguments);

        case AddEventCommand.COMMAND_WORD:
            return (Handler) new AddEventCommand(arguments);

        case AddTodoCommand.COMMAND_WORD:
            return (Handler) new AddTodoCommand(arguments);

        case DeleteCommand.COMMAND_WORD:
            return (Handler) new DeleteCommand(arguments);

        case GoodbyeCommand.COMMAND_WORD:
            return (Handler) new GoodbyeCommand();

        case MarkCommand.COMMAND_WORD:
            return (Handler) new MarkCommand(arguments);

        case ListCommand.COMMAND_WORD:
            return (Handler) new ListCommand();

        case UnmarkCommand.COMMAND_WORD:
            return (Handler) new UnmarkCommand(arguments);

        default:
            return (Handler) new IncorrectCommand();
        }
    }

    public String[] parseDeadline(String arguments) {
        try {
            int dividerPosition = arguments.indexOf("/by");
            String description = arguments.substring(0, dividerPosition).trim();
            String endTime = arguments.substring(dividerPosition + 3).trim();
            return new String[]{description, endTime};
        } catch (StringIndexOutOfBoundsException e) {
            ui.showIncorrectFormatMessage("deadline");
            return null;
        }

    }

    public String[] parseEvent(String arguments) {
        try {
            int divider1Position = arguments.indexOf("/from");
            int divider2Position = arguments.indexOf("/to");
            String description = arguments.substring(0, divider1Position).trim();
            String startTime = arguments.substring(divider1Position + 5, divider2Position).trim();
            String endTime = arguments.substring(divider2Position + 3).trim();
            return new String[]{description, startTime, endTime};
        } catch (StringIndexOutOfBoundsException e) {
            ui.showIncorrectFormatMessage("event");
            return null;
        }

    }

    public int parseIndex(String arguments) {
        try {
            return Integer.parseInt(arguments) - 1;
        } catch (InputMismatchException | NumberFormatException e) {
            ui.showTaskIndexNotIntegerError();
            return -1;
        }
    }


    public Task parseFileContent(String text) {
        Task readTask = null;
        try {
            String[] arrOfStr = text.split("[|]");
            String taskType = arrOfStr[0].trim();
            String doneString = arrOfStr[1].trim();
            String description = arrOfStr[2].trim();
            Boolean isDone;
            if (doneString.equals("0")) {
                isDone = false;
            } else if (doneString.equals("1")) {
                isDone = true;
            } else {
                isDone = null;
            }

            if (isDone != null) {

                String startTime;
                String endTime;

                switch (taskType) {
                case "T":
                    readTask = new Todo(description);
                    readTask.setMark(isDone);
                    break;
                case "D":
                    endTime = arrOfStr[3].trim();
                    readTask = new Deadline(description, endTime);
                    readTask.setMark(isDone);
                    break;
                case "E":
                    startTime = arrOfStr[3].trim();
                    endTime = arrOfStr[4].trim();
                    readTask = new Event(description, startTime, endTime);
                    readTask.setMark(isDone);
                    break;
                default:
                    readTask = null;
                }
            }
        } catch (DateTimeParseException e) {
            return readTask = null;
        }

        return readTask;
    }

    public String parseTaskToWrite(Task task) {
        Boolean isDone = task.getStatus();
        String taskType = task.getTaskType();
        String encodedBooleanIsDone;
        String description = task.getTaskDescription();
        String startTime;
        String endTime;
        String parseResult = null;

        if (isDone) {
            encodedBooleanIsDone = "1";
        } else {
            encodedBooleanIsDone = "0";
        }

        switch (taskType) {
        case "T":
            parseResult = taskType + " | " + encodedBooleanIsDone + " | " + description;
            break;
        case "D":
            endTime = ((Deadline) task).getEndTime();
            parseResult = taskType + " | " + encodedBooleanIsDone + " | " + description + " | " + endTime;
            break;
        case "E":
            startTime = ((Event) task).getStartTime();
            endTime = ((Event) task).getEndTime();
            parseResult = taskType + " | " + encodedBooleanIsDone + " | " + description + " | " + startTime + " | " + endTime;
            break;
        default:
            parseResult = "Error";
        }

        return parseResult;
    }

}
