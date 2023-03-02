package duke;
import commands.Command;
import file.FileManager;
import userInterface.Print;
import userInterface.UserInput;

/*
 * Handles the initialsation and running of the main program Duke
 */
public class Duke {

    public static void main(String[] args){
        FileManager.initialise();
        Print.printWelcome();
        while (true){
            Command.runCommand(UserInput.getInput());
        }
    }
}