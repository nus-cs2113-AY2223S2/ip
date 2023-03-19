import duke.Storage;
import duke.Ui;
import duke.DukeException;
import duke.Todo;
import duke.Parser;

import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws DukeException {

        Ui ui = new Ui();
        Storage storage = new Storage();
        Parser parser = new Parser();

        ui.showGreetingMessage();

        //Set up list to store user inputs
        ArrayList<Todo> tasks = new ArrayList<>();
        int counter = 0;


        //Initialize list with saved data
        String absoluteFilePath = storage.findFilePath();
        counter = storage.initializeList(tasks, counter, absoluteFilePath);

        //Execute program
        parser.parse(tasks, counter);
    }
}













/**
 * Marks a task as done.
 *
 * @param tasks     list of tasks already added.
 * @param fw        file to be modified.
 * @param i         increment for loop.
 * @param classType type of task.
 */


/**
 * Marks a task as done.
 *
 * @param tasks      list of tasks already added.
 * @param taskNumber number assigned to task in the list.
 */

/**
 * Returns counter after initializing list with items that were saved to disk previously.
 * If no such saved file exists, a new file will be created on disk to save list items
 * upon exiting the program.
 *
 * @param tasks   list of tasks already added.
 * @param counter number of tasks in the list.
 * @return counter number of tasks in the list.
 * @throws FileNotFoundException If .txt file is not found at specified file path.
 */

/**
 * Returns user input as a string.
 *
 * @return input string.
 */


/**
 * Prints acknowledgement message when task is marked as not done.
 *
 * @param tasks      list of tasks already added.
 * @param taskNumber number assigned to task in the list.
 */


/**
 * Prints acknowledgement message when task is marked as done.
 *
 * @param tasks      list of tasks already added.
 * @param taskNumber number assigned to task in the list.
 */

/**
 * Returns task number.
 *
 * @return task number.
 */

/**
 * Prints list contents.
 *
 * @param tasks   list of tasks already added.
 * @param counter number of tasks in the list.
 */
