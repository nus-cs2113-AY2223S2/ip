package siri;

import siri.exception.AddTaskIndexOutOfBounds;
import siri.exception.MarkerArrayIndexOutOfBoundsException;
import siri.exception.UnknownCommandException;
import siri.general.Parser;
import siri.task.*;
import siri.general.Ui;
import siri.general.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Entry point of the Task application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    /** file path of the file that stores the lists*/
    private static final String TASK_FILE = "data/tasklists.txt";
    private Ui ui;
    public static Storage storage;
    public static TaskList tasks;
    public static int indexOfTask = 0;
    public static boolean isExit = false;

    /**
     * Reads the user command and executes it, until the user issues the exit command (isExit == true).
     */
    public void runCommandLoopUntilExitCommand() {
        while (isExit == false) {
            String input = ui.readCommand();

            ui.drawSiriChatBox();

            try {
                Parser p = new Parser(input);
                p.parse();
            } catch (UnknownCommandException e) {
                ui.showError(e.printError());
            } catch (AddTaskIndexOutOfBounds e) {
                ui.showError(e.printError());
            } catch (MarkerArrayIndexOutOfBoundsException e) {
                ui.showError(e.printError());
            } catch (NumberFormatException e) {
                System.out.println("Please mark / unmark / delete each task one by one, in the following format: \n");
                System.out.println("For example if you want to mark / unmark task 2: mark 2 / unmark 2");
                System.out.println("For example if you want to delete task 2: delete 2");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please add the tasks in the following format: \n");
                System.out.println("Todo task format: todo task_name");
                System.out.println("Deadline task format: deadline deadline_name /by deadline_date");
                System.out.println("Event task format: event event_name /from event_from_timing /to event_to_timing \n");

                System.out.println("Please find the task in the following format: \n");
                System.out.println("find keyword");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please only mark / unmark / delete task that is available in your task list.");
                System.out.println("You have up to " + indexOfTask + " number of tasks.");
            } catch (IOException e) {
                ui.showLoadingError();
            }
            ui.drawStraightLine();
        }
    }

    /**
     * Read the file and load data (tasks) into task list.
     * If file is not created, create a new file and create an empty task list.
     *
     * @param filePath
     * @throws IOException
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            storage.createFile();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program until termination
     *
     * @throws IOException
     */
    public void run() throws IOException {
        ui.greet();
        runCommandLoopUntilExitCommand();
        storage.overwriteEntireList();
    }

    public static void main(String[] args) throws IOException {
        new Duke(TASK_FILE).run();
    }
}