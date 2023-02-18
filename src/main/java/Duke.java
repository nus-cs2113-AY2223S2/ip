import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.getSavedTasks(ui));
        } catch (FileNotFoundException e) {
            ui.showFileLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showGreetings();
        boolean shouldExitChat = false;
        while (!shouldExitChat) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Parser p = new Parser(fullCommand);
                Command c = p.parse();
                c.execute(tasks, ui, storage);
                shouldExitChat = c.shouldExit();
            } catch (DukeException e) {
                ui.showCommandError();
            } finally {
                ui.showLine();
            }
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
