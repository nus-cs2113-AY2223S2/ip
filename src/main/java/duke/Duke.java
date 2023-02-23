package duke;

import duke.command.Command;
import duke.exception.EmptyTaskException;
import duke.exception.IllegalCommandException;
import duke.exception.InvalidDeadline;
import duke.exception.InvalidEvent;
import duke.task.Storage;
import duke.task.TaskList;

import java.io.IOException;

public class Duke {
    public static final String FILE_PATH = "/Users/linshang/Documents/cs2113/ip/save.txt";
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            Ui.printErrorForIO();
        }
    }

    public void run() {
        ui.printWelcomeMessage();
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
            } catch (EmptyTaskException e) {
                ui.printEmptyTask();
            } catch (NumberFormatException e) {
                ui.printErrorForIdx(tasks.getSize());
            } catch (InvalidDeadline e) {
                ui.printInvalidDeadline();
            } catch (InvalidEvent e) {
                ui.printInvalidEvent();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
        System.exit(0);
    }
}
