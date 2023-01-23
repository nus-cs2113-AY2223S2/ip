import java.util.Scanner;

/**
 * Manage input commands.
 * Keep reading input command until 'bye'.
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
     * If command is 'bye', exit.
     * If command is 'list', list out all the tasks.
     * If command is mark, mark a particular task as done.
     * If command is unmark, mark a particular task as undone.
     * Else add new task.
     */
    public void manageCommand(){

        Bye bye = new Bye();
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while(!this.readCommand(in).equals("bye")){
            if(this.command.toLowerCase().equals("list")){
                taskManager.listTask();
            }else{
                String inputWords[] = this.command.split(" ");
                if(inputWords[0].toLowerCase().equals("mark")){
                    taskManager.editTaskStatus(inputWords, "mark");
                }else if(inputWords[0].toLowerCase().equals("unmark")){
                    taskManager.editTaskStatus(inputWords, "unmark");
                }else{
                    Task task = new Task(this.command);
                    taskManager.addTask(task);
                }
            }
        }
        bye.printBye();

    }
}
