package duke;

import duke.exception.InvalidCommandException;
import duke.exception.EmptyCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.storage.Storage.*;

/**
 * <h1>DUKE THE CHATBOT</h1>
 * The Duke program is a chatbot that
 * stores and outputs a task list by a single user input.
 * It can add, delete, mark as done, unmark and find relevant
 * tasks in the list.
 * <p>
 *
 * @author  Tang Yinxuan (Sophie)
 * @version 1.0
 * @since   2023-03-03
 */
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

    /**
     * This method is the main method which calls methods of other classes to
     * runs the chatbot process and write the list to a data file.
     * @param Nothing
     * @return Nothing
     * @exception IOException On input error, ClassNotFoundException
     */
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

    /**
     * This is the main method which makes use of run method.
     * @param args Unused
     * @return Nothing
     * @exception IOException On input error, ClassNotFoundException, InvalidCommandException, EmptyCommandException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvalidCommandException, EmptyCommandException {

        new Duke().run();

    }
}
