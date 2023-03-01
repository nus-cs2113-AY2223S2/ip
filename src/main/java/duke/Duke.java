package duke;

import duke.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static boolean isInUse = true;
    public static Scanner inputReader;

    /*
    private Ui ui = new Ui();
    private TaskList list = new TaskList();
    private Storage storage = new Storage();
    private Parser parser = new Parser();
    */
    public static void main(String[] args) {
        TaskList list = new TaskList();
        Storage.loadData("./data.txt", list.get());
        initDuke(list);
        while (isInUse) {
            String userInput = getUserInput(inputReader);
            String[] informationNeededForPerformingUserRequest = Parser.parseUserInput(userInput);
            performUserRequest(list.get(), informationNeededForPerformingUserRequest);
            Storage.saveData("./data.txt", list.get());
        }
    }

    public static void initDuke(TaskList list) {
        Ui.greetUser();
        Ui.listTasks(list.get());
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
            TaskList.markTask(informationNeededForPerformingUserRequest);
            break;
        case "unmark":
            TaskList.unmarkTask(informationNeededForPerformingUserRequest);
            break;
        case "delete": // new functionality to delete tasks
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
        case "invalid command": // earlier on we detected that such a command doesn't exist
            Ui.printErrorMessage("invalid command");
            break;
        default:
            Ui.printErrorMessage("error with information provided");
            break;
        }
    }

}


