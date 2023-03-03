package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Represents Duke, the personal assistant chatbot
 * that can help keep track of tasks.
 */
public class Duke {

    /**
     * The main function for 'Duke' to run.
     * @param args String array of arguments
     */
    public static void main(String[] args) {
        Ui.printGreet();
        Storage.load();
        Parser.getCommand();
        Ui.printBye();
    }
}
