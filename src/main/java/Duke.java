import command.Command;
import components.Parser;
import components.Storage;
import components.TaskList;
import components.UI;
import exception.DukeException;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private final UI ui;
    private TaskList tasks;

    private Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.greet();
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        while (!isExit) {
            try {
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