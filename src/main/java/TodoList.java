public class TodoList {
    static final String LINE_BREAK = "____________________\n";
    static final String YOUR_TODO_LIST = "This is your todo list.\n";
    static final String HAVE_ADDED = "I have added ";
    static final String TO_LIST = " to your todo list.\n";
    static final String MARKED_TASK = "I have marked Task ";
    static final String UNMARKED_TASK = "I have unmarked Task ";
    static final String IN_LIST = " in your todo list.\n";
    static final int MAX_COMMAND_ARGS = 3;
    static final int MAX_TASKS = 100;
    static final String BY_COMMAND = " /by";
    static final int BY_COMMAND_LENGTH = 5;
    static final String FROM_COMMAND = " /from";
    static final int FROM_COMMAND_LENGTH = 7;
    static final String TO_COMMAND = " /to";
    static final int TO_COMMAND_LENGTH = 5;
    private static int tasksNumber = 0;
    private static Todo[] todos = new Todo[MAX_TASKS];

    private static String[] parseDates(String args) {
        String[] commandArray = new String[MAX_COMMAND_ARGS];
        int byIndex = args.indexOf(BY_COMMAND);
        if (byIndex != -1) {
            commandArray[0] = args.substring(0, byIndex);
            commandArray[1] = args.substring(byIndex + BY_COMMAND_LENGTH);
        } else {
            int fromIndex = args.indexOf(FROM_COMMAND);
            int toIndex = args.indexOf(TO_COMMAND);
            commandArray[0] = args.substring(0, fromIndex);
            commandArray[1] = args.substring(fromIndex + FROM_COMMAND_LENGTH, toIndex);
            commandArray[2] = args.substring(toIndex + TO_COMMAND_LENGTH);
        }
        return commandArray;
    }

    public static void add(String[] args) {
        switch (args[0]) {
        case "todo":
            addTodo(args[1]);
            break;
        case "deadline":
            String[] deadlineArray = parseDates(args[1]);
            addDeadline(deadlineArray);
            break;
        case "event":
            String[] eventArray = parseDates(args[1]);
            addEvent(eventArray);
        }
    }

    public static void addTodo(String args) {
        todos[tasksNumber] = new Todo(args);
        tasksNumber += 1;
        System.out.println(LINE_BREAK + HAVE_ADDED + args + TO_LIST + LINE_BREAK);
    }

    public static void addDeadline(String[] args) {
        todos[tasksNumber] = new Deadline(args);
        tasksNumber += 1;
        System.out.println(LINE_BREAK + HAVE_ADDED + args[0] + TO_LIST + LINE_BREAK);
    }

    public static void addEvent(String[] args) {
        todos[tasksNumber] = new Event(args);
        tasksNumber += 1;
        System.out.println(LINE_BREAK + HAVE_ADDED + args[0] + TO_LIST + LINE_BREAK);
    }

    public static void list() {
        System.out.print(LINE_BREAK + YOUR_TODO_LIST + LINE_BREAK);
        for (int i = 0; i < tasksNumber; i += 1) {
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
