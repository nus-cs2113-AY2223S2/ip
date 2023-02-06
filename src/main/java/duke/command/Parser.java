package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Errors;

import java.util.HashMap;
import java.util.StringJoiner;

public abstract class Parser {
    public static HashMap<String, String> parseArguments(String command, String[] flags) throws DukeException {
        HashMap<String, String> args = new HashMap<>();
        StringJoiner currentArg = new StringJoiner(" ");
        String currentFlag = flags[0];

        String[] splitCommand = command.split(" ");
        for (String string : splitCommand) {
            boolean newFlag = false;
            for (String flag : flags) {
                if (!string.equals(flag)) {
                    continue;
                }
                if (args.containsKey(flag) || currentFlag.equals(flag)) {
                    throw new DukeException(Errors.INVALID_FORMAT.MESSAGE);
                }

                args.put(currentFlag, currentArg.toString());
                currentArg = new StringJoiner(" ");
                currentFlag = flag;
                newFlag = true;
                break;
            }
            if (!newFlag) {
                currentArg.add(string);
            }
        }
        args.put(currentFlag, currentArg.toString());

        return args;
    }

    public static Command parseCommand(String string, TaskList taskList) {
        String[] command = string.trim().replaceAll("\\s+", " ").split(" ", 2);

        try {
            switch (command[0]) {
            case "todo":
                return new ToDoTaskCommand(command);
            case "deadline":
                return new DeadlineTaskCommand(command);
            case "event":
                return new EventTaskCommand(command);
            case "list":
                return new ListTasksCommand();
            case "mark":
                // Fallthrough
            case "unmark":
                return new MarkTaskCommand(command, taskList);
            case "bye":
                return new ExitCommand();
            default:
                return new InvalidCommand(Errors.INVALID_COMMAND.MESSAGE, command[0]);
            }
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage(), command[0]);
        }
    }
}
