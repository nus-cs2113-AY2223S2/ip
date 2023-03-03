package duke;

/**
 * Class for the main program
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for the main program
     * @param filePath location of the storage file
     */
    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
        ui.showExitMessage();
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}