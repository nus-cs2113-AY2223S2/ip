package duke;

import duke.outputs.Messages;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.UI;
import duke.file.TaskList;

import java.io.IOException;

/**
 * Main class to manage all operations
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;
    public boolean isExit = false;

    /**
     * Constructor to create new UI, storage and tasklist objects
     * @param filepath filepath of the existing text file
     */
    public Duke(String filepath) {
        ui = new UI();
        storage = new Storage(filepath);
        tasks = new TaskList();
    }

    /**
     * Loads the text file and executes the program
     * @throws IOException if error occurs during the file load
     */
    public void run() throws IOException {
        try {
            storage.createTextFile();
        } catch (IOException exception) {
            Messages.taskLoadErrorMessage();
        }
        storage.loadTextFile(tasks);
        UI.showWelcomeMessage();
        while (!isExit) {
            String input = ui.getUserInput();
            Parser parser = new Parser(input);
            parser.runCommand(tasks, ui);
            storage.updateTextFile(tasks);
            isExit = parser.isByeCommand;
        }
    }

    /**
     * Command to start the program
     * @throws IOException if error occurs during the file load
     */
    public static void main(String[] args) throws IOException {
        new Duke("./duke.txt").run();
    }

}






