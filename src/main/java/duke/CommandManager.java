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
        if(!(commandType.equals("todo")||commandType.equals("event")||commandType.equals("deadline")||commandType.equals("list")||commandType.equals("bye")||commandType.equals("mark")||commandType.equals("unmark")||commandType.equals("delete"))){
            throw new UnknownCommandException();
        }
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
    public void executeCommand() throws MissingParameterException,EditEmptyTasks,MissingFileException, IOException,  DeleteIndexOutOfBound, DeleteEmptyTasks{
        if(commandType.equals("bye")){
            sayBye();
            System.exit(0);
        }else if(commandType.equals("list")){
            TaskManager.listTask();
        }else if(commandType.equals("mark")){
            TaskManager.editTaskStatus(this.commandDescription, "mark");
        }else if(commandType.equals("unmark")){
            TaskManager.editTaskStatus(this.commandDescription, "unmark");
        }else if(commandType.equals("todo")||commandType.equals("event")||commandType.equals("deadline")){
            TaskManager.addTask(this.commandType, this.commandDescription);
        }else if(commandType.equals("delete")){
            TaskManager.deleteTask(this.commandDescription);
        }
    }
}
