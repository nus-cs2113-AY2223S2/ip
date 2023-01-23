import java.util.Scanner;

/**
 * To manage input commands
 * Keep reading input command until 'bye'
 */
public class CommandManager {
    private String command;

    public CommandManager() {
        this.command = " ";
    }

    public String readCommand(Scanner in){
        String inputCommand;
        inputCommand = in.nextLine();
        this.command = inputCommand;
        return inputCommand.toLowerCase();
    }

    /**
     * If command is 'bye', exit
     * If command is 'list', list out all the tasks
     * Else store input commands and echo to users
     */
    public void manageCommand(){
        Echo echo = new Echo();
        Bye bye = new Bye();
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while(!this.readCommand(in).equals("bye")){
            if(this.command.toLowerCase().equals("list")){
                taskManager.printTask();
            }else{
                taskManager.addTask(this.command);
                echo.echoInput(this.command);
            }
        }
        bye.printBye();

    }
}
