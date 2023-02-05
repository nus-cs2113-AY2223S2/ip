import java.util.ArrayList;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class Duke {
    public static final String BYE_COMMAND = "bye";
    public static final String CHANGE_COMMAND = "change";
    public static final String LANG_COMMAND = "lang";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String DEADLINE_BY = "/by";
    public static final String EVENT_COMMAND = "event";
    public static final String EVENT_START_FROM = "/from";
    public static final String EVENT_END_TO = "/to";
    /** A fixed sized buffer to trim strings at the right index */
    public static final int COMMAND_BUFFER = 1;
    /** The return result when a substring is not found */
    public static final int OUT_OF_BOUNDS = -1;

    /** Language state of the program. */
    public static boolean isSinglish = false;
    /** A fixed sized array to store all the tasks entered from the user. */
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Toggles the language setting between normal and Singlish mode.
     * Prints to the output the changes made.
     */
    public static void changeLanguage() {
        isSinglish = !isSinglish;
        Greeting.sayChangeLanguage(isSinglish);
    }

    /**
     * Adds the entered task to the list of tasks.
     * if the list of tasks is full, informs the user that the task list is full and no new tasks can be added.
     * @param line String entered by user.
     * @param typeOfTask Type of tasks (TODO, DEADLINE, EVENT).
     * @param startDate startDate for EVENT, Deadline date for DEADLINE, null for TODO.
     * @param endDate endDate for EVENT, null for DEADLINE, null for TODO.
     */
    public static void addToList(String line, TypeOfTask typeOfTask, String startDate, String endDate) {
        if (typeOfTask.equals(TypeOfTask.TODO)) {
            Todo item = new Todo(line, tasks.size());
            tasks.add(item);
        } else if (typeOfTask.equals(TypeOfTask.DEADLINE)) {
            Deadline item = new Deadline(line, tasks.size(), startDate);
            tasks.add(item);
        } else if (typeOfTask.equals(TypeOfTask.EVENT))  {
            Event item = new Event(line, tasks.size(), startDate, endDate);
            tasks.add(item);
        }
        Greeting.sayAddToList(isSinglish);
        printTask(tasks.get(tasks.size() - 1));
        Greeting.printHorizontalLines(isSinglish);
    }

    /**
     * Prints out the task passed into it.
     */
    public static void printTask(Task task) {
        task.printTask();
    }

    /**
     * Prints out the entire list of tasks entered by the user.
     */
    public static void printList() {
        for (Task task : tasks) {
            printTask(task);
        }
        Greeting.printHorizontalLines(isSinglish);
    }

    /**
     * Marks or Unmark the selected task whether it is done, prints out the selected task alongside its state.
     * If the user gives an invalid index, informs the user about it.
     * Does nothing if the user trys to mark a marked task and vice versa.
     *
     * @param index The index of the task selected to be marked or unmarked.
     * @param isMark Whether to mark or unmark the task.
     */
    public static void markTask(int index, boolean isMark) throws IndexOutOfBoundsException {
        index--;
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            if (tasks.get(index).getIsDone() != isMark) {
                tasks.get(index).switchIsDone();
            }
            Greeting.sayUpdatedTask(isSinglish);
            tasks.get(index).printTask();
        }
        Greeting.printHorizontalLines(isSinglish);
    }

    public static void main(String[] args) {
        Greeting.sayHello(isSinglish);


        while (true) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            String[] commands = line.split(" ");
            boolean isBye = commands[0].equals(BYE_COMMAND);
            boolean isValidChangeLangCommand = commands[0].equals(CHANGE_COMMAND) && commands.length == 2 && commands[1].equals(LANG_COMMAND);
            boolean isValidPrintListCommand = commands[0].equals(LIST_COMMAND) && commands.length == 1;
            boolean isValidMarkOrUnmarkCommand = (commands[0].equals(MARK_COMMAND) || commands[0].equals(UNMARK_COMMAND)) && commands.length == 2;
            boolean isTodo = commands[0].equals(TODO_COMMAND);
            boolean isDeadline = commands[0].equals(DEADLINE_COMMAND);
            boolean isEvent = commands[0].equals(EVENT_COMMAND);

            try {
                if (isBye) {
                    Greeting.sayGoodbye(isSinglish);
                    break;
                } else if (isValidChangeLangCommand) {
                    changeLanguage();
                } else if (isValidPrintListCommand) {
                    printList();
                } else if (isValidMarkOrUnmarkCommand) {
                    checkAndMarkTask(commands);
                } else if (isTodo) {
                    addTodo(line);
                } else if (isDeadline) {
                    addDeadline(line);
                } else if (isEvent) {
                    addEvent(line);
                } else {
                    throw new IllegalAccessException();
                }
            } catch (IndexOutOfBoundsException e) {
                Greeting.warnOutOfRange(isSinglish);
            } catch (NumberFormatException e) {
                Greeting.warnWrongSyntax(isSinglish);
            } catch (IllegalAccessException e) {
                Greeting.warnWrongSyntax(isSinglish);
            } catch (MissingFormatArgumentException e) {
                Greeting.warnEmptyDesc(isSinglish);
            }
        }
    }

    /**
     * Checks the validity of the event command.
     * adds the event to the list if command has the right syntax.
     * otherwise, it informs the user that it has a wrong syntax.
     * @param line String entered by the user.
     */
    private static void addEvent(String line) throws MissingFormatArgumentException{
        int indexOfStartDate = line.indexOf(EVENT_START_FROM);
        int indexOfEndDate = line.indexOf(EVENT_END_TO);
        boolean isOutOfBounds = indexOfStartDate == OUT_OF_BOUNDS || indexOfEndDate == OUT_OF_BOUNDS;
        boolean isMissingDescription = indexOfStartDate <= EVENT_COMMAND.length() + COMMAND_BUFFER;

        if (!isOutOfBounds && !isMissingDescription) {
            // +6 for "/from " length
            String startOfDate = line.substring(indexOfStartDate + EVENT_START_FROM.length() + COMMAND_BUFFER, indexOfEndDate);
            // +5 for "/to " length
            String endOfDate = line.substring(indexOfEndDate + EVENT_END_TO.length() + COMMAND_BUFFER);
            // 7 for "event " length
            String descOfTask = line.substring(EVENT_COMMAND.length() + COMMAND_BUFFER, indexOfStartDate);
            addToList(descOfTask, TypeOfTask.EVENT, startOfDate, endOfDate);
        } else if (isOutOfBounds) {
            throw new IndexOutOfBoundsException();
        } else if (isMissingDescription) {
            throw new MissingFormatArgumentException("Missing description");
        }
    }

    /**
     * Checks the validity of the deadline command.
     * adds the deadline to the list if command has the right syntax.
     * otherwise, it informs the user that it has a wrong syntax.
     * @param line String entered by the user.
     */
    private static void addDeadline(String line) throws IndexOutOfBoundsException, MissingFormatArgumentException {
        int indexOfDate = line.indexOf(DEADLINE_BY);
        boolean isOutOfBounds = indexOfDate == OUT_OF_BOUNDS;
        boolean isMissingDescription = indexOfDate <= DEADLINE_COMMAND.length() + COMMAND_BUFFER;

        if (!isOutOfBounds && !isMissingDescription) {
            // +4 for "/by " length
            String startOfDate = line.substring(indexOfDate + DEADLINE_BY.length() + COMMAND_BUFFER);
            // 10 for "deadline " length
            String descriptionOfTask = line.substring(DEADLINE_COMMAND.length() + COMMAND_BUFFER, indexOfDate);
            addToList(descriptionOfTask, TypeOfTask.DEADLINE, startOfDate, null);
        } else if (isOutOfBounds) {
            throw new IndexOutOfBoundsException();
        } else if (isMissingDescription) {
            throw new MissingFormatArgumentException("Missing description");
        }

    }

    /**
     * Checks the validity of the deadline command.
     * adds the deadline to the list if command has the right syntax.
     * otherwise, it informs the user that it has a wrong syntax.
     * @param line String entered by the user.
     */
    private static void addTodo(String line) {
        // 6 for "deadline " length
        String desc = line.substring(TODO_COMMAND.length() + COMMAND_BUFFER);
        addToList(desc, TypeOfTask.TODO, null, null);
    }

    /**
     * Checks the validity of the mark/unmark command.
     * if the task selected in valid, marks or unmark the task depending on the exact command.
     * otherwise, informs the user that the command entered has wrong syntax or task of that index does not exist.
     * @param commands commands entered by the user.
     */
    private static void checkAndMarkTask(String[] commands) throws NumberFormatException {
        if (commands[1].matches("\\d+?")) {
            boolean isMark = commands[0].equals(MARK_COMMAND);
            markTask(Integer.parseInt(commands[1]), isMark);
        } else {
            throw new NumberFormatException();
        }
    }
}
