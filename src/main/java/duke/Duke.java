package duke;

import duke.task.Task;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();
    static boolean isFileEdited = false;
    static boolean toPrint = true;
    static int taskCount = 0;

    public static void handleUserCommand(String userCommand) {
        String[] extractFirstWord = userCommand.split(" ", 2);
        String firstWord = extractFirstWord[0];
        switch (firstWord) {
        case Ui.COMMAND_MARK:
            try {
                int taskNum = Integer.parseInt(extractFirstWord[1]);
                TaskList.doCommandMark(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_mark_a) {
                Ui.printInvalidNumber("mark");
            }
            break;
        case Ui.COMMAND_UNMARK:
            try {
                int taskNum = Integer.parseInt(extractFirstWord[1]);
                TaskList.doCommandUnmark(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_unmark_a) {
                Ui.printInvalidNumber("unmark");
            }
            break;
        case Ui.COMMAND_LIST:
            TaskList.doCommandList();
            break;
        case Ui.COMMAND_BYE:
            Ui.doCommandBye();
            break;
        case Ui.COMMAND_DELETE:
            try {
                int taskNum = Integer.parseInt(extractFirstWord[1]);
                TaskList.doCommandDelete(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_delete_a) {
                Ui.printInvalidNumber("delete");
            }
            break;
        case Ui.COMMAND_TODO:
            try {
                String taskName = (extractFirstWord[1]);
                TaskList.doCommandTodo(taskName);
            } catch (IndexOutOfBoundsException out_todo_a) {
                Ui.printEmptyCommand("todo");
            }
            break;
        case Ui.COMMAND_DEADLINE:
            try {
                int index = extractFirstWord[1].indexOf("/by");
                String taskName = extractFirstWord[1].substring(0, index);
                String taskDeadline = extractFirstWord[1].substring(index + 4);
                TaskList.doCommandDeadline(taskName, taskDeadline);
            } catch (StringIndexOutOfBoundsException out_deadline_a) {
                Ui.printInvalidFormat("deadline");
            } catch (IndexOutOfBoundsException out_deadline_a) {
                Ui.printEmptyCommand("deadline");
            }
            break;
        case Ui.COMMAND_EVENT:
            try {
                int indexOfEventDetailsPartOne = extractFirstWord[1].indexOf("/from");
                int indexOfEventDetailsPartTwo = extractFirstWord[1].indexOf("/to");
                String eventName = extractFirstWord[1].substring(0, indexOfEventDetailsPartOne);
                String eventDetailsPartOne = extractFirstWord[1].substring(indexOfEventDetailsPartOne + 6, indexOfEventDetailsPartTwo - 1);
                String eventDetailsPartTwo = extractFirstWord[1].substring(indexOfEventDetailsPartTwo + 4);
                TaskList.doCommandEvent(eventName, eventDetailsPartOne, eventDetailsPartTwo);
            } catch (StringIndexOutOfBoundsException out_event_a) {
                Ui.printInvalidFormat("event");
            } catch (IndexOutOfBoundsException out_event_a) {
                Ui.printEmptyCommand("event");
            }
            break;
        default:
            if (toPrint) {
                Ui.printInvalidCommand();
            }
        }
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "duke.txt");
        String absolutePath = path.toString();
        Storage.openFile(absolutePath);
        Ui.doCommandGreet();
        Scanner in = new Scanner(System.in);
        String userCommand;
        do {
            userCommand = in.nextLine();
            handleUserCommand(userCommand);
        } while (!userCommand.equals(Ui.COMMAND_BYE));
        try {
            if (isFileEdited) {
                Storage.doEditFile(absolutePath);
            }
        } catch (IOException e) {
            Ui.printFileCreated(false);
        }
    }
}
