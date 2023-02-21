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
    public static final String SAVE_FILE_DOES_NOT_EXIST = "     ☹ OOPS!!! The save file could not be loaded as it " +
            "does not exist.";
    public static final String SAVE_FILE_LOAD_ERROR = "     ☹ OOPS!!! An error has occurred, the current task list " +
            "will not be saved in a file!";
    public static final String FILE_WRITE_ERROR = "     ☹ OOPS!!! There is an error writing to the file :-(";

    public static final String INVALID_COMMAND_FORMAT = "     ☹ OOPS!!! Invalid command format! Remember the format: "
            + System.lineSeparator() + "     deadline <description> /by <due date>" + System.lineSeparator() +
            "     event <description> /from <start date/time> /to <end date/time>";
    public static final String INVALID_TASK_NUMBER = "     ☹ OOPS!!! This task number is invalid! " +
            System.lineSeparator() + "     Please enter a task number in the valid range.";
    public static final String EMPTY_TASK_NUMBER = "     ☹ OOPS!!! Please provide a task number! Remember this format:"
            + System.lineSeparator() + "     mark <taskNumber>" + System.lineSeparator() +
            "     unmark <taskNumber>" + System.lineSeparator() +  "     delete <taskNumber>";
    protected TaskList taskList;
    protected FileDataHandler fileDataHandler;
    protected Ui ui;

    /**
     * Creates new TaskList, Ui and FileDataHandler objects given the filePath and name of directory.
     * Creates and loads the saved file data, if the file does not exist, throws an error message.
     *
     * @param filePath the path to the .txt file where the saved data will be stored.
     * @param directoryName The name of the folder where the saved file is to be located.
     */
    public Duke(String filePath, String directoryName) {
        taskList = new TaskList();
        fileDataHandler = new FileDataHandler(filePath, directoryName);
        ui  = new Ui();
        try {
            if(!fileDataHandler.createFile()) {
                fileDataHandler.loadFile(taskList);
            }
        } catch (FileNotFoundException exception) {
            ui.printErrorMessage(SAVE_FILE_DOES_NOT_EXIST);
        } catch (IOException exception) {
            ui.printErrorMessage(SAVE_FILE_LOAD_ERROR);
        }
    }

    /**
     * Starts the Duke chatbot, begins to wait for inputs by the user to pass to the Parser class to
     * execute the commands. The loop finishes upon user keying in command "bye" and triggers an exit.
     * This also catches exceptions thrown by the Parser and TaskList class during execution.
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
                ui.printErrorMessage(FILE_WRITE_ERROR);
            } catch (StringIndexOutOfBoundsException exception) {
                ui.printErrorMessage(INVALID_COMMAND_FORMAT);
            } catch (IndexOutOfBoundsException exception) {
                ui.printErrorMessage(INVALID_TASK_NUMBER);
            } catch (NumberFormatException exception) {
                ui.printErrorMessage(EMPTY_TASK_NUMBER);
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

