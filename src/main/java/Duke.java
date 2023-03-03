import tasks.TaskList;
import storage.Storage;
import command.Command;
import parser.Parser;
import ui.Ui;

/**
 * Represents the task list tracking programme. A <code>Duke</code> object corresponds to
 * a task list tracker.
 */
public class Duke {

    private TaskList tasks;

    /**
     * Class constructor specifying filePath for saving data.
     *
     * @param filePath a relative file path giving the location of the saved file.
     */
    public Duke(String filePath) {
        Storage.setFilePath(filePath);
        try {
            Storage.createSavedFile();
            tasks = Storage.readFileContents();
        } catch (Exception e) {
            Ui.showLoadingErrorMessage(e);
            tasks = new TaskList();
        } finally {
            Ui.showLine();
        }
    }

    /**
     * Starts the Duke programme and continuously take in user inputs until
     * it is terminated by the user.
     */
    public void run() {
        Ui.showWelcome();
        Ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Ui.readCommand();
                Ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parseCommands(fullCommand);
                c.execute(tasks);
                Storage.saveToFile(tasks);
                isExit = c.isExit();
            } catch (Exception e) {
                Ui.showDudeMainError(e);
            } finally {
                Ui.showLine();
            }
        }
    }

    /**
     * The main method that runs the entire programme.
     */
    public static void main(String[] args) {
        new Duke("data/taskList.txt").run();
        System.exit(0);
    }
}
