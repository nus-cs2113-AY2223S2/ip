import duke.exception.DukeException;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;
import duke.util.Parser;
import duke.task.Task;
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
            ui.showLoadingError();
            storage.makeDukeDataDirectory();
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    public static void run() throws DukeException {
        ui.printIntro();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.getInput();
            ui.printLine(); // show the divider line ("_______")
            parser.processInput(fullCommand, tasks);
            isExit = Parser.toExit();
            ui.printLine();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }
}
