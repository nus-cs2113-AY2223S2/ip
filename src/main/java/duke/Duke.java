package duke;

import duke.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    // private static ArrayList<Task> tasks = new ArrayList<>();
    public static boolean isInUse = true;
    public static Scanner inputReader;
    static final String DEADLINE_BY_DELIMITER = " /by ";
    static final String EVENT_FROM_DELIMITER = " /from ";
    static final String EVENT_TO_DELIMITER = " /to ";
    static final int EVENT_START_INDEX = 7;
    static final int EVENT_END_INDEX = 5;
    static final String SPACE_DELIMITER = " ";
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        Storage.loadData(tasks, "./data.txt");
        initDuke();
        while (isInUse) {
            String userInput = getUserInput(inputReader);
            String[] informationNeededForPerformingUserRequest = Parser.parseUserInput(userInput);
            performUserRequest(tasks, informationNeededForPerformingUserRequest);
            Storage.saveData("./data.txt", tasks);
        }
    }

    public static void initDuke() {
        Ui.greetUser();
        Ui.listTasks(tasks);
        inputReader = new Scanner(System.in);
    }

    private static String getUserInput(Scanner in) {
        return in.nextLine();
    }

    // probably put into TaskList
    public static void performUserRequest(ArrayList<Task> tasks, String[] informationNeededForPerformingUserRequest) {
        switch (informationNeededForPerformingUserRequest[0]) {
        case "bye":
            isInUse = false;
            inputReader.close();
            Ui.printExitMessage();
            break;
        case "list":
            Ui.listTasks(tasks);
            break;
        case "mark":
            TaskList.markTask(tasks, informationNeededForPerformingUserRequest);
            break;
        case "unmark":
            TaskList.unmarkTask(tasks, informationNeededForPerformingUserRequest);
            break;
        case "delete": // new functionality to delete tasks
            TaskList.deleteTask(tasks, informationNeededForPerformingUserRequest);
            break;
        case "todo":
            TaskList.addTodoTask(tasks, informationNeededForPerformingUserRequest);
            break;
        case "deadline":
            TaskList.addDeadlineTask(tasks, informationNeededForPerformingUserRequest);
            break;
        case "event":
            TaskList.addEventTask(tasks, informationNeededForPerformingUserRequest);
            break;
        case "invalid command": // earlier on we detected that such a command doesn't exist
            Ui.printErrorMessage("invalid command");
            break;
        default:
            Ui.printErrorMessage("error with information provided");
            break;
        }
    }

}


