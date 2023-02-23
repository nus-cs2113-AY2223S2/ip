package duke.tasklist;

import duke.command.Command;
import duke.Duke;
import duke.command.CommandDelete;
import duke.command.CommandList;
import duke.command.CommandMark;
import duke.command.CommandUnmark;
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
    public static final String COMMAND_FIND = "find";

    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns task details in the same format as the user command
     *
     * @param taskNum task number in stored list
     * @return returns task info in a user command format
     */
    public static String retrieveTask(int taskNum) {
        return tasks.get(taskNum).returnCommand();
    }

    /**
     * Takes in a keyword from user and searches current stored tasks
     * If matching tasks are found, prints out the tasks found
     * Prints no match message if there are no matching tasks found
     *
     * @param taskDetails user input of keyword to find
     */
    public static void getTasks(String taskDetails) {
        Ui.printLine();
        int index = 0;
        for (Task task : tasks) {
            if (task.isMatchingTask(taskDetails)) {
                ++index;
                Ui.printTaskDetails(index, task);
            }
        }
        if (index == 0) {
            Ui.printNoMatchingTasks();
        }
        Ui.printLine();

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
                int taskNum = getTaskNum(parser);
                CommandMark.execute(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_mark_a) {
                Ui.printInvalidNumber("mark");
            }
            break;
        case COMMAND_UNMARK:
            try {
                int taskNum = getTaskNum(parser);
                CommandUnmark.execute(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_unmark_a) {
                Ui.printInvalidNumber("unmark");
            }
            break;
        case COMMAND_LIST:
            CommandList.execute();
            break;
        case COMMAND_BYE:
            Ui.printExitMessage();
            break;
        case COMMAND_DELETE:
            try {
                int taskNum = getTaskNum(parser);
                CommandDelete.execute(taskNum);
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
                String eventName = parser.extractEventName();
                String eventStartDetails = parser.extractEventStartDetails();
                String eventEndDetails = parser.extractEventEndDetails();
                Command.doCommandEvent(eventName, eventStartDetails, eventEndDetails);
            } catch (StringIndexOutOfBoundsException out_event_a) {
                Ui.printInvalidFormat("event");
            } catch (IndexOutOfBoundsException out_event_a) {
                Ui.printEmptyCommand("event");
            }
            break;
        case COMMAND_FIND:
            try {
                String commandInfo = parser.extractCommandInfo();
                getTasks(commandInfo);
            } catch (IndexOutOfBoundsException out_find_a) {
                Ui.printEmptyCommand("find");
            }
            break;
        default:
            if (Duke.toPrint) {
                Ui.printInvalidCommand();
            }
        }
    }

    private static int getTaskNum(Parser parser) {
        String commandInfo = parser.extractCommandInfo();
        int taskNum = Integer.parseInt(commandInfo);
        return taskNum;
    }
}
