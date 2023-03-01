import command.Command;
import components.Parser;
import components.Storage;
import components.TaskList;
import components.Ui;
import exception.DukeException;

import java.util.Scanner;

public class Duke {
    public Storage storage;
    public final Ui ui;
    public TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                Scanner in = new Scanner(System.in);
                String fullCommand = ui.readCommand(in);
                ui.printHorizontalLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printHorizontalLine();
            }
        }
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}