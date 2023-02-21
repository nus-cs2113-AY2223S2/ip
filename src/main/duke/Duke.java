package duke;

import duke.parser.Parser;
import duke.tasklist.DataManager;
import duke.ui.DukeMessages;
import duke.util.DukeException;

/**
 * Main class for the application.
 * Initializes the application by running method to read datafile.
 */
public class Duke {

    private final DataManager dm;
    private final DukeMessages ui;
    private final Parser parser;

    /**
     * Runs the initialization for the application and runs the application.
     *
     * @param path Path of datafile
     */
    public Duke(String path) {
        ui = new DukeMessages();
        parser = new Parser(ui);
        dm = new DataManager(path, ui, parser);
        dm.initialize();
        run();
    }

    /**
     * Starts user interaction.
     * Prints exit message and ends application when user types <code>bye</code>.
     * Passes all user inputs into Parser object and returns a <code>parsedCommand</code>.
     * <code>parsedCommand</code> is then sent to DataManager object.
     */
    public void run() {
        dm.run();
        do {
            String parsedCommand = parser.run();
            if (parsedCommand.equals("bye")) {
                break;
            }
            ui.printDiv();
            String next;
            try {
                next = parser.check();
            } catch (DukeException e) {
                ui.printError();
                ui.printDiv();
                continue;
            }
            dm.command(parsedCommand, next);
            ui.printDiv();
        } while (true);
        ui.printBye();
    }

    public static void main(String[] args) {
        String path = ".\\data\\tasks.txt";
        new Duke(path);
    }
}