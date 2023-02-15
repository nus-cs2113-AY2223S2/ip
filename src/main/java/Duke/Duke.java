package Duke;

import java.util.Objects;
import java.util.ArrayList;

import static Duke.TaskList.*;
import static Duke.Ui.*;
import static Duke.InputCheckingPackage.InputChecking.*;


public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();
    private static String filePath = "data/tasklist.txt";
    private Ui ui;



    public Duke(String filePath) {
        ui = new Ui();
        //Storage storage = new Storage(filePath);
        try {
            Storage.readFile(filePath, tasks);
        } catch (java.io.FileNotFoundException e) {
            ui.showLoadingError();
        }
    }

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


    public static void main(String[] args) {
        new Duke(filePath).run();
    }

}
