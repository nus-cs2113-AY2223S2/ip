package duke;

import duke.exception.FolderNotFoundException;
import duke.exception.NoKeyException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Duke class that contains the main method. Provides a way for users
 * to keep track of the different type of tasks in their daily lives
 */
public class Duke {
    public static void main(String[] args) throws IOException {
        Storage storage = new Storage("data", "data/Duke.txt");
        Ui ui = new Ui();
        Ui.sayHi();
        try {
            storage.load();
        } catch (FileNotFoundException e) {
            Ui.displayErrorFileNotFoundException();
            storage.createNewFile();
        } catch (FolderNotFoundException e) {
            Ui.displayErrorFolderNotFoundException();
            storage.createNewFolder();
        }
        do {
            try {
                ui.readUserInput();
                Parser.parseCommand(ui.getUserInput());
                storage.save();
            } catch (NoKeyException e) {
                Ui.displayErrorNoKeyException();
            } catch (IOException e) {
                Ui.displayErrorIOException();
            }
        } while (!ui.getUserInput().equals("/bye"));
        Ui.sayBye();
    }
}
