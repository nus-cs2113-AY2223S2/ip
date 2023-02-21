package duke;

import duke.exceptions.CommandDescriptionEmptyException;
import duke.exceptions.MissingFileException;
import duke.exceptions.MissingParameterException;
import duke.exceptions.UnknownCommandException;
import duke.exceptions.*;
import duke.tasks.Bye;
import duke.tasks.Greet;
import duke.tools.Parser;

import java.io.IOException;

/**
 * Manage input commands.
 * Keep reading input command until 'bye'.
 */
public class CommandManager {
    private String inputCommand;
    private String commandType;
    private String commandDescription;
    private static final Parser COMMAND_PARSER = new Parser();

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
     * If command is 'bye', exit.
     * If command is 'list', list out all the tasks.
     * If command is mark, mark a particular task as done.
     * If command is unmark, mark a particular task as undone.
     * Else add new task.
     */
    public void executeCommand() throws MissingParameterException,EditEmptyTasks,MissingFileException, IOException,  DeleteIndexOutOfBound, DeleteEmptyTasks, UnknownCommandException{
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
        default:
            throw new UnknownCommandException();
        }
    }
}
