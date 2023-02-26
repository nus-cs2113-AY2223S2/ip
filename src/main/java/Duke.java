import duke.data.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.UI;

import java.io.IOException;

/**
 * Main class to manage all operations
 */
public class Duke {
    public boolean isEnd = false;
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Start the duke programme
     * @throws IOException if an error occurred while loading save file
     */
    public static void main(String[] args) throws IOException {
        new Duke("./taskSave.txt").run();
    }

    /**
     * Constructor to create new UI, storage and task list objects
     * @param filepath path of the previous save file
     */
    public Duke(String filepath) {
        ui = new UI();
        storage = new Storage(filepath);
        tasks = new TaskList();
    }

    /**
     * Loads the save file and executes duke programme
     * @throws IOException if an error occurred while loading the save file
     */
    public void run() throws IOException {
        try {
            storage.createSaveFile();
        } catch (IOException exception) {
            System.out.println("☹ OOPS!!! An error has occurred while loading the save file");
        }
        storage.loadSaveFile(tasks);
        ui.printHelloStatement();
        while (!isEnd) {
            String input = ui.getInput();
            Parser parser = new Parser(input);
            parser.handleCommand(tasks, ui);
            storage.updateSaveFile(tasks);
            isEnd = parser.isByeCommand;
        }
    }
}