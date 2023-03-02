package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.Task;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents Duke, the personal assistant chatbot
 * that can help keep track of tasks.
 */
public class Duke {

    /**
     * The main function for 'Duke' to run.
     * @param args
     */
    public static void main(String[] args) {
        Ui.printGreet();
        Storage.load();
        Parser.getCommand();
        Ui.printBye();
    }
}
