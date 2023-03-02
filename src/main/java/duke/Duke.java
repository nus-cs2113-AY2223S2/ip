package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath, tasks);
    }

    public void run() {
        // greet user
        ui.greet();

        // input saved tasks into tasks list
        System.out.println("Loading saved tasks...");
        try {
            storage.createSavedTasksFile();
            storage.loadSavedTasks();
        } catch (IOException excpetion) {
            System.out.println("I/O Error! ");
        }
        System.out.println("Loading complete!");

        Scanner scan = new Scanner(System.in);
        Parser parse = new Parser();
        while (true) {
            // get task inputted by user
            String[] input = scan.nextLine().split(" ");
            // parse task inputted by user
            parse.parseInput(input, tasks);

            // store task to file
            try {
                storage.saveTaskToFile();
            } catch (IOException e) {
                System.out.println("I/O Error!");
            }

            // check if user is done
            if (parse.getIsUserDone()) {
                break;
            }
        }
        // say bye to user
        ui.bye();
    }

    public static void main(String[] args) {
        new Duke("src/main/duke.txt").run();
    }
}




