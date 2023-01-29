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

    // max number of tasks
    private static final int CAPACITY = 100;

    private static Scanner in = new Scanner(System.in);
    // list of all tasks
    private static Task[] allTasks;
    // total number of tasks
    private static int counter;

    public static void main(String[] args) {
        initTasks();
        printWelcomeMessage();
        while (true) {
            String userCommand = in.nextLine();
            final String[] commandAndParam = processCommand(userCommand);
            String command = commandAndParam[0];
            String param = commandAndParam[1];
            executeCommand(command, param);
        }
    }

    private static void executeCommand(String command, String param) {
        switch (command) {
        case COMMAND_EXIT_WORD:
            printExitMessage();
            System.exit(0);
        case COMMAND_LIST_WORD:
            printList(allTasks, counter);
            break;
        case COMMAND_MARK_WORD:
            markDone(param, allTasks, counter);
            break;
        case COMMAND_UNMARK_WORD:
            markNotDone(param, allTasks, counter);
            break;
        case COMMAND_TODO_WORD:
            if (canStore()) {
                addToDo(param);
            }
            break;
        case COMMAND_DEADLINE_WORD:
            if (canStore()) {
                final String[] paramAndBy = processDeadline(param);
                if (paramAndBy == null) {
                    System.out.println(DIVIDER +
                            "Please enter deadline as \"deadline [task] /by [date]\". \n" +
                            DIVIDER);
                } else {
                    addDeadline(paramAndBy[0], paramAndBy[1]);
                }
            }
            break;
        case COMMAND_EVENT_WORD:
            if (canStore()) {
                final String[] paramAndFromTo = processEvent(param);
                if (paramAndFromTo == null) {
                    System.out.println(DIVIDER +
                            "Please enter event as \"event [task] /from [date] /to [date]\". \n" +
                            DIVIDER);
                } else {
                    addEvent(paramAndFromTo[0], paramAndFromTo[1], paramAndFromTo[2]);
                }
            }
            break;
        }
    }

    private static boolean canStore() {
        if (counter < CAPACITY) {
            return true;
        }
        printErrorForStorage();
        return false;
    }


    private static void initTasks() {
        allTasks = new Task[CAPACITY];
        counter = 0;
    }

    private static String[] processCommand(String userCommand) {
        final String[] split = userCommand.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    private static String[] processDeadline(String param) {
        String[] split = param.trim().split("\\s/by\\s", 2);
        return split.length == 2 ? split : null;
    }

    private static String[] processEvent(String param) {
        String[] split = param.trim().split("\\s/from\\s|\\s/to\\s", 3);
        return split.length == 3 ? split : null;
    }

    private static void markNotDone(String param, Task[] store, int counter) {
        try {
            int idx = Integer.parseInt(param) - 1;
            if (idx < 0 || idx >= counter) {
                printErrorForIdx(counter);
                return;
            }
            store[idx].setDone(false);
            System.out.println(DIVIDER +
                    "OK, I've marked this task as not done yet:\n" +
                    "[ ] " + store[idx].getDescription() + "\n" +
                    DIVIDER);
        } catch (NumberFormatException ex) {
            printErrorForIdx(counter);
        }
    }

    private static void markDone(String param, Task[] store, int counter) {
        try {
            int idx = Integer.parseInt(param) - 1;
            if (idx < 0 || idx >= counter) {
                printErrorForIdx(counter);
                return;
            }
            store[idx].setDone(true);
            System.out.println(DIVIDER + "Nice!, I've marked this task as done:\n" +
                    store[idx] + "\n" + DIVIDER);
        } catch (NumberFormatException ex) {
            printErrorForIdx(counter);
        }
    }

    private static void addToDo(String param) {
        allTasks[counter] = new ToDo(param);
        printAddMessage(allTasks[counter]);
        counter++;
    }

    private static void addDeadline(String param, String by) {
        allTasks[counter] = new Deadline(param, by);
        printAddMessage(allTasks[counter]);
        counter++;
    }

    private static void addEvent(String param, String from, String to) {
        allTasks[counter] = new Event(param, from, to);
        printAddMessage(allTasks[counter]);
        counter++;
    }

    private static void printAddMessage(Task store) {
        System.out.println(DIVIDER + "Got it. I've added this task: \n" +
                "  " + store + "\n" + DIVIDER);
    }

    private static void printErrorForStorage() {
        System.out.println(DIVIDER +
                "Maximum number of tasks reached. \n" +
                DIVIDER);
    }

    private static void printErrorForIdx(int counter) {
        System.out.println(DIVIDER +
                "Please enter [idx] in the form of an integer from 1 to " + counter + ". \n" +
                DIVIDER);
    }

    private static void printList(Task[] store, int counter) {
        System.out.println(DIVIDER +
                "Here are the tasks in your list: ");
        for (int i = 0; i < counter; i++) {
            System.out.println(i+1 + "." + store[i]);
        }
        System.out.println(DIVIDER);
    }

    private static void printExitMessage() {
        System.out.println(DIVIDER +
                " Bye. Hope to see you again soon!\n" +
                DIVIDER);
        return;
    }

    private static void printWelcomeMessage() {
        System.out.println(DIVIDER +
                " Hello! I'm Duke! \n" +
                " Enter \"list\" to see all tasks \n" +
                " Enter \"todo [task]\" to add a task \n" +
                " Enter \"deadline [task] /by [date]\" to add a deadline \n" +
                " Enter \"event [task] /from [date] /to [date]\" to add an event \n" +
                " Enter \"mark [idx]\" to mark task as done \n" +
                " Enter \"unmark [idx]\" to mark task as not done \n" +
                " Enter \"bye\" to exit the program \n" +
                DIVIDER);
    }
}
