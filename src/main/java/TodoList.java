public class TodoList {
    static final String LINE_BREAK = "____________________\n";
    static final String YOUR_TODO_LIST = "This is your todo list.\n";
    static final String HAVE_ADDED = "I have added ";
    static final String TO_LIST = " to your todo list.\n";
    static final String MARKED_TASK = "I have marked Task ";
    static final String UNMARKED_TASK = "I have unmarked Task ";
    static final String IN_LIST = " in your todo list.\n";
    static final int MAX_DEADLINE_ARGS = -1;
    static final int MAX_EVENT_ARGS = 3;
    static final int MAX_TASKS = 100;
    static final String BY_COMMAND = " /by ";
    static final String FROM_COMMAND = " /from ";
    static final String TO_COMMAND = " /to ";
    private static int numberOfTasks = 0;

    private static Todo[] todos = new Todo[MAX_TASKS];

    private static String[] parseDeadline(String args) throws InvalidCommandFormatException {
        String[] vars = args.split(BY_COMMAND, MAX_DEADLINE_ARGS);
        if (vars.length != 2) {
            throw new InvalidCommandFormatException();
        }
        return vars;
    }

    private static String[] parseEvent(String args) throws InvalidCommandFormatException {
        String[] vars = new String[MAX_EVENT_ARGS];
        String[] tempArray0 = args.split(FROM_COMMAND, 2);
        vars[0] = tempArray0[0];
        String[] tempArray1 = tempArray0[1].split(TO_COMMAND, 2);
        vars[1] = tempArray1[0];
        vars[2] = tempArray1[1];
        if (vars.length != 3) {
            throw new InvalidCommandFormatException();
        }
        return vars;
    }

    public static void todo(String args) {
        todos[numberOfTasks] = new Todo(args);
        numberOfTasks += 1;
        System.out.println(LINE_BREAK + HAVE_ADDED + args + TO_LIST + LINE_BREAK);
    }

    public static void deadline(String args) {
        String[] vars;
        try {
            vars = parseDeadline(args);
        } catch (ArrayIndexOutOfBoundsException | InvalidCommandFormatException e) {
            Printer.formatError();
            return;
        }
        todos[numberOfTasks] = new Deadline(vars);
        numberOfTasks += 1;
        System.out.println(LINE_BREAK + HAVE_ADDED + vars[0] + TO_LIST + LINE_BREAK);
    }

    public static void event(String args) {
        String[] vars;
        try {
            vars = parseEvent(args);
        } catch (ArrayIndexOutOfBoundsException | InvalidCommandFormatException e) {
            Printer.formatError();
            return;
        }
        todos[numberOfTasks] = new Event(vars);
        numberOfTasks += 1;
        System.out.println(LINE_BREAK + HAVE_ADDED + vars[0] + TO_LIST + LINE_BREAK);
    }

    public static void list() {
        System.out.print(LINE_BREAK + YOUR_TODO_LIST + LINE_BREAK);
        for (int i = 0; i < numberOfTasks; i += 1) {
            System.out.println(i + 1 + "." + todos[i].printTask());
        }
        System.out.print(LINE_BREAK);
    }

    public static void mark(int num) {
        todos[num - 1].isDone = true;
        System.out.println(LINE_BREAK + MARKED_TASK + num + IN_LIST + todos[num - 1].getStatusIcon()
                + ' ' + todos[num - 1].description + "\n" + LINE_BREAK);
    }

    public static void unmark(int num) {
        todos[num - 1].isDone = false;
        System.out.println(LINE_BREAK + UNMARKED_TASK + num + IN_LIST + todos[num - 1].getStatusIcon()
                + ' ' + todos[num - 1].description + "\n" + LINE_BREAK);
    }
}
