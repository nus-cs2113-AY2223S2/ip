package parser;

import command.*;
import exception.InvalidInputException;
import task.Deadline;
import exception.IncompleteInputException;
import task.Event;
import task.Task;
import java.time.format.DateTimeParseException;

/**
 * Represents a Parser class that is used to process all the input from the User into the appropriate
 * classes {@link Command} or {@link Task}
 */
public class Parser {
    private boolean isExecuting;

    public Parser() {
        isExecuting = true;
    }

    public boolean isExecuting() {
        return this.isExecuting;
    }

    /**
     * Process the input line String that is in the duke.txt file into a {@link Task}
     * This handler function will be called by {@link storage.TaskStorage#loadTasks}
     * @param input The input line that is stored in the txt file on the local machine
     * @return The appropriate {@link Task} complete with its description and state (marked or unmarked)
     * @throws IncompleteInputException If user specifies incomplete input tied to the command type. For example, "todo" input should always be accompanied by a description string.
     * @throws DateTimeParseException If the Time specified for {@link Deadline} and {@link Event} does not follow the YYYY-MM-DD HH:mm format
     */
    public static Task processSavedInput (String input) throws IncompleteInputException, DateTimeParseException {
        String taskType = "", commandInfo = "";
        switch (input.charAt(1)) {
            case 'T' :
                taskType = Input.TODO.input;
                commandInfo = input.substring(7);
                break;
            case 'D' :
                taskType = Input.DEADLINE.input;
                commandInfo = input.substring(7).replace("(by:","/by").replace(")","");

                break;
            case 'E' :
                taskType = Input.EVENT.input;
                commandInfo = input.substring(7).replace("(from:","/from").replace("to:","/to").replace(")","");
                break;
        }
        Task taskToAdd = handleAddTask(taskType,commandInfo);
        if (input.charAt(4) == 'X') {
            taskToAdd.markAsDone();
        }
        return taskToAdd;
    }

    /**
     * Handler function that wraps the taskType and commandInfo into its respective class {@link Task}, {@link Deadline}, or {@link Event}.
     * This handler function will be called by method {@link #parse} and {@link #processSavedInput}
     * @param taskType  The type of the task to be added, which can only be "todo","deadline", or "event".
     * @param commandInfo The description string for {@link Task}, {@link Deadline}, or {@link Event}
     * @return The corresponding {@link Task} depending on the taskType.
     * @throws IncompleteInputException If user specifies incomplete input tied to the command type. For example, "todo" input should always be accompanied by a description string.
     * @throws DateTimeParseException If the Time specified for {@link Deadline} and {@link Event} does not follow the YYYY-MM-DD HH:mm format
     */
    public static Task handleAddTask(String taskType, String commandInfo) throws IncompleteInputException, DateTimeParseException {
        Task newTask = null;
        if (taskType.equals(Input.TODO.input)) {
            newTask = new Task(commandInfo);
        }
        else if (taskType.equals(Input.DEADLINE.input)) {
            String[] infoArr = commandInfo.split("/by");
            if (infoArr.length != 2 ) {
                throw new IncompleteInputException ("Please specify your deadline string /by YYYY-MM-DD HH:MM");
            }

            //infoArr contains descStr and deadlineStr respectively
            newTask = new Deadline(infoArr[0].trim(), infoArr[1].trim());
        }
        else if (taskType.equals(Input.EVENT.input)) {
            String[] infoArr = commandInfo.split("/from|/to");
            if (infoArr.length !=3 ) {
                throw new IncompleteInputException ("Please specify the starting and ending time of your event");
            }
            //infoArr contains descStr, fromStr, and toStr respectively
            newTask = new Event(infoArr[0].trim(), infoArr[1].trim(), infoArr[2].trim());
        }
        return newTask;
    }

    /**
     * Handler function that converts the commandType and index to edit into {@link MarkCommand}, {@link UnmarkCommand}, or {@link RemoveCommand}.
     * This handler function will be called by method {@link #parse} whenever the command type is either "mark","unmark" or "delete"
     *
     * @param commandType The command type specified by the user ("mark","unmark","delete").
     * @param idxToEdit The 0-based index of the task that the user wishes to mark, unmark or delete.
     * @return The appropriate {@link Command} depending on the commandType.
     * @throws NumberFormatException If the idxToEdit cannot be parsed into an Integer.
     */
    public Command handleEditTask(String commandType, String idxToEdit) throws NumberFormatException {
        if (commandType.equals(Input.MARK.input)) {
            int indexToMark = Integer.parseInt(idxToEdit) - 1; //turn it into 0-based
            return new MarkCommand(indexToMark);
        } else if (commandType.equals(Input.UNMARK.input)) {
            int indexToUnmark = Integer.parseInt(idxToEdit) - 1;
            return new UnmarkCommand(indexToUnmark);
        } else {
            int indexToRemove = Integer.parseInt(idxToEdit)-1;
            return new RemoveCommand(indexToRemove);
        }
    }

    /**
     * Parses user input into respective {@link Command} depending on the command type and description.
     *
     * @param inputLine The complete user input from the CLI.
     * @return The appropriate {@link Command} that is associated with the inputLine.
     * @throws IncompleteInputException If user specifies incomplete input tied to the command type. For example, "todo" input should always be accompanied by a description string.
     * @throws InvalidInputException If user types in input other than "list","bye","mark","unmark", "find","todo","deadline","event".
     * @throws NumberFormatException If the input following "mark","unmark","delete" cannot be parsed into an Integer.
     * @throws DateTimeParseException If the Time specified for {@link Deadline} and {@link Event} does not follow the YYYY-MM-DD HH:mm format
     */
    public Command parse (String inputLine) throws IncompleteInputException, InvalidInputException ,NumberFormatException, DateTimeParseException {
        //splits input based on one or more whitespaces into two words
        String[] inputWords = inputLine.split("\\s+", 2);
        String command = inputWords[0];

        if (command.equals(Input.BYE.input)) {
            isExecuting = false;
            return new ExitCommand();
        }
        else if (command.equals(Input.LIST.input)) {
            return new ListCommand();
        }

        else if(command.equals(Input.FIND.input)){
            if (inputWords.length < 2) {
                throw new IncompleteInputException ("Please specify which task you wish to edit");
            } else {
                return new FindCommand(inputWords[1]);
            }
        }
        else if (command.matches(Input.MARK.input + "|" + Input.UNMARK.input + "|" + Input.DELETE.input)) {
            //inputWords[1] is string that no longer contains the command string
            if (inputWords.length < 2) {
                throw new IncompleteInputException ("Please specify which task you wish to edit");
            } else {
                return handleEditTask(command, inputWords[1]);
            }
        }
        //check if command string matches either of todo,deadline,event
        else if (command.matches(Input.TODO.input + "|" + Input.DEADLINE.input + "|" + Input.EVENT.input)) {
            if (inputWords.length < 2) {
                throw new IncompleteInputException ("Please specify the description to the task that you wish to add");
            }
            Task taskToAdd = handleAddTask(command, inputWords[1]);
            return new AddTaskCommand(taskToAdd);
        }
        else {
            throw new InvalidInputException ("fsgfsuygu I don't know what that means :(");
        }
    }
}

