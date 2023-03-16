import duke.exception.CommandNotFoundException;
import duke.exception.DukeException;
import duke.exception.IllegalFormatException;
import duke.exception.InvalidInputException;
import duke.exception.MissingInputException;

public class Duke {

    protected UI ui = new UI();
    protected TaskList taskList = new TaskList();
    protected Parser parser = new Parser();

    public void run() {
        ui.printHelloMessage();
        boolean proceedToNextCommand = true;

        while (proceedToNextCommand) {
            String userInput = UI.readCommand();
            ui.printDivider();
            try {
                proceedToNextCommand = parser.executeCommand(userInput);
                if (userInput.equals("bye")) {
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
