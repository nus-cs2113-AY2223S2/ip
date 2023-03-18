import duke.exception.CommandNotFoundException;
import duke.exception.DukeException;
import duke.exception.IllegalFormatException;
import duke.exception.InvalidInputException;
import duke.exception.MissingInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    protected UI ui = new UI();
    protected TaskList taskList = new TaskList();
    protected Parser parser = new Parser();
    protected Data data = new Data("data/duke.txt", "data");

    public void run(){
        ui.printHelloMessage();
        boolean proceedToNextCommand = true;
        try {
            data.readFile(taskList);
        } catch (FileNotFoundException e) {
            try {
                data.checkFile();
            } catch (IOException checkException) {
                ui.printErrorMessage("File not found. I will be creating one for you.");
            }
        } catch (IOException readException) {
            ui.printErrorMessage("I'm sorry, but there is an error in reading the file.");
        }
        while (proceedToNextCommand) {
            String userInput = UI.readCommand();
            ui.printDivider();
            try {
                proceedToNextCommand = parser.executeCommand(userInput);
                if (userInput.equals("bye")) {
                    data.writeFile();
                    break;
                }
            } catch (CommandNotFoundException e) {
                ui.printErrorMessage("I'm sorry, but I don't know what that means.");
            } catch (MissingInputException e) {
                ui.printErrorMessage("Description of the command is empty.");
            } catch (IllegalFormatException e) {
                ui.printErrorMessage("Please check the format of command in the user guide.");
            } catch (InvalidInputException e) {
                ui.printErrorMessage("Please check if the input is correct.");
            } catch (NumberFormatException e) {
                ui.printErrorMessage("Task number should be an integer.");
            } catch (IOException e) {
                ui.printErrorMessage("I'm sorry, but there is an error in writing the file.");
            } catch (DukeException e) {
                ui.printErrorMessage("Error...");
            }
            ui.printDivider();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
