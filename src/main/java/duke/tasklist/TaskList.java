package duke.tasklist;

import duke.command.Command;
import duke.Duke;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_DEADLINE = "deadline";

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static String retrieveTask(int taskNum) {
        return tasks.get(taskNum).returnCommand();
    }


    /**
     * Function takes in user input and identifies the user command
     * Calls the command function to add user command into arraylist
     *
     * @param userCommand string of user input
     */
    public static void handleUserCommand(String userCommand) {
        Parser parser = new Parser(userCommand);

        String command = parser.extractCommand();

        switch (command) {
        case COMMAND_MARK:
            try {
                String commandInfo = parser.extractCommandInfo();
                int taskNum = Integer.parseInt(commandInfo);
                Command.doCommandMark(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_mark_a) {
                Ui.printInvalidNumber("mark");
            }
            break;
        case COMMAND_UNMARK:
            try {
                String commandInfo = parser.extractCommandInfo();
                int taskNum = Integer.parseInt(commandInfo);
                Command.doCommandUnmark(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_unmark_a) {
                Ui.printInvalidNumber("unmark");
            }
            break;
        case COMMAND_LIST:
            Command.doCommandList();
            break;
        case COMMAND_BYE:
            Ui.printExitMessage();
            break;
        case COMMAND_DELETE:
            try {
                String commandInfo = parser.extractCommandInfo();
                int taskNum = Integer.parseInt(commandInfo);
                Command.doCommandDelete(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_delete_a) {
                Ui.printInvalidNumber("delete");
            }
            break;
        case COMMAND_TODO:
            try {
                String commandInfo = parser.extractCommandInfo();
                Command.doCommandTodo(commandInfo);
            } catch (IndexOutOfBoundsException out_todo_a) {
                Ui.printEmptyCommand("todo");
            }
            break;
        case COMMAND_DEADLINE:
            try {
                String commandInfo = parser.extractCommandInfo();
                String taskName = parser.extractTaskName();
                String taskDeadline = parser.extractTaskDeadline();
                Command.doCommandDeadline(taskName, taskDeadline);
            } catch (StringIndexOutOfBoundsException out_deadline_b) {
                Ui.printInvalidFormat("deadline");
            } catch (IndexOutOfBoundsException out_deadline_a) {
                Ui.printEmptyCommand("deadline");
            }
            break;
        case COMMAND_EVENT:
            try {
                String commandInfo = parser.extractCommandInfo();
                String eventName = parser.extractEventName("/from");
                String eventStartDetails = parser.extractEventStartDetails();
                String eventEndDetails = parser.extractEventEndDetails();
                Command.doCommandEvent(eventName, eventStartDetails, eventEndDetails);
            } catch (StringIndexOutOfBoundsException out_event_a) {
                Ui.printInvalidFormat("event");
            } catch (IndexOutOfBoundsException out_event_a) {
                Ui.printEmptyCommand("event");
            }
            break;
        default:
            if (Duke.toPrint) {
                Ui.printInvalidCommand();
            }
        }
    }
}
