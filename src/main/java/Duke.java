import Command.Command;
import Entities.TaskList;
import EntityUtils.Parser;
import FileUtils.Storage;
import Output.UI;
import Exceptions.DukeException;

/**
 * Chatbot made for CS2113 AY22/23 Sem 2
 * @author Koh Ming En
 * @since 2023-02-19
 */
public class Duke {
    private static final String FILE_PATH = "data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Constructor for chatbot
     * @param filePath path to database
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot
     */
    public void run() {
        ui.printIntroduction();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}