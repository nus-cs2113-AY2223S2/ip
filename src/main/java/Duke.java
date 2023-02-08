import java.util.Scanner;

public class Duke {
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_MARK_WORD = "mark";
    private static final String COMMAND_UNMARK_WORD = "unmark";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_EXIT_WORD = "bye";

    private static final String DIVIDER = "____________________________________________________________\n";

//    public static final String INVALID_DEADLINE =
//            DIVIDER + "Please enter deadline as \"deadline [task] /by [date]\". \n" + DIVIDER;
//    public static final String INVALID_EVENT =
//            DIVIDER + "Please enter event as \"event [task] /from [date] /to [date]\". \n" + DIVIDER;
//    public static final String INVALID_COMMAND =
//            DIVIDER + "Sorry, but I don't know what that means :( \n" + DIVIDER;
//    public static final String EMPTY_TASK_DESCRIPTION =
//            DIVIDER + "Oops! The description of a task cannot be empty. \n" + DIVIDER;

    // max number of tasks
    private static final int CAPACITY = 100;
    // list of all tasks
    private static Task[] allTasks;
    private static Scanner in = new Scanner(System.in);


    public static void main(String[] args) {
        initTasks();
        Output.printWelcomeMessage();
        while (true) {
            String userCommand = in.nextLine();
            final String[] commandAndParam = processCommand(userCommand);
            String command = commandAndParam[0];
            String param = commandAndParam[1];

            try {
                executeCommand(command, param);
            } catch (IllegalCommandException e) {
                Output.printInvalidCommand();
            } catch (IndexOutOfBoundsException e) {
                Output.printErrorForStorage();
            } catch (EmptyTaskException e) {
                Output.printEmptyTask();
            }
        }
    }

    private static void executeCommand(String command, String param) throws IllegalCommandException, EmptyTaskException {
        switch (command) {
        case COMMAND_EXIT_WORD:
            Output.printExitMessage();
            System.exit(0);
        case COMMAND_LIST_WORD:
            Output.printList(allTasks);
            break;
        case COMMAND_MARK_WORD:
            markDone(param);
            break;
        case COMMAND_UNMARK_WORD:
            markNotDone(param);
            break;
        case COMMAND_TODO_WORD:
            if (isFullCapacity()) {
                throw new IndexOutOfBoundsException();
            }
            if (param == "") {
                throw new EmptyTaskException();
            }
            addToDo(param);
            break;
        case COMMAND_DEADLINE_WORD:
            if (isFullCapacity()) {
                throw new IndexOutOfBoundsException();
            }
            if (param == "") {
                throw new EmptyTaskException();
            }
            final String[] paramAndBy = processDeadline(param);
            if (paramAndBy == null) {
                Output.printInvalidDeadline();
            } else {
                addDeadline(paramAndBy[0], paramAndBy[1]);
            }
            break;
        case COMMAND_EVENT_WORD:
            if (isFullCapacity()) {
                throw new IndexOutOfBoundsException();
            }
            if (param == "") {
                throw new EmptyTaskException();
            }
            final String[] paramAndFromTo = processEvent(param);
            if (paramAndFromTo == null) {
                Output.printInvalidEvent();
            } else {
                addEvent(paramAndFromTo[0], paramAndFromTo[1], paramAndFromTo[2]);
            }
            break;
        default:
            throw new IllegalCommandException();
        }
    }

    private static boolean isFullCapacity() {
        if (Task.getCounter() < CAPACITY) {
            return false;
        }
        return true;
    }

    private static void initTasks() {
        allTasks = new Task[CAPACITY];
    }

    private static String[] processCommand(String userCommand) {
        final String[] split = userCommand.trim().split("\\s+", 2);
        return (split.length == 2) ? split : new String[]{split[0], ""};
    }

    private static String[] processDeadline(String param) {
        String[] split = param.trim().split("\\s/by\\s", 2);
        return split.length == 2 ? split : null;
    }

    private static String[] processEvent(String param) {
        String[] split = param.trim().split("\\s/from\\s|\\s/to\\s", 3);
        return split.length == 3 ? split : null;
    }

    private static void markNotDone(String param) {
        try {
            int idx = Integer.parseInt(param) - 1;
//            if (idx < 0 || idx >= Task.getCounter()) {
//                printErrorForIdx();
//                return;
//            }
            allTasks[idx].setDone(false);
            System.out.println(DIVIDER +
                    "OK, I've marked this task as not done yet:\n" +
                    "[ ] " + allTasks[idx].getDescription() + "\n" +
                    DIVIDER);
        } catch (NumberFormatException ex) {
            Output.printErrorForIdx();
        }
    }

    private static void markDone(String param) {
        try {
            int idx = Integer.parseInt(param) - 1;
//            if (idx < 0 || idx >= Task.getCounter()) {
//                printErrorForIdx();
//                return;
//            }
            allTasks[idx].setDone(true);
            System.out.println(DIVIDER + "Nice!, I've marked this task as done:\n" +
                    allTasks[idx] + "\n" + DIVIDER);
        } catch (NumberFormatException ex) {
            Output.printErrorForIdx();
        }
    }

    private static void addToDo(String param) {
        allTasks[Task.getCounter()] = new ToDo(param);
        Output.printAddMessage(allTasks[Task.getCounter()-1]);
    }

    private static void addDeadline(String param, String by) {
        allTasks[Task.getCounter()] = new Deadline(param, by);
        Output.printAddMessage(allTasks[Task.getCounter()-1]);
    }

    private static void addEvent(String param, String from, String to) {
        allTasks[Task.getCounter()] = new Event(param, from, to);
        Output.printAddMessage(allTasks[Task.getCounter()-1]);
    }

//    private static void printAddMessage(Task store) {
//        System.out.println(DIVIDER + "Got it. I've added this task: \n" +
//                "  " + store + "\n" + DIVIDER);
//    }
//
//    private static void printErrorForStorage() {
//        System.out.println(DIVIDER +
//                "Maximum number of tasks reached. \n" +
//                DIVIDER);
//    }
//
//    private static void printErrorForIdx() {
//        System.out.println(DIVIDER +
//                "Please enter [idx] in the form of an integer from 1 to " + Task.getCounter() + ". \n" +
//                DIVIDER);
//    }
//
//    private static void printList() {
//        System.out.println(DIVIDER +
//                "Here are the tasks in your list: ");
//        for (int i = 0; i < Task.getCounter(); i++) {
//            System.out.println(i+1 + "." + allTasks[i]);
//        }
//        System.out.println(DIVIDER);
//    }
//
//    private static void printExitMessage() {
//        System.out.println(DIVIDER +
//                " Bye. Hope to see you again soon!\n" +
//                DIVIDER);
//        return;
//    }
}
