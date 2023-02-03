import java.util.Scanner;

/**
 * Manage input commands.
 * Keep reading input command until 'bye'.
 */
public class CommandManager {
    private String inputCommand;
    private String commandType;
    private String commandDescription;
    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final Parser COMMAND_PARSER = new Parser();

    public void setCommand(String command) throws UnknownCommandException, ContentEmptyException{
        this.inputCommand = command;

        this.commandType = COMMAND_PARSER.getCommandType(command);
        if(!(commandType.equals("todo")||commandType.equals("event")||commandType.equals("deadline")||commandType.equals("list")||commandType.equals("bye")||commandType.equals("mark")||commandType.equals("unmark"))){
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
     * If command is 'bye', exit.
     * If command is 'list', list out all the tasks.
     * If command is mark, mark a particular task as done.
     * If command is unmark, mark a particular task as undone.
     * Else add new task.
     */
    public void executeCommand() throws MissingParameterException{
        if(commandType.equals("bye")){
            sayBye();
            System.exit(0);
        }else if(commandType.equals("list")){
            TASK_MANAGER.listTask();
        }else if(commandType.equals("mark")){
            TASK_MANAGER.editTaskStatus(this.commandDescription, "mark");
        }else if(commandType.equals("unmark")){
            TASK_MANAGER.editTaskStatus(this.commandDescription, "unmark");
        }else if(commandType.equals("todo")||commandType.equals("event")||commandType.equals("deadline")){
            TASK_MANAGER.addTask(this.commandType, this.commandDescription);
        }
    }
}
