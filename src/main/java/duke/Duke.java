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
        Parser parser = new Parser(userCommand);

        String command = parser.extractCommand();

        switch (command) {
        case Ui.COMMAND_MARK:
            try {
                String commandInfo = parser.extractCommandInfo();
                int taskNum = Integer.parseInt(commandInfo);
                TaskList.doCommandMark(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_mark_a) {
                Ui.printInvalidNumber("mark");
            }
            break;
        case Ui.COMMAND_UNMARK:
            try {
                String commandInfo = parser.extractCommandInfo();
                int taskNum = Integer.parseInt(commandInfo);
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
                String commandInfo = parser.extractCommandInfo();
                int taskNum = Integer.parseInt(commandInfo);
                TaskList.doCommandDelete(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_delete_a) {
                Ui.printInvalidNumber("delete");
            }
            break;
        case Ui.COMMAND_TODO:
            try {
                String commandInfo = parser.extractCommandInfo();
                TaskList.doCommandTodo(commandInfo);
            } catch (IndexOutOfBoundsException out_todo_a) {
                Ui.printEmptyCommand("todo");
            }
            break;
        case Ui.COMMAND_DEADLINE:
            try {
                String commandInfo = parser.extractCommandInfo();
                String taskName = parser.extractTaskName();
                String taskDeadline = parser.extractTaskDeadline();
                TaskList.doCommandDeadline(taskName, taskDeadline);
            } catch (StringIndexOutOfBoundsException out_deadline_b) {
                Ui.printInvalidFormat("deadline");
            } catch (IndexOutOfBoundsException out_deadline_a) {
                Ui.printEmptyCommand("deadline");
            }
            break;
        case Ui.COMMAND_EVENT:
            try {
                String commandInfo = parser.extractCommandInfo();
                String eventName = parser.extractEventName("/from");
                String eventStartDetails = parser.extractEventStartDetails();
                String eventEndDetails = parser.extractEventEndDetails();
                TaskList.doCommandEvent(eventName, eventStartDetails, eventEndDetails);
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
