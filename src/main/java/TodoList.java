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
    private static Task[] tasks = new Task[MAX_TASKS];

    public static void add(String item) {
        tasks[tasksNumber] = new Task(item);
        tasksNumber += 1;
        System.out.println(LINE_BREAK + HAVE_ADDED + item + TO_LIST + LINE_BREAK);
    }

    public static void list() {
        System.out.print(LINE_BREAK + YOUR_TODO_LIST + LINE_BREAK);
        for (int i = 0; i < tasksNumber; i += 1) {
            System.out.println(i + 1 + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
        }
        System.out.print(LINE_BREAK);
    }

    public static void mark(int num) {
        tasks[num - 1].isDone = true;
        System.out.println(LINE_BREAK + MARKED_TASK + num + IN_LIST + "[" + tasks[num - 1].getStatusIcon()
                + "] " + tasks[num - 1].description + "\n" + LINE_BREAK);
    }

    public static void unmark(int num) {
        tasks[num - 1].isDone = false;
        System.out.println(LINE_BREAK + UNMARKED_TASK + num + IN_LIST + "[" + tasks[num - 1].getStatusIcon()
                + "] " + tasks[num - 1].description + "\n" + LINE_BREAK);
    }
}
