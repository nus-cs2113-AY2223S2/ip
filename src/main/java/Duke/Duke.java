package Duke;

import java.util.Objects;
import java.util.ArrayList;

import static Duke.InputCheckingPackage.InputChecking.isValidInput;
import static Duke.TaskList.readTask;
import static Duke.TaskList.checkForAdditionalTask;
import static Duke.Ui.greetUser;
import static Duke.Ui.showNumberOfTasks;
import static Duke.Ui.printHorizontalBar;
import static Duke.Ui.sayBye;
import static Duke.InputCheckingPackage.InputChecking.isValidInput;

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
        greetUser();
        boolean isRunning = true;

        while (isRunning) {
            isRunning = getInput();

        }
        Storage.writeFile(filePath, tasks);
        sayBye();

    }

    /**
     * Reads the users input until the user wants to exit the application
     * @return boolean to determine if the application should continue running
     */
    private static boolean getInput() {
        String command;
        command = readTask();
        boolean isValidInput = isValidInput(command);
        while (!isValidInput) {
            command = readTask();
            isValidInput = isValidInput(command);
        }
        Parser.respondToInput(command);
        showNumberOfTasks(tasks);
        printHorizontalBar();
        if (Objects.equals(command, "bye")) {
            return false;
        }
        return true;
    }

    /**
     * Function to run the application
     * @param args
     */

    public static void main(String[] args) {
        new Duke(filePath).run();
    }

}
