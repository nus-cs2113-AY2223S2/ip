import duke.exception.DukeException;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.util.Parser;
import duke.task.Task;
import duke.command.Command;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static Parser parser;



    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readDukeFile());
        } catch (FileNotFoundException e) {
            ui.printLoadingError();
            storage.makeDukeDataDirectory();
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Takes in user inputs from the command line.
     * Program is exited if isExit is true.
     */
    public static void run() throws DukeException {
        ui.printIntro();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = parser.getInput();
            ui.printLine(); // show the divider line ("_______")
            Command c = parser.processInput(fullCommand, tasks);
            c.run(tasks);
            storage.saveDataFromInput(tasks.lists);
            isExit = c.toExit();
            ui.printLine();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }
}
