package duke.Commands;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import dukeException.DukeException;

public class TempDuke extends Command{

    private Storage storage;
    public static final String LINE_SPACING =  "\t____________________________________________________________";

    private Ui ui;

    public TempDuke(String filePath) {
        ui = new Ui();
        storage = new Storage(tasks);
    }

    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.cmd();
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {

        new TempDuke("./data/duke.txt").run();
    }
}
