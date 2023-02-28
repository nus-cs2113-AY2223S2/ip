package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.Task;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    private static final String line = "__________________________________________________________";
    private static ArrayList<Task> inputList = new ArrayList<>();

    private static final String filePath = "data/duke.txt";

    public static void main(String[] args) throws IOException {
        Ui.greet();
        try {
            Storage.load();
        } catch (RuntimeException e) {
            System.out.println("Unable to load due to runtime exception :(");
        }
        Parser.getCommand();
        Ui.printBye();
    }
}
