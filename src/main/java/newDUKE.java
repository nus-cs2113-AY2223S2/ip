import DataManager.Command;
import DataManager.Parser;
import UI.Ui;
import task.Storage;
import task.TaskList;
import Exception.DukeException;

public class newDUKE {
    private TaskList tasks;

    public newDUKE(String filePath) {
        Storage.filePath = filePath;
        tasks = new TaskList();
        Storage.loadExistingData();
    }

    public void run() {
        Ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = Ui.getUserInput();
                Command userCommands = Parser.parseUserInput(userInput);
                isExit = userCommands.getExitStatus();
            } catch (Exception e) {
                Ui.showError(e);
            }
        }
        Ui.bye();
    }

    public static void main(String[] args) {
        new newDUKE("tasks.txt").run();
    }
}
