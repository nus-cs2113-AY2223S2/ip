package duke;

import duke.exception.EmptyTaskException;
import duke.exception.IllegalCommandException;

import java.io.IOException;


public class Duke {
    public static final String FILE_PATH = "/Users/linshang/Documents/cs2113/ip/save.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        Ui.printWelcomeMessage();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            Ui.printErrorForIO();
        }
    }

    public void run() {
        while (true) {
            String fullCommand = ui.readCommand();
            final String[] commandAndParam = Parser.command(fullCommand);
            String command = commandAndParam[0];
            String param = commandAndParam[1];
            try {
                tasks.executeCommand(command, param);
                Storage.updateDuke();
            } catch (IllegalCommandException e) {
                ui.printInvalidCommand();
            } catch (EmptyTaskException e) {
                ui.printEmptyTask();
            } catch (IOException e) {
                ui.printErrorForIO();
            } catch (NumberFormatException e) {
                ui.printErrorForIdx();
            }
        }
    }



    // array list of all tasks


    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }


}
