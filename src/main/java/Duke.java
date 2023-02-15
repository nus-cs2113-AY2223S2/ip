import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import util.Task;
import util.Ui;
import util.Storage;
import util.Parser;
import util.TaskList;

public class Duke {
    private Ui ui;
    public static void main(String[] args) {
        Ui ui =new Ui();
        ui.printWelcomeMessage();
        Storage.initializeFile();
        ArrayList<Task> commands = TaskList.createList();
        Parser parser = new Parser();
        try {
            commands = Storage.loadDataFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        boolean isExit = false;

        while (!isExit) {
            String line = ui.ask();
            commands = parser.answerCommand(line, commands);
            isExit = parser.IsExit();
            try {
                Storage.updateDatafile(commands);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
