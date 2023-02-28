package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.Task;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents Duke, the personal assistant chatbot
 * that can help keep track of things.
 */
public class Duke {

    /**
     * The main function for 'Duke' to run.
     * @param args
     */
    public static void main(String[] args) {
        Ui.printGreet();
//        try {
//            Storage.load();
//        } catch (RuntimeException e) {
//            System.out.println("Unable to load due to runtime exception :(");
//        }
        Storage.load();
        Parser.getCommand();
        Ui.printBye();
    }
}
