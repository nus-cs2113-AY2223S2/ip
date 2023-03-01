package Arsdorint.parser;

import Arsdorint.MessageList;
import Arsdorint.command.*;
import Arsdorint.data.TaskList;
import Arsdorint.task.Deadline;
import Arsdorint.task.Event;
import Arsdorint.task.Task;
import Arsdorint.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static Arsdorint.Arsdorint.*;
import static Arsdorint.MessageList.*;
import static Arsdorint.MessageList.MESSAGE_DIVIDER;
import static Arsdorint.data.Storage.save;

public class TaskParser {
    public TaskParser() {
    }
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
        //add command when user don't type the instruction's Arsdorint.command
        else if (lowerCaseLine.equalsIgnoreCase("delete")) {
            return delete(arguments);
        } else if (lowerCaseLine.equalsIgnoreCase("date")) {
            return addDate(arguments);
            /*} else if (lowerCaseLine.equalsIgnoreCase("save")) {
            //save();
            return new CommandExit();*/
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
                toDoList.add(new Todo(parsedLine[2]));
                toDoList.get(Task.numOfTasks - 1).isDone = Boolean.valueOf(parsedLine[1]);
                addTaskMessage();
                break;
            case Deadline.TYPE_DEADLINE:
                toDoList.add(new Deadline(parsedLine[2], parsedLine[3]));
                toDoList.get(Task.numOfTasks - 1).isDone = Boolean.valueOf(parsedLine[1]);
                addTaskMessage();
                break;
            case Event.TYPE_EVENT:
                toDoList.add(new Event(parsedLine[2], parsedLine[3]));
                toDoList.get(Task.numOfTasks - 1).isDone = Boolean.valueOf(parsedLine[1]);
                addTaskMessage();
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
                //System.out.println("Nice! I've marked this task as done: \n");
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
            //System.out.println("OK, I've marked this task as not done yet: \n");
            } return new CommandMark(strToIntArr(parsed));
        } catch (NumberFormatException | IndexOutOfBoundsException | ArsdorintException err) {
            return new CommandWrong(CommandUnmark.SYNTAX);
    }
}

    public static void removeTaskMessage(int idx) {
        showToUser(MESSAGE_DIVIDER);
        System.out.println("\t");
        toDoList.get(idx).printTask();
        System.out.println("\t" + "Now you have " + --Task.numOfTasks + " tasks in the list.");
        showToUser(MESSAGE_DIVIDER);
    }

    public Command delete(String command) {
        try {
            String[] parsed = command.split(" ");
            if (parsed.length < 1) {
                throw new ArsdorintException();
            } return new CommandMark(strToIntArr(parsed));
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


    private static void exitMessage() {
        showToUser(MESSAGE_DIVIDER, EXIT_MESSAGE, MESSAGE_DIVIDER);
    }

    public static void showToUser(String... message) {
        for (String i : message) System.out.println(i);
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
