package Duke;

import java.util.Objects;
import java.util.ArrayList;

import static Duke.TaskList.*;
import static Duke.Ui.*;
import static Duke.InputCheckingPackage.InputChecking.*;

/**
 * Duke is a Command Line Application that helps user keep track of things to do,
 * and deadlines ar events they might have
 */

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();
    private static String filePath = "data/tasklist.txt";
    private Ui ui;

    /**
     * Initialises Duke application and reads for exsiting tasks that
     * are saved in a text file
     * @param filePath path of the text file that contains previous task information
     */

    public Duke(String filePath) {
        ui = new Ui();
        
        try {
            Storage.readFile(filePath, tasks);
        } catch (java.io.FileNotFoundException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Runs the application and reads the user input and carries out the related
     * commands until the user chooses to exit the application
     */
    public static void run() {
        Storage storage = new Storage(filePath);

        String command;
        String latestResponse = "";
        boolean hasAdditionalTask = false;
        greetUser();
        boolean isRunning = true;



        while (true) {
            command = latestResponse;


            if (!hasAdditionalTask) {
                System.out.println("What can I do for your today?");
                command = readTask();
            }
            boolean isValidInput = checkForValidInput(command);

            while (!isValidInput) {
                command = readTask();
                isValidInput = checkForValidInput(command);
            }

            Parser.respondToInput(command);
            showNumberOfTasks(tasks);
            printHorizontalBar();
            latestResponse = checkForAdditionalTask();
            hasAdditionalTask = true;
            if (Objects.equals(latestResponse, "no") || Objects.equals(latestResponse, "bye")) {
                break;
            }

        }
        Storage.writeFile(filePath, tasks);
        sayBye();

    }

    /**
     * Function to run the application
     * @param args
     */

    public static void main(String[] args) {
        new Duke(filePath).run();
    }

}
