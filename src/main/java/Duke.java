import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import util.Task;
import util.Ui;
import util.Storage;
import util.Parser;
import util.TaskList;

/**
 * The main class of the Duke chatbot program. This class initializes the user
 * interface,
 * task list, and file storage. It then enters a loop where it receives user
 * input,
 * parses it, and executes the corresponding commands until the user exits the
 * program.
 */
public class Duke {
    private Ui ui;

    public static void main(String[] args) {
        Ui ui = new Ui();
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
