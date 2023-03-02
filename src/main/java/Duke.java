import io.*;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;
import task.Task;

import java.util.Scanner;

public class Duke {
    private TaskList tasks;
    private final Storage storage;

    private Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(Ui.ERROR_MESSAGE_CANNOT_LOAD);
            tasks = new TaskList();
        }
    }

    private Duke() {
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println("Error");
            tasks = new TaskList();
        }
    }

    /**
     * Runs duke. Takes in a command-line argument on the directory (if any).
     * If there are no command-line arguments, it uses the default directory.
     * @param args First arg should be directory.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            new Duke().run();
        }
        if (Parser.isValidPath(args[0])) {
            new Duke(args[0]).run();
        } else {
            new Duke().run();
        }
    }

    private void run() {
        Ui.printGreeting();

        // Input variables initialised.
        Scanner myScanner = new Scanner(System.in);
        String userInput;

        // The main loop, which ends when user says Bye
        while (true) {
            System.out.print("> ");
            userInput = myScanner.nextLine();

            // Execute command and print out the feedback string.
            String feedback = Parser.executeCommand(tasks, storage, userInput);
            System.out.println(feedback);

            Ui.printHLine();
        }
    }


}
