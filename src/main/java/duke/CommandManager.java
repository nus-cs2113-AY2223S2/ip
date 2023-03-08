package duke;

import duke.exceptions.CommandDescriptionEmptyException;
import duke.exceptions.MissingFileException;
import duke.exceptions.MissingParameterException;
import duke.exceptions.UnknownCommandException;
import duke.exceptions.*;
import duke.tasks.Bye;
import duke.tasks.Greet;
import duke.tasks.Task;
import duke.tools.Parser;

import java.io.IOException;

/**
 * Manage input commands.
 * Call relevant TaskManager methods based on user commands to edit tasks.
 */
public class CommandManager {
    private String inputCommand;
    private String commandType;
    private String commandDescription;
    private static final Parser COMMAND_PARSER = new Parser();

    /**
     * Set user command type and command description from user input string.
     *
     * @param command
     * @throws UnknownCommandException
     * @throws CommandDescriptionEmptyException
     */
    public void setCommand(String command) throws UnknownCommandException, CommandDescriptionEmptyException {
        this.inputCommand = command;
        this.commandType = COMMAND_PARSER.getCommandType(command);
        this.commandDescription = COMMAND_PARSER.getCommandDescription(command);
    }

    public void sayHello() {
        Greet hello = new Greet();
        hello.printHello();
    }

    public void sayBye() {
        Bye bye = new Bye();
        bye.printBye();
    }

    /**
     * Call different TaskManager methods based on input commands.
     * Throw errors if an unknown command is caught.
     */
    public void executeCommand() throws MissingParameterException,EditEmptyTasks,MissingFileException, IOException, NumberFormatException , DeleteIndexOutOfBound, DeleteEmptyTasks, UnknownCommandException, UpdateOutOfBound, WrongDateFormat{
        switch(commandType){
        case "bye":
            sayBye();
            System.exit(0);
            break;
        case "list":
            TaskManager.listTask();
            break;
        case "mark":
            TaskManager.editTaskStatus(this.commandDescription, "mark");
            break;
        case "unmark":
            TaskManager.editTaskStatus(this.commandDescription, "unmark");
            break;
        case "delete":
            TaskManager.deleteTask(this.commandDescription);
            break;
        case "todo":
        case "event":
        case "deadline":
            TaskManager.addTask(this.commandType, this.commandDescription);
            break;
        case "find":
            TaskManager.findTasksByKeyword(this.commandDescription);
            break;
        case "date":
            TaskManager.findDeadlinesByDate(this.commandDescription);
            break;
        default:
            throw new UnknownCommandException();
        }
    }
}
