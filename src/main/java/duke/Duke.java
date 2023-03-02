package duke;

import duke.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * The main class. Reads in inputs from the terminal, parses them to find out what the user wants to do, then performs
 * the user's request.
 */
public class Duke {
    public static boolean isInUse = true;
    public static Scanner inputReader;

    /**
     * Main method called when the program is run.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        TaskList list = new TaskList();
        Storage.loadData("./data.txt", list.getTasksList());
        initDuke(list);
        while (isInUse) {
            String userInput = getUserInput(inputReader);
            String[] informationNeededForPerformingUserRequest = Parser.parseUserInput(userInput);
            performUserRequest(list.getTasksList(), informationNeededForPerformingUserRequest);
            Storage.saveData("./data.txt", list.getTasksList());
        }
    }

    /**
     * Initializes Duke where it makes Duke greet the user, displays a task list based on the user's local save file (if
     * there is one) and instantiates a Scanner so that Duke can start receiving inputs from the user.
     *
     * @param list The task list used by Duke to help the user track his/her tasks.
     */
    public static void initDuke(TaskList list) {
        Ui.greetUser();
        Ui.listTasks(list.getTasksList(), "list");
        inputReader = new Scanner(System.in);
    }

    private static String getUserInput(Scanner in) {
        return in.nextLine();
    }

    /**
     * Performs the user's request based on the user's input in the command line interface
     *
     * @param tasks                                     The task list.
     * @param informationNeededForPerformingUserRequest An array of strings which contain key information about what the
     *                                                  user wants to do. This array is obtained after parsing the user's
     *                                                  input in the command line interface.
     */
    public static void performUserRequest(ArrayList<Task> tasks, String[] informationNeededForPerformingUserRequest) {
        switch (informationNeededForPerformingUserRequest[0]) {
        case "bye":
            isInUse = false;
            inputReader.close();
            Ui.printExitMessage();
            break;
        case "list":
            Ui.listTasks(tasks, "list");
            break;
        case "mark":
            TaskList.markTask(informationNeededForPerformingUserRequest);
            break;
        case "unmark":
            TaskList.unmarkTask(informationNeededForPerformingUserRequest);
            break;
        case "delete":
            TaskList.deleteTask(informationNeededForPerformingUserRequest);
            break;
        case "todo":
            TaskList.addToDoTask(informationNeededForPerformingUserRequest);
            break;
        case "deadline":
            TaskList.addDeadlineTask(informationNeededForPerformingUserRequest);
            break;
        case "event":
            TaskList.addEventTask(informationNeededForPerformingUserRequest);
            break;
        case "find":
            TaskList.findTask(informationNeededForPerformingUserRequest);
            break;
        case "invalid command":
            Ui.printErrorMessage("invalid command");
            break;
        default:
            Ui.printErrorMessage("error with information provided");
            break;
        }
    }
}


