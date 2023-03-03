package duke.parser;

import duke.command.*;
import duke.exception.InvalidIndexError;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Parser that handles the user inputs
 */
public interface Parser {

    /**
     * Handles the various command type and parse accordingly
     *
     * @param input the information that requires parsing
     * @return command type that would represent what to execute
     */
    public static Command parse(String input) {
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
        } else if (command.equals("find")){
            String keyword = inputWords[1];
            FindCommand findCommand = new FindCommand(command, keyword);
            return findCommand;
        }
        SingleWordCommand singleWordCommand = new SingleWordCommand(command);
        return singleWordCommand;
    }

    /**
     * Handles the deadline task and parse accordingly
     *
     * @param taskField the information that requires parsing
     * @return list of string that contains the details of the deadline task
     */
    public static List<String> parseDeadline(String taskField){
        taskField = taskField.replaceFirst("deadline", "").trim();
        int indexOfSlash = taskField.indexOf("/");
        String taskName = taskField.substring(0, indexOfSlash - 1);
        String by = taskField.substring(indexOfSlash + 4);
        List<String> taskFieldList = Arrays.asList(taskName, by);
        return taskFieldList;
    }

    /**
     * Handles the event task and parse accordingly
     *
     * @param taskField the information that requires parsing
     * @return list of string that contains the details of the event task
     */
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

    /**
     * Parses the text file line
     *
     * @param textLine the line of information to be parsed into task details
     * @return a task object with all its details
     */
    public static Task parseTextFile(String textLine){
        String[] textLineArray = textLine.split(" ");
        switch (textLineArray[0]) {
            case "T":
                String taskName = textLine.substring(8);
                Todo todo = new Todo(taskName, "T");
                if (textLineArray[2].equals("1")) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                textLine = textLine.substring(8);
                int indexOfBoundary = textLine.indexOf("|");
                taskName = textLine.substring(0, indexOfBoundary - 1);
                String by = textLine.substring(indexOfBoundary + 2);
                Deadline deadline = new Deadline(taskName, "D", by);
                if (textLineArray[2].equals("1")) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                textLine = textLine.substring(8);
                indexOfBoundary = textLine.indexOf("|");
                int lastIndexOfBoundary = textLine.lastIndexOf("|");
                taskName = textLine.substring(0, indexOfBoundary - 1);
                String from = textLine.substring(indexOfBoundary + 2, lastIndexOfBoundary - 1);
                String to = textLine.substring(lastIndexOfBoundary + 2);
                Event event = new Event(taskName, "E", from, to);
                if (textLineArray[2].equals("1")) {
                    event.markAsDone();
                }
                return event;
        }
        return null;
    }

}
