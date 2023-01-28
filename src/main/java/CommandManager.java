import java.util.Scanner;

/**
 * Manage input commands.
 * Keep reading input command until 'bye'.
 */
public class CommandManager {
    private String inputCommand;
    private String commandType;
    private String commandDescription;

    public void setCommand(String command) {
        Parser parseCommand = new Parser();
        this.inputCommand = command;
        this.commandType = parseCommand.getCommandType(command);
        this.commandDescription = parseCommand.getCommandDescription(command);
    }

    public void sayHello(){
        Greet hello = new Greet();
        hello.printHello();
    }

    public void sayBye(){
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
    public void executeCommand(){


        UI readInputCommandLoop = new UI();
        TaskManager taskManager = new TaskManager();

        while(!this.commandType.equals("bye")){
            if(commandType.equals("list")){
                taskManager.listTask();
            }else if(commandType.equals("mark")){
                taskManager.editTaskStatus(this.commandDescription, "mark");
            }else if(commandType.equals("unmark")){
                taskManager.editTaskStatus(this.commandDescription, "unmark");
            }else{
                taskManager.addTask(this.commandType, this.commandDescription);
            }
            setCommand(readInputCommandLoop.readInput());
        }
        sayBye();
    }
}
