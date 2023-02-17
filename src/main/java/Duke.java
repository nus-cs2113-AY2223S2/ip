import exception.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the attributes of the chatbot Duke. Every instantiation of a new Duke object
 * allows features such as adding, deleting, listing, loading, saving, marking and unmarking of
 * different types of tasks into a task list.
 */
public class Duke {
    protected TaskList taskList;
    protected FileDataHandler fileDataHandler;
    protected Ui ui;

    public Duke(String filePath, String directoryName) {
        taskList = new TaskList();
        fileDataHandler = new FileDataHandler(filePath, directoryName);
        ui  = new Ui();
        try {
            if(!fileDataHandler.createFile()) {
                fileDataHandler.loadFile(taskList);
            }
        } catch (FileNotFoundException exception) {
            ui.printErrorMessage("     ☹ OOPS!!! The save file could not be loaded as it does not exist.");
        } catch (IOException exception) {
            ui.printErrorMessage("     ☹ OOPS!!! As an error has occurred, the current task list will not be saved in a file!");
        }
    }

    /**
     * Starts the Duke chatbot, begins to wait for inputs by the user to pass to the Parser class to
     * execute the commands. The loop finishes upon user keying in command "bye" and triggers an exit.
     * This also catches exceptions thrown by the Parser class during execution.
     */
    public void run() {
        ui.welcomeMessage();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            Parser parseInput = new Parser(this);
            String[] nextInput = input.nextLine().split(" ", 2);
            boolean isExit;
            try {
                isExit = parseInput.parse(nextInput);
                if(isExit) {
                    break;
                }
                fileDataHandler.saveFile(taskList);
            } catch (DukeException exception) {
                ui.printErrorMessage(exception.getMessage());
            } catch (ArrayIndexOutOfBoundsException exception) {
                //for the case of no space after calling a command
                ui.printErrorMessage("     ☹ OOPS!!! The description of a " + nextInput[0] + " cannot be empty.");
            } catch (IOException exception) {
                ui.printErrorMessage("     ☹ OOPS!!! There is an error writing to the file :-(");
            }
        }
        ui.farewellMessage();
    }

    /**
     * Instantiates a new Duke object that takes in a relative filepath of data/duke.txt and a directoryName to load
     * and store the task list into duke.txt.
     * Calls the run() method to begin a session with the Duke chatbot.
     *
     * @param args String array that takes in arguments from the command line when the application is running.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt", "data").run();
    }

}

