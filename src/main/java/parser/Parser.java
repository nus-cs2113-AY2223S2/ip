package parser;

import exception.DukeException;

public class Parser {
    public static String[] parse(String input) throws DukeException {
        String[] commands = new String[10];
        input = input.trim();
        String[] inputs = input.split(" ");
        commands[0] = inputs[0];
        switch (commands[0]) {
        case "list":
            break;
        case "mark":
        case "unmark":
            try {
                commands[1] = inputs[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Missing task number!");
            }
            break;
        case "todo":
            try {
                String description = input.split("todo ")[1];
                commands[1] = description;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            try {
                String removedKeyword = input.split("deadline ")[1];
                String[] splitString = removedKeyword.split(" /by ");
                String description = splitString[0];
                String due = splitString[1];
                commands[1] = description;
                commands[2] = due;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Missing details for deadline!");
            }
            break;
        case "event":
            try {
                String removedKeyword = input.split("event ")[1];
                String[] splitString = removedKeyword.split(" /from ");
                String description = splitString[0];
                splitString = splitString[1].split(" /to ");
                String start = splitString[0];
                String end = splitString[1];
                commands[1] = description;
                commands[2] = start;
                commands[3] = end;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Missing details for event!");
            }
            break;
        }
        return commands;
    }
}