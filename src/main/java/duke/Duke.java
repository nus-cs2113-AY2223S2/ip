package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static final Ui ui = new Ui();
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Takes input from the user and parses it.
     * Executes Duke until user inputs the bye command.
     */
    private static void runDuke() {
        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();
        while (!input.equals("bye")) {
            Parser.handleCommand(input, tasks);
            input = in.nextLine();
        }
    }

    /**
     * Reads the file and loads the saved data to the task list.
     * Greets the user and runs duke to execute user's commands.
     * Exits Duke by greeting the user with a bye message.
     */
    public static void main(String[] args) {
        Storage.loadData(tasks);
        ui.printWelcomeMessage();
        runDuke();
        ui.printByeMessage();
    }
}

