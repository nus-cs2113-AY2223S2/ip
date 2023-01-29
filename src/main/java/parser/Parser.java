package parser;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Optional;
import task.Deadline;
import task.EmptyDescriptionException;
import task.Event;
import task.Task;
import task.TaskTypeEnum;
import task.ToDo;

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
    private TaskTypeEnum typeOfTask() throws InvalidCommandException {
        String task = message.split(" ")[0].toUpperCase();
        Optional<TaskTypeEnum> answer = Arrays.stream(TaskTypeEnum.values())
                                        .filter(x -> x.toString().equals(task))
                                        .findFirst();
        if (answer.isPresent()) {
            return answer.get();
        }
        else {
            throw new InvalidCommandException("Invalid command given", new IllegalArgumentException());
        }
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
    public Task getTask() throws InvalidCommandException {
        TaskTypeEnum tasking = typeOfTask();

        if (message.split(" ", 2).length == 1) {
            // Only command given but no arguments
            // Raise error
            throw new InvalidCommandException("No arguments given in command", new IllegalArgumentException());
        }
        String arguments = message.split(" ", 2)[1];
        try {
            Task task = null;
            switch (tasking) {
                case DEADLINE:
                    task = new Deadline();
                    break;
                case EVENT:
                    task = new Event();
                    break;
                case TODO:
                    task = new ToDo();
                    break;
            }
            task.parseArgument(arguments);
            return task;
        } catch (InvalidCommandException e) {
            throw e;
        } catch (StringIndexOutOfBoundsException | EmptyDescriptionException e) {
            throw new InvalidCommandException("Command could not be understood",e);
        }
    }
    @Override
	public int getTaskIndex() throws InvalidCommandException{
        try {
            return Integer.parseInt(message.split(" ")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index", e);
        }
	}
}