import Command.Command;
import Entities.TaskList;
import EntityUtils.Parser;
import FileUtils.Storage;
import Output.UI;
import Exceptions.*;

public class Duke {
    private static final String filePath = "data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printIntroduction();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}