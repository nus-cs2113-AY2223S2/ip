package duke;

import duke.command.Command;
import duke.exception.EmptyDescException;
import duke.exception.IllegalCommandException;
import duke.exception.InvalidDateTime;
import duke.exception.InvalidDeadline;
import duke.exception.InvalidEvent;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Main class for running Duke
 */
public class Duke {

    public static final String FILE_PATH = "/Users/linshang/Documents/cs2113/ip/save.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises Duke by loading in data from save file.
     * Sets up Ui, Storage, and TaskList.
     *
     * @param filePath location of the local save file
     */
    public Duke(String filePath) {
        ui = new Ui();
        ui.printWelcomeMessage();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(ui));
            storage.update(tasks);
        } catch (IOException e) {
            ui.printErrorForIO();
        }
    }

    /**
     * Continuously reads, executes, and prints outputs of user commands until ExitCommand is called.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.getExit();
            } catch (IllegalCommandException e) {
                ui.printInvalidCommand();
            } catch (EmptyDescException e) {
                ui.printEmptyDescription();
            } catch (NumberFormatException e) {
                ui.printErrorForIdx(tasks.getSize());
            } catch (InvalidDeadline e) {
                ui.printInvalidDeadline();
            } catch (InvalidEvent e) {
                ui.printInvalidEvent();
            } catch (InvalidDateTime e) {
                ui.printInvalidDateTime();
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method. Initialises and runs Duke
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
        System.exit(0);
    }

}
