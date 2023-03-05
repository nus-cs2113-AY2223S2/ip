package duke.main;

import duke.entity.Storage;
import duke.entity.TaskList;
import duke.entity.Ui;
import duke.exceptions.DukeException;

/**
 * A class that acts as a CLI that keeps tracks of tasks you write and mark down
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.toString());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greetings();
        boolean exited = true;
        while (exited) {
            exited = ui.manageInput(tasks, storage);
        }
        ui.goodbye();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}