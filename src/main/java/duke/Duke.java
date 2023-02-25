package duke;
import commands.Command;
import file.FileManager;
import userInterface.Print;
import userInterface.UserInput;
import tasklist.Tasklist;

public class Duke {

    private Tasklist tasksList;

    public Duke(){
        tasksList = new Tasklist();
    }
    public static void main(String[] args){
        FileManager.initialise();
        Print.printWelcome();
        while (true){
            Command.runCommand(UserInput.getInput());
        }
    }
}