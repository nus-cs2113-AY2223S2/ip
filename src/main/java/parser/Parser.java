package parser;

import exception.DukeException;

import java.util.ArrayList;

public class Parser {
    public static ArrayList<String> parse(String input) throws DukeException {
        ArrayList<String> commands = new ArrayList<>();
        input = input.trim();
        String[] inputs = input.split(" ");
        commands.add(inputs[0]);
        switch (commands.get(0)) {
        case "list":
            break;
        case "mark":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "delete":
            try {
                commands.add(inputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Missing task number!");
            }
            break;
        case "todo":
            try {
                String description = input.split("todo ")[1];
                commands.add(description);
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
                commands.add(description);
                commands.add(due);
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
                commands.add(description);
                commands.add(start);
                commands.add(end);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Missing details for event!");
            }
            break;
        }
        return commands;
    }
}