package tusky;

import tusky.commands.Command;
import tusky.exceptions.EmptyDescriptionException;
import tusky.exceptions.KeyNotFoundException;
import tusky.parser.Parser;
import tusky.storage.Storage;

import tusky.tasks.TaskList;
import tusky.ui.Ui;

import java.io.IOException;

/**
 * Main class for the Tusky program
 */
public class Tusky {
    private static TaskList tasks;

    private static Storage storage;
    private static Ui ui;

    /**
     * Runs the main loop of the program
     */
    public static void run () {
        boolean isExit = false;
        while (!isExit) {

            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NumberFormatException e){
                ui.showNumberFormatError();
            } catch (IllegalArgumentException e) {
                ui.showUnknownCommand();
            } catch (ArrayIndexOutOfBoundsException e) {
                // something went wrong when parsing the command parameters
                ui.showInvalidParameters();
            } catch (KeyNotFoundException e) {
                ui.showKeyNotFound(e.getMessage());
            } catch (EmptyDescriptionException e) {
                ui.showEmptyDescription(e);
            } catch (IndexOutOfBoundsException e) {
                // an invalid index or index greater than the current length of
                // task list was provided
                ui.showInvalidIndex();
            } catch (Exception e) {
                ui.showUnknownException(e);
            } finally {
                ui.showLine();
            }

        }
    }


    public static void main (String[] args) {
        ui = new Ui();
        storage = new Storage("data/tusky.json", ui);

        ui.showLine();
        ui.showWelcome();
        try {
            tasks = new TaskList(storage.readFile());
            ui.showFileLoaded();
        } catch (IOException e){
            ui.showFileLoadError();
            tasks = new TaskList();
            storage.writeFile(tasks);
        }
        ui.showLine();

        run();

    }
}
