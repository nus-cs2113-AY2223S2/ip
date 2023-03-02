import tasks.TaskList;
import storage.Storage;
import command.Command;
import parser.Parser;
import ui.Ui;

public class Duke {

    private TaskList tasks;

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

    public static void main(String[] args) {
        new Duke("data/taskList.txt").run();
        System.exit(0);
    }
}
