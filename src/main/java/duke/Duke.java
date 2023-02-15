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
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String COMMAND_EXIT_WORD = "bye";
    private static final String COMMAND_HELP_WORD = "help";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_MARK_WORD = "mark";
    private static final String COMMAND_UNMARK_WORD = "unmark";
    private static final String COMMAND_DELETE_WORD = "delete";
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_EVENT_WORD = "event";

    // array list of all tasks
    public static ArrayList<Task> allTasks;
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        initTasks();
        Output.printWelcomeMessage();
        while (true) {
            String userCommand = in.nextLine();
            final String[] commandAndParam = Processor.command(userCommand);
            String command = commandAndParam[0];
            String param = commandAndParam[1];
            try {
                executeCommand(command, param);
                Storage.updateDuke();
            } catch (IllegalCommandException e) {
                Output.printInvalidCommand();
            } catch (EmptyTaskException e) {
                Output.printEmptyTask();
            } catch (IOException e) {
                Output.printErrorForIO();
            } catch (NumberFormatException e) {
                Output.printErrorForIdx();
            }
        }
    }

    private static void executeCommand(String command, String param)
            throws IllegalCommandException, EmptyTaskException {
        switch (command) {
        case COMMAND_EXIT_WORD:
            Output.printExitMessage();
            System.exit(0);
        case COMMAND_HELP_WORD:
            Output.printHelpMessage();
            break;
        case COMMAND_LIST_WORD:
            Output.printList(allTasks);
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
                final String[] paramAndBy = Processor.deadline(param);
                addDeadline(paramAndBy[0], paramAndBy[1]);
            } catch (InvalidDeadline e) {
                Output.printInvalidDeadline();
            }
            break;
        case COMMAND_EVENT_WORD:
            if (param.equals("")) {
                throw new EmptyTaskException();
            }
            try {
                final String[] paramAndFromTo = Processor.event(param);
                addEvent(paramAndFromTo[0], paramAndFromTo[1], paramAndFromTo[2]);
            } catch (InvalidEvent e) {
                Output.printInvalidEvent();
            }
            break;
        default:
            throw new IllegalCommandException();
        }
    }

    private static void initTasks() {
        try {
            allTasks = Storage.initDuke();
        } catch (IOException e) {
            Output.printErrorForIO();
        }
    }

    private static void markNotDone(String param) {
        int idx = Integer.parseInt(param) - 1;
        if (idx < 0 || idx >= allTasks.size()) {
            throw new NumberFormatException();
        }
        allTasks.get(idx).setDone(false);
        Output.printMarkNotDone(allTasks.get(idx));
    }

    private static void markDone(String param) {
        int idx = Integer.parseInt(param) - 1;
        if (idx < 0 || idx >= allTasks.size()) {
            throw new NumberFormatException();
        }
        allTasks.get(idx).setDone(true);
        Output.printMarkDone(allTasks.get(idx));
    }

    private static void deleteTask(String param) {
        int idx = Integer.parseInt(param) - 1;
        if (idx < 0 || idx >= allTasks.size()) {
            throw new NumberFormatException();
        }
        Output.printDeleted(allTasks.get(idx));
        allTasks.remove(idx);

    }

    private static void addToDo(String param) {
        allTasks.add(new ToDo(param));
        Output.printAddMessage(allTasks.get(allTasks.size() - 1));
    }

    private static void addDeadline(String param, String by) {
        allTasks.add(new Deadline(param, by));
        Output.printAddMessage(allTasks.get(allTasks.size() - 1));
    }

    private static void addEvent(String param, String from, String to) {
        allTasks.add(new Event(param, from, to));
        Output.printAddMessage(allTasks.get(allTasks.size() - 1));
    }
}
