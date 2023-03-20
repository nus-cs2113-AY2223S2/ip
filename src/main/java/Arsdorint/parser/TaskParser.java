package Arsdorint.parser;

import Arsdorint.command.*;
import Arsdorint.data.TaskList;
import Arsdorint.task.Deadline;
import Arsdorint.task.Event;
import Arsdorint.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static Arsdorint.MessageList.*;

/**
 * A class that handle the parsing of command
 */

public class TaskParser {
    private TaskList taskList;

    /**
     * Parser instance take in the taskList to be manipulated
     */
    public TaskParser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parse user input string
     *
     * @param input The user input string
     * @return Command and its arguments
     */
    public Command parsedCommand(String input) {
        String[] command = input.split(" ");
        if (command.length == 0) {
            return new CommandWrong(MESSAGE_UNKNOWN, COMMAND_LIST_MESSAGE);
        }
        final String arguments = input.replaceFirst(command[0], "").trim();
        String lowerCaseLine = command[0].toLowerCase();
        if (lowerCaseLine.equals("bye")) {
            return new CommandExit();
        }
        else if (lowerCaseLine.contains("list")) {
            System.out.println("Here are the tasks in your list:\n");
            return new CommandList();
        }
        else if (lowerCaseLine.equalsIgnoreCase("mark")) {
            return mark(arguments);
        }
        else if (lowerCaseLine.equalsIgnoreCase("unmark")) {
            return unmark(arguments);
        }
        else if (lowerCaseLine.equalsIgnoreCase("todo")) {
            return addToDo(arguments);
        }
        else if (lowerCaseLine.equalsIgnoreCase("deadline")) {
            return addDeadline(arguments);
        }
        else if (lowerCaseLine.equalsIgnoreCase("event")) {
            return addEvent(arguments);
        }
        else if (lowerCaseLine.equalsIgnoreCase("find")) {
            return addFind(arguments);
        }
        //add command when user don't type the instruction's Arsdorint.command
        else if (lowerCaseLine.equalsIgnoreCase("delete")) {
            return delete(arguments);
        } else if (lowerCaseLine.equalsIgnoreCase("date")) {
            return addDate(arguments);
        } else {
            return new CommandWrong(MESSAGE_UNKNOWN, COMMAND_LIST_MESSAGE);
        }
    }

    public static void fileParser(String input) throws ArsdorintFileException {
        String[] parsedLine = input.split("");
        String[] parsedTrim = new String[parsedLine.length];

        for (int i = 0; i < parsedLine.length; i++) {
            parsedTrim[i] = parsedLine[i].trim();
        }
        switch (parsedLine[0]) {
            case Todo.TYPE_TODO:
                todoParser(parsedTrim);
                break;
            case Deadline.TYPE_DEADLINE:
                deadlineParser(parsedTrim);
                break;
            case Event.TYPE_EVENT:
                eventParser(parsedTrim);
                break;
            default:
                throw new ArsdorintFileException();
        }
    }

    public Command mark(String command) {
        try {
            String[] parsed = command.split(" ");
            if (parsed.length < 1) {
                throw new ArsdorintException();
            } return new CommandMark(strToIntArr(parsed));
        } catch (NumberFormatException | IndexOutOfBoundsException | ArsdorintException err) {
            return new CommandWrong(CommandMark.SYNTAX);
        }
    }

    public Command unmark(String command) {
        try {
            String[] parsed = command.split(" ");
            if (parsed.length < 1) {
                throw new ArsdorintException();
            } return new CommandUnmark(strToIntArr(parsed));
        } catch (NumberFormatException | IndexOutOfBoundsException | ArsdorintException err) {
            return new CommandWrong(CommandUnmark.SYNTAX);
    }
}

    public Command delete(String command) {
        try {
            String[] parsed = command.split(" ");
            if (parsed.length < 1) {
                throw new ArsdorintException();
            } return new CommandDelete(strToIntArr(parsed));
        } catch (NumberFormatException | IndexOutOfBoundsException | ArsdorintException err) {
            return new CommandWrong(CommandDelete.SYNTAX);
        }
    }

    public Command addToDo(String taskDescription) {
        try {
            String[] parsed = taskDescription.split("/");
            if (parsed[0].equals("")) {
                throw new ArsdorintException("Error: Empty Todo Description.");
            }
            return new CommandToDo(taskDescription);
        } catch (ArrayIndexOutOfBoundsException | ArsdorintException | NumberFormatException err) {
            return new CommandWrong(CommandToDo.SYNTAX);
        }
    }

    public Command addDeadline(String taskDescription) {
        try {
            String[] parsed = taskDescription.split("/");
            if (!(parsed.length == 2)) {
                throw new ArsdorintException("Error: Empty Deadline Description.");
            }
            return new CommandDeadline(parsed[0].trim(), parsed[1].trim());
        } catch (ArrayIndexOutOfBoundsException | ArsdorintException | NumberFormatException err) {
            return new CommandWrong(CommandDeadline.SYNTAX);
        }
    }

    public Command addEvent(String taskDescription) {
        try {
            String[] parsed = taskDescription.split("/");
            if (!(parsed.length == 2)) {
                throw new ArsdorintException("Error: Empty Event Description.");
            }
            return new CommandEvent(parsed[0].trim(), parsed[1].trim());
        } catch (ArrayIndexOutOfBoundsException | ArsdorintException | NumberFormatException err) {
            return new CommandWrong(CommandEvent.SYNTAX);
        }
    }

    public Command addDate(String taskDescription) {
        try {
            return new CommandDate(LocalDate.parse(taskDescription));
        } catch (DateTimeParseException err) {
            return new CommandWrong(CommandDate.SYNTAX);
        }
    }

    public Command addFind(String taskDescription) {
        try {
            if (taskDescription.equals("")) {
                throw new ArsdorintException();
            }
            return new CommandFind(taskDescription);
        } catch (ArsdorintException err) {
            return new CommandWrong(CommandFind.SYNTAX);
        }
    }

    private static void todoParser(String[] parsed) throws ArsdorintFileException {

        if (!(parsed.length == 3)) {
            throw new ArsdorintFileException();
        }
        TaskList.list.add(new Todo(Boolean.valueOf(parsed[1]), parsed[2]));
    }

    private static void deadlineParser(String[] parsed) throws ArsdorintFileException {
        if (!(parsed.length == 4)) {
            throw new ArsdorintFileException();
        }
        TaskList.list.add(new Deadline(Boolean.valueOf(parsed[1]), parsed[2], parsed[3]));
    }

    private static void eventParser(String[] parsed) throws ArsdorintFileException {
        if (!(parsed.length == 4)) {
            throw new ArsdorintFileException();
        }
        TaskList.list.add(new Event(Boolean.valueOf(parsed[1]), parsed[2], parsed[3]));
    }


    private int[] strToIntArr(String[] parsed) throws ArsdorintException {
        int[] intArr = new int[parsed.length];
        for (int i = 0; i < parsed.length; i++) {
            intArr[i] = Integer.parseInt(parsed[i]) - OFFSET;
            if (intArr[i] < 0 | intArr[i] > TaskList.list.size()) {
                throw new ArsdorintException();
            }
        }
        return intArr;
    }


}
