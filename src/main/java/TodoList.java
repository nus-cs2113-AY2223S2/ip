public class TodoList {
    static String lineBreak = "____________________\n";
    static String listing = "This is your todo list.\n";
    static String addBefore = "I have added ";
    static String addAfter = " to your todo list.\n";
    static String markBefore = "I have marked Task ";
    static String unmarkBefore = "I have unmarked Task ";
    static String markingAfter = " in your todo list.\n";
    private static int tasksNumber = 0;
    private static Task[] todo = new Task[100];

    public static void add(String item) {
        todo[tasksNumber] = new Task(item);
        tasksNumber += 1;
        System.out.println(lineBreak + addBefore + item + addAfter + lineBreak);
    }

    public static void list() {
        System.out.print(lineBreak + listing + lineBreak);
        for (int i = 0; i < tasksNumber; i += 1) {
            System.out.println(i + 1 + ". [" + todo[i].getStatusIcon() + "] " + todo[i].description);
        }
        System.out.print(lineBreak);
    }

    public static void mark(int num) {
        todo[num - 1].isDone = true;
        System.out.println(lineBreak + markBefore + num + markingAfter + "[" + todo[num - 1].getStatusIcon()
                + "] " + todo[num - 1].description + "\n" + lineBreak);
    }

    public static void unmark(int num) {
        todo[num - 1].isDone = false;
        System.out.println(lineBreak + unmarkBefore + num + markingAfter + "[" + todo[num - 1].getStatusIcon()
                + "] " + todo[num - 1].description + "\n" + lineBreak);
    }
}
