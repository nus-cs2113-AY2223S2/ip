package duke;

import duke.task.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import static duke.Storage.*;
import static duke.Ui.*;
import static duke.Parser.*;

public class Duke {

    public static final String FILEPATH = "data.txt";
    private Storage storage;
    private TaskList list;
    private static Ui ui;

    private static Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        parser = new Parser();

    }

    public void run() throws IOException, ClassNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        ui.displayWelcome();
        String line = in.nextLine();
        int numToMark;

        boolean userDone = false;
        loadTasksToFile();

        while (!userDone) {
            userDone = parser.manipulateUserCommand(line, tasks);
            if (!userDone) {
                // Read next line
                line = in.nextLine();
            }
        }
        ui.displayGoodBye();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        new Duke().run();

    }

}
