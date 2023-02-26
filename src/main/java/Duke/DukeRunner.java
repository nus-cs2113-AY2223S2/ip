import Handlers.StorageManager;
import Handlers.Ui;
public class DukeRunner {

    public static void main(String[] args) {
        Ui.printHello();
        Ui.printLineBreak();
        StorageManager.loadFileContents();
        Ui.takeUserInputs();
        StorageManager.saveFileContents();
        Ui.printBye();
    }
}
