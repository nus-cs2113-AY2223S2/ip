import duke.database.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Parser;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static boolean canExit = false;
    private Storage database;
    private TaskList tasks;

    public Duke() {
        try {
            database = new Storage();
        } catch (IOException e) {
            Ui.printInitialiseErrorMessage();
        }
        tasks = new TaskList(database.tasks);
        run(database, tasks);
    }

    /**
     * Function to run the program by reading in inputs from user and calling the respective functions.
     *
     * @param database  the data stored in txt file
     * @param myList    the list of tasks
     */
    public void run(Storage database, TaskList myList) {
        Ui.printGreetMessage();
        while (!canExit) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            String command = s.toLowerCase();

            if (command.equals("bye")) {
                Ui.printByeMessage();
                canExit = true;
            } else if (command.equals("list")) {
                Ui.printList(myList);
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                Parser.handleMarkUnmark(s, myList, database);
            } else if (command.startsWith("todo")) {
                Parser.handleToDo(s, myList);
            } else if (command.startsWith("deadline")) {
                Parser.handleDeadline(s, myList);
            } else if (command.startsWith("event")) {
                Parser.handleEvent(s, myList);
            } else if (command.startsWith("delete")) {
                Parser.handleDeleteTask(s, myList, database);
            } else {
                Ui.printIllegalInputMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Duke();
    }
}