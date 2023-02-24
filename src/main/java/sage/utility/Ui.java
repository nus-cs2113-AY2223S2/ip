package sage.utility;

import java.util.Scanner;

/**
 * A class that represents user interaction methods.
 */

public class Ui {

    private static Display display = new Display();
    private static TaskList taskList = new TaskList();

    private static Storage fm = new Storage();


    /**
     * Retrieves and restore any available task information data stored in ./data/sage.txt
     */
    public void recoverData() {
        fm.recoverData(taskList);
    }

    public String readInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Reads the parsed user input and delegate the respective operation based on the task type
     *
     * @param parsed a Parser object that contains parsed user input
     * @return boolean that indicates whether to exit the application
     */
    public boolean readCommand(Parser parsed) {
        switch (parsed.getTaskType()) {
        case "bye":
            display.printGoodByeUser();
            return true;
        case "list":
            taskList.listTask();
            break;
        case "unmark":
            taskList.markingTask(parsed.getTaskDescription(), false);
            break;
        case "mark":
            taskList.markingTask(parsed.getTaskDescription(), true);
            break;
        case "delete":
            taskList.deleteTask(parsed.getTaskDescription());
            break;
        case "todo":
            taskList.addTask(parsed.getTaskDescription(), false, false);
            break;
        case "deadline":
            taskList.addTask(parsed.getTaskDescription(), parsed.getBy(), false, false);
            break;
        case "event":
            taskList.addTask(parsed.getTaskDescription(), parsed.getFrom(), parsed.getTo(), false, false);
            break;
        case "find":
            taskList.findTask(parsed.getTaskDescription());
            break;
        default:
            display.printUnknownInput();
            break;
        }
        return false;
    }

}
