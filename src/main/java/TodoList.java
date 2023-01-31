public class TodoList {
    static final String LINE_BREAK = "____________________\n";
    static final String YOUR_TODO_LIST = "This is your todo list.\n";
    static final String HAVE_ADDED = "I have added ";
    static final String TO_LIST = " to your todo list.\n";
    static final String MARKED_TASK = "I have marked Task ";
    static final String UNMARKED_TASK = "I have unmarked Task ";
    static final String IN_LIST = " in your todo list.\n";
    public static final int MAX_TASKS = 100;
    private static int tasksNumber = 0;
    private static Todo[] todos = new Todo[MAX_TASKS];

    public static void addTodo(String args) {
        todos[tasksNumber] = new Todo(args);
        tasksNumber += 1;
        System.out.println(LINE_BREAK + HAVE_ADDED + args + TO_LIST + LINE_BREAK);
    }

    public static void addDeadline(String args) {
        String[] cmdArray = new String[2];
        todos[tasksNumber] = new Deadline(cmdArray);
        tasksNumber += 1;
        System.out.println(LINE_BREAK + HAVE_ADDED + cmdArray[0] + TO_LIST + LINE_BREAK);
    }

    public static void addEvent(String args) {
        String[] cmdArray = new String[3];
        todos[tasksNumber] = new Event(cmdArray);
        tasksNumber += 1;
        System.out.println(LINE_BREAK + HAVE_ADDED + cmdArray[0] + TO_LIST + LINE_BREAK);
    }

    public static void list() {
        System.out.print(LINE_BREAK + YOUR_TODO_LIST + LINE_BREAK);
        for (int i = 0; i < tasksNumber; i += 1) {
            System.out.println(i + 1 + ". [" + todos[i].getStatusIcon() + "] " + todos[i].description);
        }
        System.out.print(LINE_BREAK);
    }

    public static void mark(int num) {
        todos[num - 1].isDone = true;
        System.out.println(LINE_BREAK + MARKED_TASK + num + IN_LIST + "[" + todos[num - 1].getStatusIcon()
                + "] " + todos[num - 1].description + "\n" + LINE_BREAK);
    }

    public static void unmark(int num) {
        todos[num - 1].isDone = false;
        System.out.println(LINE_BREAK + UNMARKED_TASK + num + IN_LIST + "[" + todos[num - 1].getStatusIcon()
                + "] " + todos[num - 1].description + "\n" + LINE_BREAK);
    }
}
