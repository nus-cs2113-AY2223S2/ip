package duke;

import java.io.IOException;
import java.util.ArrayList;

import static duke.Storage.writeTasksToFile;
import static duke.Ui.*;
import static duke.Ui.UNMARK_MSG;

/**
 * <h1>Parser</h1>
 * The Parser class deals with extracting and interpreting the user input.
 * <p>
 *
 * @author  Tang Yinxuan (Sophie)
 * @version 1.0
 * @since   2023-03-03
 */

public class Parser {

    public int item = 0;

    public Parser() {
        this.item = item;

    }

    /**
     * This method takes the user input and tasks arraylist,
     * reads the user input and runs relevant methods to deal with the relevant input.
     * It returns the boolean status of whether the user has finished his/her input session.
     *
     * @param String line - the user input, ArrayList tasks - the collection that stores all the user's tasks
     * @return boolean of whether the user has finished his/her input session
     * @exception IOException On input error, ClassNotFoundException
     */
    public boolean manipulateUserCommand(String line, ArrayList tasks) throws IOException, ClassNotFoundException {
        Ui ui = new Ui();

        if (line.equals("bye")) {
            return true;
        }

        if (!(line.toLowerCase().startsWith("find") || line.toLowerCase().startsWith("event") || line.toLowerCase().startsWith("delete") || line.toLowerCase().startsWith("deadline") || line.toLowerCase().startsWith("todo") || line.equals("list") || line.toLowerCase().startsWith("mark") || line.toLowerCase().startsWith("unmark"))) {
            ui.displayUnrecognisedWord();
            return false;

        } else {
            // Add event
            if (line.toLowerCase().startsWith("event")) {
                item = TaskList.addEvent(line, item, tasks);
                displayNumItemsInList(item);

            }
            // Add deadline
            if (line.toLowerCase().startsWith("deadline")) {
                item = TaskList.addDeadline(line, item, tasks);
                displayNumItemsInList(item);

            }
            // Add todo
            if (line.toLowerCase().startsWith("todo")) {
                item = TaskList.addToDo(line, item, tasks);
                displayNumItemsInList(item);

            }

            // Display list
            if (line.equalsIgnoreCase("list")) {
                displayList(tasks);
            }

            // Mark item as done: constraint - user input begins with "mark"
            if (line.toLowerCase().startsWith("mark")) {
                TaskList.toggleDoneStatus(line, item, MARK_MSG, true, tasks);
            }
            // Unmark item: constraint - user input begins with "mark"
            if (line.toLowerCase().startsWith("unmark")) {
                TaskList.toggleDoneStatus(line, item, UNMARK_MSG, false, tasks);
            }

            // Delete item
            if (line.toLowerCase().startsWith("delete")) {
                item = TaskList.deleteTask(line, item, tasks);
            }

            // Find item
            if (line.toLowerCase().startsWith("find")) {
                TaskList.findTasks(line, tasks);
            }

            Storage.writeTasksToFile(tasks);
            return false;

        }

    }
}
