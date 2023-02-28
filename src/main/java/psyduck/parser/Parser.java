package psyduck.parser;

import psyduck.exceptions.*;

import java.util.Scanner;

public class Parser {
    private static final int NOT_FOUND = -1;


    public String prepareCommand(String input) {
        input = input.trim();
        int space = input.indexOf(" ");
        String command;
        if (space == NOT_FOUND) {
            command = input;
        } else {
            command = input.substring(0, space);
        }
        return command;
    }

    public String[] prepareDeadline(String input) throws
            TaskEmptyException, InvalidDeadlineFormatException {
        input = input.trim();
        int space = input.indexOf(" ");
        if (space == NOT_FOUND) {
            throw new TaskEmptyException();
        }
        int byPos = input.indexOf("/by");
        if (byPos == NOT_FOUND) {
            throw new InvalidDeadlineFormatException();
        }
        String description = input.substring(space + 1, byPos);
        String by = input.substring(byPos + 4);
        String[] output = new String[2];
        output[0] = description;
        output[1] = by;
        return output;
    }

    public String[] prepareEvent(String input) throws
            TaskEmptyException, InvalidEventFormatException {
        input = input.trim();
        int space = input.indexOf(" ");
        if (space == NOT_FOUND) {
            throw new TaskEmptyException();
        }
        int fromPos = input.indexOf("/from");
        int toPos = input.indexOf("/to");
        if (fromPos == NOT_FOUND || toPos == NOT_FOUND) {
            throw new InvalidEventFormatException();
        }
        String description = input.substring(space + 1, fromPos);
        String from = input.substring(fromPos + 6, toPos);
        String to = input.substring(toPos + 4);
        String[] output = new String[3];
        output[0] = description;
        output[1] = from;
        output[2] = to;
        return output;
    }

    public String[] prepareToDo(String input) throws TaskEmptyException {
        input = input.trim();
        int space = input.indexOf(" ");
        if (space == NOT_FOUND) {
            throw new TaskEmptyException();
        }
        String description = input.substring(space + 1);
        String[] output = new String[1];
        output[0] = description;
        return output;
    }

    public String[] prepareRemove(String input) {
        input = input.trim();
        int space = input.indexOf(" ");
        int taskNum = Integer.parseInt(input.substring(space + 1));
        String[] output = new String[1];
        output[0] = Integer.toString(taskNum);
        return output;
    }

    public String[] prepareMark(String input) {
        input = input.trim();
        int space = input.indexOf(" ");
        int taskNum = Integer.parseInt(input.substring(space + 1));
        String[] output = new String[1];
        output[0] = Integer.toString(taskNum);
        return output;
    }

    public String[] prepareUnmark(String input) {
        input = input.trim();
        int space = input.indexOf(" ");
        int taskNum = Integer.parseInt(input.substring(space + 1));
        String[] output = new String[1];
        output[0] = Integer.toString(taskNum);
        return output;
    }
}
