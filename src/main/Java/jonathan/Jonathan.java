package jonathan;

import command.Command;
import task.TaskList;

public class Jonathan {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jonathan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JonathanException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Jonathan("data/jonathan.txt").run();
    }
}

