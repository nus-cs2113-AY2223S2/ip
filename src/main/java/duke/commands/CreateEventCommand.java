package duke.commands;

import duke.exception.EmptyCommandException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command for adding a deadline task to the task list.
 */
public class CreateEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs a command that will add an event task to the task list.
     *
     * @param cases An array of string that contains the deadline command, description and due time.
     * @throws EmptyCommandException The exception thrown when user only inputs the command.
     * @throws InvalidFormatException The exception thrown when user enters a command in an invalid format.
     * @throws EmptyDescriptionException The exception thrown when user leaves the description of the command to be empty.
     */
    public CreateEventCommand(String[] cases) throws EmptyCommandException, InvalidFormatException,
            EmptyDescriptionException {
        if (cases.length == 1) {
            throw new EmptyCommandException();
        }
        String input = cases[1];
        if (!input.contains("/")){
            throw new InvalidFormatException();
        }
        String [] checkSecondSlash = input.split("/", 2);
        if (!checkSecondSlash[1].contains("/")){
            throw new InvalidFormatException();
        }
        String[] splitInput = input.split("/", 3);
        description = splitInput[0].trim();
        if (description.length() == 0){
            throw new EmptyDescriptionException();
        }
        if (splitInput[1].length() < 6 || splitInput[2].length() < 4) {
            throw new InvalidFormatException();
        }
        from = splitInput[1].substring(5);
        to = splitInput[2].substring(3);
    }

    /**
     * Executes the command to create a new event task which will be
     * added to the task list and database.
     *
     * @param taskList The task list that the command is executed on
     */
    @Override
    public void execute(TaskList taskList){
        Task currTask = new Event(description, from, to);
        taskList.addTasks(currTask);
        Ui.printAddedTaskCommand(taskList);
    }
}
