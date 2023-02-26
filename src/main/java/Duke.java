
import DukeFunctions.Storage;
import DukeFunctions.TaskList;
import DukeFunctions.Ui;
import DukeFunctions.Parser;
import Exceptions.DukeError;


import java.util.Scanner;

/**
 * The Duke class represents a task list.
 * This class uses a TaskList object to manage tasks, and a Storage object to read and write data
 * to a local file. The Ui class handles user interaction, and the Parser class
 * handles the input.
 */

public class Duke {

    /**
     * Ui object handles user interaction.
     */
    private Ui ui;

    /**
     * TaskList object manages the list of tasks.
     */
    private TaskList tasks;

    /**
     * Storage object reads and writes data to and from a local file.
     */
    private Storage storage;

    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath the file path of the locally saved memory text file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeError e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke app.
     *
     * @throws DukeError if there is an error
     */
    public void run() throws DukeError {
        ui.welcome();
        boolean isExit = false;
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {
            String input = scanner.nextLine();
            try {
                parser.parseCommand(input, tasks, ui);
                isExit = parser.isExit;
            } catch (DukeError e) {
                ui.showError(e);
            }
            try {
                storage.save(tasks);
            } catch (DukeError e) {
                ui.showError(e);
            }
        }
        ui.bye();
    }

    /**
     * The main method of the Duke application.
     *
     * @param args the command line arguments
     * @throws DukeError if there is an error with the app
     */
    public static void main(String[] args) throws DukeError {
        new Duke("memory.txt").run();
    }
}
