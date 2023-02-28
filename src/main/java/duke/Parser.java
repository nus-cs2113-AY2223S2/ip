package duke;

import java.util.Arrays;
import java.util.List;

public interface Parser {
    public static Command parse(String input){
        String[] inputWords = input.split(" ");
        String firstCommand = inputWords[0];
        String taskFields = input.replaceFirst(firstCommand, "").trim();
        List<String> addCommands = Arrays.asList("todo", "deadline", "event");
        List<String> indexCommands = Arrays.asList("mark", "unmark", "delete");
        if (addCommands.contains(firstCommand)){
            AddCommand addCommand = new AddCommand(firstCommand, taskFields);
            return addCommand;
        } else if (indexCommands.contains(firstCommand)) {
            IndexCommand indexCommand = new IndexCommand(firstCommand, Integer.parseInt(inputWords[1]) - 1);
            return indexCommand;
        }
        Command c = new Command(inputWords[0]);
        return c;
    }
    public static List<String> parseDeadline(String taskFields){
        taskFields = taskFields.replaceFirst("deadline", "").trim();
        int indexOfSlash = taskFields.indexOf("/");
        String taskName = taskFields.substring(0, indexOfSlash - 1);
        String by = taskFields.substring(indexOfSlash + 4);
        List<String> returnList = Arrays.asList(taskName, by);
        return returnList;
    }

    public static List<String> parseEvent(String taskFields){
        taskFields = taskFields.replaceFirst("event", "").trim();
        int indexOfSlash = taskFields.indexOf("/");
        int lastIndexOfSlash = taskFields.lastIndexOf("/");
        String taskName = taskFields.substring(0, indexOfSlash - 1);
        String start = taskFields.substring(indexOfSlash + 6, lastIndexOfSlash - 1);
        String end = taskFields.substring(lastIndexOfSlash + 4);
        List<String> returnList = Arrays.asList(taskName, start, end);
        return returnList;
    }



}
