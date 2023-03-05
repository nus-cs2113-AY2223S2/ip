package duke;

import duke.exceptions.DukeException;

import java.io.IOException;

import static duke.Ui.SEPARATOR;

/**
 * Driver class for Duke, a task manager which is able to add, mark, unmark, delete and find tasks
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Duke(String folderPath, String fileName) throws DukeException, IOException {
        storage = new Storage(folderPath, fileName);
        tasks = new TaskList(storage.loadTaskList());
        ui = new Ui(tasks);
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("data", "taskList.txt").run();
    }

    public void run() {
        ui.showWelcomeMessage();
        // reused from contacts Contacts1.java with modification
        while (true) {
            try {
                String userCommand = ui.getUserInput();
                ui.executeCommand(userCommand);
                storage.saveTaskList(tasks.getTASK_LIST(), storage.data);
            } catch (DukeException err) {
                ui.showToUser(err.ProduceErrorMessage());
                ui.showToUser(SEPARATOR);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
