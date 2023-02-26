import Handlers.StorageManager;
import Handlers.Ui;
public class DukeRunner {

    public static void main(String[] args) {
        StorageManager.loadFileContents();
        Ui.takeUserInputs();
        StorageManager.saveFileContents();
    }
}
