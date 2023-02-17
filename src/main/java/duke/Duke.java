package duke;

import duke.command.ExitCommand;
import duke.data.Storage;
import duke.command.Command;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;
import java.io.File;

public class Duke {
    private Ui ui; // todo improve
    private Storage storage; // todo improve
    private TaskList taskList;

    private void startUp() {
        try {
            this.ui = new Ui();
            this.storage = new Storage();
            storage.makeDirectory(); // make dir if it does not exist
            File dataFile = storage.openDataFile(); // make file if it does not exist
            this.taskList = new TaskList(storage.importData(dataFile)); // import the data from file to program
            Ui.showGreeting();
        } catch (Exception e) {
            // todo print saying not able to start, failure in storage management
            System.exit(1);
        }

    }

    public void run() {
        startUp();
        // user to input update
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals(ExitCommand.COMMAND_WORD)) {
            Command.evaluate(input, this.taskList);
            input = in.nextLine();
        }
        ExitCommand.exit(this.taskList);
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}