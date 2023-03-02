import DataManager.Command;
import DataManager.Parser;
import UI.Ui;
import task.Storage;
import task.TaskList;

public class DUKE {
    private TaskList tasks;

    public DUKE(String filePath) {
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
        new DUKE("data.txt").run();
    }
}
