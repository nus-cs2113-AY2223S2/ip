package duke;

import duke.exception.EmptyTaskException;
import duke.exception.IllegalCommandException;
import duke.exception.InvalidDeadline;
import duke.exception.InvalidEvent;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList {
    private static final String COMMAND_EXIT_WORD = "bye";
    private static final String COMMAND_HELP_WORD = "help";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_MARK_WORD = "mark";
    private static final String COMMAND_UNMARK_WORD = "unmark";
    private static final String COMMAND_DELETE_WORD = "delete";
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_EVENT_WORD = "event";

    public static ArrayList<Task> allTasks;

    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    protected void executeCommand(String command, String param)
            throws IllegalCommandException, EmptyTaskException {
        switch (command) {
        case COMMAND_EXIT_WORD:
            Ui.printExitMessage();
            System.exit(0);
        case COMMAND_HELP_WORD:
            Ui.printHelpMessage();
            break;
        case COMMAND_LIST_WORD:
            Ui.printList(allTasks);
            break;
        case COMMAND_MARK_WORD:
            markDone(param);
            break;
        case COMMAND_UNMARK_WORD:
            markNotDone(param);
            break;
        case COMMAND_DELETE_WORD:
            deleteTask(param);
            break;
        case COMMAND_TODO_WORD:
            if (param.equals("")) {
                throw new EmptyTaskException();
            }
            addToDo(param);
            break;
        case COMMAND_DEADLINE_WORD:
            if (param.equals("")) {
                throw new EmptyTaskException();
            }
            try {
                final String[] paramAndBy = Parser.deadline(param);
                addDeadline(paramAndBy[0], paramAndBy[1]);
            } catch (InvalidDeadline e) {
                Ui.printInvalidDeadline();
            }
            break;
        case COMMAND_EVENT_WORD:
            if (param.equals("")) {
                throw new EmptyTaskException();
            }
            try {
                final String[] paramAndFromTo = Parser.event(param);
                addEvent(paramAndFromTo[0], paramAndFromTo[1], paramAndFromTo[2]);
            } catch (InvalidEvent e) {
                Ui.printInvalidEvent();
            }
            break;
        default:
            throw new IllegalCommandException();
        }
    }
    private static void markNotDone(String param) {
        int idx = Integer.parseInt(param) - 1;
        if (idx < 0 || idx >= allTasks.size()) {
            throw new NumberFormatException();
        }
        allTasks.get(idx).setDone(false);
        Ui.printMarkNotDone(allTasks.get(idx));
    }

    private static void markDone(String param) {
        int idx = Integer.parseInt(param) - 1;
        if (idx < 0 || idx >= allTasks.size()) {
            throw new NumberFormatException();
        }
        allTasks.get(idx).setDone(true);
        Ui.printMarkDone(allTasks.get(idx));
    }

    private static void deleteTask(String param) {
        int idx = Integer.parseInt(param) - 1;
        if (idx < 0 || idx >= allTasks.size()) {
            throw new NumberFormatException();
        }
        Ui.printDeleted(allTasks.get(idx));
        allTasks.remove(idx);

    }

    private static void addToDo(String param) {
        allTasks.add(new ToDo(param));
        Ui.printAddMessage(allTasks.get(allTasks.size() - 1));
    }

    private static void addDeadline(String param, String by) {
        allTasks.add(new Deadline(param, by));
        Ui.printAddMessage(allTasks.get(allTasks.size() - 1));
    }

    private static void addEvent(String param, String from, String to) {
        allTasks.add(new Event(param, from, to));
        Ui.printAddMessage(allTasks.get(allTasks.size() - 1));
    }
}
