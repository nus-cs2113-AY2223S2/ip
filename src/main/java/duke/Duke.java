package duke;

import duke.instructions.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String FILE_PATH = "data/tasks.txt";
    
    private static final ArrayList<Task> taskNameList = new ArrayList<>();


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
            isRunning = Parser.checkInput(taskNameList);

        }
        Storage.writeTaskToFile(FILE_PATH, taskNameList);
        Parser.sayBye();

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
