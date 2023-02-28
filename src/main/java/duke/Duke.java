package duke;

import duke.parser.Parser;
import duke.tasklist.Task;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    private static final String line = "__________________________________________________________";
    private static ArrayList<Task> inputList = new ArrayList<>();
    private static int numTasks = 0;

//    private Storage storage;
//    private Ui ui;
//    private Parser parser;
//    private TaskList taskList;
    private static final String filePath = "data/duke.txt";

    public static void main(String[] args) throws IOException {

        Ui.greet();
        Parser.getCommand();
        Ui.printBye();
    }
}
