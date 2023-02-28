import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static final String filePath = "data/tasks.txt";

    /**
     * Attempts to create a TaskList object which contains tasks that are previously
     * saved in the tasks.txt file. If the tasks.txt file cannot be read, create a
     * new TaskList object.
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.getSavedTasks(ui));
        } catch (FileNotFoundException e) {
            ui.showFileLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke ChatBot and continuously prompts the user for
     * commands until the user gives a "bye" command.
     */
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
        new Duke().run();
    }
}
