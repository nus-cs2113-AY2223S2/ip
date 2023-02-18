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
    private static final String LIST = "list";
    private static final String EXIT = "bye";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";
    private static final String FIND = "find";
    public Parser(Scanner sc) {
        this.sc = sc;
    }
    @Override
    public String getMessage() {
        return message;
    }
    // private boolean isList() {
    //     return (message.split(" ")[0].toLowerCase().equals(LIST));
    // }
    // private boolean isExit() {
    //     return (message.split(" ")[0].toLowerCase().equals(EXIT));
    // }
    // private boolean isMark(){
    //     return (message.split(" ")[0].toLowerCase().equals(MARK));
    // }
    // private boolean isUnmark(){
    //     return (message.split(" ")[0].toLowerCase().equals(UNMARK));
    // }
    // private boolean isDelete() {
    //     return (message.split(" ")[0].toLowerCase().equals(DELETE));
    // }
    /**
     * Method checks the type of task given by the user
     * @return TaskTypeEnum
     * @exception InvalidCommandException
     */
    private TaskTypeEnum typeOfTask() throws InvalidCommandException {
        String task = message.split(" ")[0].toUpperCase();
        Optional<TaskTypeEnum> answer = Arrays.stream(TaskTypeEnum.values())
                                            .filter(x -> x.toString().equals(task))
                                            .findFirst();
        if (answer.isPresent()) {
            return answer.get();
        }
        else {
            throw new InvalidCommandException("Invalid format!!!\nKeyword must be in the form of <Keyword> <Args>", new IllegalArgumentException());
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
            // If the message is blank it means that the user did not input anything
            // so throw an exception
            if (message.isBlank()){
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            throw new EmptyCommandException("Empty command", e);
        }
    }
    @Override
    public Command getCommand() throws EmptyCommandException {
        try {
            // Get the message from the user before checking what is the command
            getNextMessage();
        } catch (EmptyCommandException e) {
            throw e;
        }

        // Assert assumption that the message is not null
        // after calling getNextMessage
        assert message != null;
        switch (message.split(" ")[0].toLowerCase()){
        case LIST:
            return currentCommand = Command.LIST;
        case EXIT:
            return currentCommand = Command.EXIT;
        case DELETE:
            return currentCommand = Command.DELETE;
        case UNMARK:
            return currentCommand = Command.UNMARK;
        case MARK:
            return currentCommand = Command.MARK;
        case FIND:
            return currentCommand = Command.FIND;
        default:
            return currentCommand = Command.TASK;
        }
        // if (isExit()) {
        //     currentCommand = Command.EXIT;
        // }
        // else if (isList()) {
        //     currentCommand = Command.LIST;
        // }
        // else if (isUnmark()) {
        //     currentCommand = Command.UNMARK;
        // }
        // else if (isMark()) {
        //     currentCommand =  Command.MARK;
        // }
        // else if (isDelete()) {
        //     currentCommand =  Command.DELETE;
        // }
        // else if (isFind()) {
        //     currentCommand =  Command.FIND;
        // }
        // else {
        //     currentCommand =  Command.TASK;
        // }
        // return currentCommand;
    }

    @Override
    public Task getTask() throws InvalidCommandException {
        TaskTypeEnum tasking = typeOfTask();

        if (message.split(" ", 2).length == 1) {
            // Only command given but no arguments
            // Raise error
            throw new InvalidCommandException("No arguments passed!!!\nKeyword must be in the form of <Keyword> <Args>", new IllegalArgumentException());
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
            default:
                throw new InvalidCommandException("Invalid task specified", new IllegalArgumentException());
            }
            task.parseArgument(arguments);
            return task;
        } catch (InvalidCommandException e) {
            throw e;
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException(e.getMessage(),e);
        } catch (EmptyDescriptionException e) {
            throw new InvalidCommandException(e.getMessage(),e);
        }
    }
    @Override
	public int getTaskIndex() throws InvalidCommandException{
        // Assert that mark or unmark is the command
        assert (currentCommand == Command.MARK) ||
               (currentCommand == Command.UNMARK) ||
               (currentCommand == Command.DELETE);

        try {
            // Try to get the index from the command
            // if there is an error then throw error message
            return Integer.parseInt(message.split(" ")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index", e);
        }
	}
    @Override
    public String getFindKeyword() {
        assert (currentCommand == Command.FIND);

        return message.split(" ")[1];
    }
}