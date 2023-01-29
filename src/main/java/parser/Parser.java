package parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Optional;
import task.TaskType;

public class Parser implements IParser {
    private Scanner sc;
    private String message;
    private Command currentCommand;
    public Parser(Scanner sc) {
        this.sc = sc;
    }
    @Override
    public String getMessage() {
        return message;
    }
    private boolean isList() {
        return (message.split(" ")[0].toLowerCase().equals("list"));
    }
    private boolean isExit() {
        return (message.split(" ")[0].toLowerCase().equals("bye"));
    }
    private boolean isMark(){
        return (message.split(" ")[0].toLowerCase().equals("mark"));
    }
    private boolean isUnmark(){
        return (message.split(" ")[0].toLowerCase().equals("unmark"));
    }
    private TaskType typeOfTask() throws InvalidCommandException {
        String task = message.split(" ")[0].toUpperCase();
        Optional<TaskType> answer = Arrays.stream(TaskType.values())
                                        .filter(x -> x.toString().equals(task))
                                        .findFirst();
        if (answer.isPresent()) {
            return answer.get();
        }
        else {
            throw new InvalidCommandException("Invalid command given", new IllegalArgumentException());
        }
    }
    private HashMap<String, String> parseTask(TaskType tasking) throws InvalidCommandException {
        HashMap<String, String> dict = new HashMap<String, String>();

        if (message.split(" ", 2).length == 1) {
            // Only command given but no arguments
            // Raise error
            throw new InvalidCommandException("No arugments given in command", new IllegalArgumentException());
        }
        String arugments = message.split(" ", 2)[1];
        try {
            switch (tasking) {
                case DEADLINE:
                    int by = arugments.indexOf("/by");
                    // Check if expression exist and if it is surrounded by a space
                    if (by <= 0 || arugments.charAt(by-1)!=' ' || arugments.charAt(by+3)!=' ') {
                        break;
                    }
                    dict.put("description", arugments.substring(0,by - 1));
                    dict.put("endDate", arugments.substring(by + 4));
                    return dict;
                case EVENT:
                    int from = arugments.indexOf("/from");
                    int to = arugments.indexOf("/to");
                    if (from == -1 || to == -1  || arugments.charAt(from-1)!=' ' || arugments.charAt(from+5)!=' '
                        || arugments.charAt(to-1)!=' ' || arugments.charAt(to+3)!=' ') {
                        break;
                    }
                    dict.put("description", arugments.substring(0,from - 1));
                    dict.put("startDate", arugments.substring(from + 6, to -1));
                    dict.put("endDate", arugments.substring(to + 4));
                    return dict;
                case TODO:
                    dict.put("description", arugments);
                    return dict;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // Throw error below
        }
        throw new InvalidCommandException("Command could not be understood", new IllegalArgumentException());
    }
    /**
     * Gets the a message from console that user inputs.
     * Checks if the input message is empty and raises Exception if empty.
     * @exception EmptyCommandException
     */
    private void getNextMessage() throws EmptyCommandException {
        try {
            message = sc.nextLine().trim();
            if (message.isBlank()){
                throw new NoSuchElementException();
            }
        }
        catch (NoSuchElementException e) {
            throw new EmptyCommandException("Empty command", e);
        }
    }
    @Override
    public Command getCommand() throws EmptyCommandException {
        getNextMessage();
        if (message == null) {
            throw new EmptyCommandException("Empty command",new NoSuchElementException());
        }
        if (isExit()) {
            currentCommand = Command.EXIT;
        }
        else if (isList()) {
            currentCommand = Command.LIST;
        }
        else if (isUnmark()) {
            currentCommand = Command.UNMARK;
        }
        else if (isMark()) {
            currentCommand =  Command.MARK;
        }
        else {
            currentCommand =  Command.TASK;
        }
        return currentCommand;
    }
    @Override
    public Argument getCommandArguments(Command command) throws InvalidCommandException {
        if (currentCommand.compareTo(command) != 0){
            // Mismatch in request
            // TODO Throw error
        }
        try {
            Argument arg = new Argument();
            if (command.equals(Command.MARK) || command.equals(Command.UNMARK)) {
                arg.setCommand(command);
                arg.setIndex(Integer.parseInt(message.split(" ")[1]));
            }
            else if (command.equals(Command.TASK)) {
                TaskType tasking = typeOfTask();
                arg.setCommand(tasking);
                arg.setVariableArguments(parseTask(tasking));
            }
            else {
                // TODO Handle error
            }
            return arg;
        } catch (InvalidCommandException e) {
            throw e;
        }
    }
}
