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

    public void run(Storage database, TaskList myList) {
        Ui.printGreetMessage();
        while (!canExit) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            if (s.toLowerCase().equals("bye")) {
                Ui.printByeMessage();
                canExit = true;
            } else if (s.toLowerCase().equals("list")) {
                Ui.printList(myList);
            } else if (s.toLowerCase().startsWith("mark") || s.toLowerCase().startsWith("unmark")) {
                Parser.handleMarkUnmark(s, myList, database);
            } else if (s.toLowerCase().startsWith("todo")) {
                Parser.handleToDo(s, myList);
            } else if (s.toLowerCase().startsWith("deadline")) {
                Parser.handleDeadline(s, myList);
            } else if (s.toLowerCase().startsWith("event")) {
                Parser.handleEvent(s, myList);
            } else if (s.toLowerCase().startsWith("delete")) {
                Parser.handleDeleteTask(s, myList, database);
            } else if (s.toLowerCase().startsWith("find")) {
                Parser.findTask(s, myList);
            } else {
                Ui.printIllegalInputMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Duke();
    }
}