package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.IndexCommand;

import java.util.Arrays;
import java.util.List;

public interface Parser {
    public static Command parse(String input){
        String[] inputWords = input.split(" ");
        String command = inputWords[0];
        String taskField = input.replaceFirst(command, "").trim();
        List<String> addCommands = Arrays.asList("todo", "deadline", "event");
        List<String> indexCommands = Arrays.asList("mark", "unmark", "delete");
        if (addCommands.contains(command)){
            AddCommand addCommand = new AddCommand(command, taskField);
            return addCommand;
        } else if (indexCommands.contains(command)) {
            int taskNum = Integer.parseInt(inputWords[1]) - 1;
            IndexCommand indexCommand = new IndexCommand(command, taskNum);
            return indexCommand;
        }
        Command newCommand = new Command(command);
        return newCommand;
    }
    public static List<String> parseDeadline(String taskField){
        taskField = taskField.replaceFirst("deadline", "").trim();
        int indexOfSlash = taskField.indexOf("/");
        String taskName = taskField.substring(0, indexOfSlash - 1);
        String by = taskField.substring(indexOfSlash + 4);
        List<String> taskFieldList = Arrays.asList(taskName, by);
        return taskFieldList;
    }

    public static List<String> parseEvent(String taskField){
        taskField = taskField.replaceFirst("event", "").trim();
        int indexOfSlash = taskField.indexOf("/");
        int lastIndexOfSlash = taskField.lastIndexOf("/");
        String taskName = taskField.substring(0, indexOfSlash - 1);
        String start = taskField.substring(indexOfSlash + 6, lastIndexOfSlash - 1);
        String end = taskField.substring(lastIndexOfSlash + 4);
        List<String> taskFieldList = Arrays.asList(taskName, start, end);
        return taskFieldList;
    }
}
