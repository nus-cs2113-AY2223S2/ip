package Duke;

import Duke.command.DukeCommand;
import Duke.task.DukeTaskList;

public class Duke {

    private DukeStorage storage;
    private DukeTaskList tasks;
    private DukeUi ui;

    public Duke(String filePath) {
        ui = new DukeUi();
        storage = new DukeStorage(filePath);
        tasks = new DukeTaskList();
        // TODO: load tasks from file
        // try {
        //     tasks = new DukeTaskList(storage.load());
        // } catch (DukeException e) {
        //     ui.showLoadingError();
        //     tasks = new DukeTaskList();
        // }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                DukeCommand c = DukeParser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run(); // TODO: create jar file
        // TODO: update User Guide and release
    }
}
