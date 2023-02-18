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

    private static void runDuke() {
        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();
        while (!input.equals("bye")) {
            Parser.handleCommand(input, tasks);
            input = in.nextLine();
        }
    }

    public static void main(String[] args) {
        Storage.loadData(tasks);
        ui.printWelcomeMessage();
        runDuke();
        ui.printByeMessage();
    }
}

