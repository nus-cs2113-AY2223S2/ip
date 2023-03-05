package duke;

import duke.instructions.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final ArrayList<Task> taskNameList = new ArrayList<>();
    private static String filePath = "data/tasklist.txt";


    public Duke(String filePath) {

        try {
            Storage.readFile(filePath, taskNameList);
        } catch (java.io.FileNotFoundException e) {
            Ui.showNotFoundError();
        }
    }

    public static void run() {

        Ui.greeting();
        boolean isRunning = true;

        while (isRunning) {
            isRunning = Parser.checkInput(taskNameList, filePath);

        }
        Parser.sayBye();

    }

    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
