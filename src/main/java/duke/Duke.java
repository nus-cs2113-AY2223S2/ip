package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import static duke.storage.Storage.writeToFile;

public class Duke {

    public static void main(String[] args) {
        TaskList tasks = new TaskList();
        Ui.printGreeting();
        Storage.readFileData(tasks);

        do {
            Parser.handleCommand(tasks);
        } while(Parser.isRunning());

        Ui.printExit();
        writeToFile(tasks);
    }
}
