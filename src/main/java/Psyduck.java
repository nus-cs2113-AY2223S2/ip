
public class Psyduck {
    private static boolean shouldExit = false;

    private static Task[] tasks = new Task[100];

    private static int taskCount = 0;

    public static int getTaskCount() {
        return taskCount;
    }

    public static Task getTask(int taskNum) {
        return tasks[taskNum - 1]; //array is 0-indexed, taskNum is 1-indexed
    }

    public static Task getNewestTask() {
        return tasks[taskCount - 1]; //array is 0-indexed, taskNum is 1-indexed
    }

    public static void setShouldExit(boolean shouldExit) {
        Psyduck.shouldExit = shouldExit;
    }

    public static void addToDo(String description) {
        ToDo newTask = new ToDo(description);
        tasks[taskCount] = newTask;
        taskCount++;
    }


    public static void addDeadline(String description, String by) {
        Deadline newTask = new Deadline(description, by);
        tasks[taskCount] = newTask;
        taskCount++;
    }

    public static void addEvent(String description, String from, String to) {
        Event newTask = new Event(description, from, to);
        tasks[taskCount] = newTask;
        taskCount++;
    }

    public static void listTasks() {
        Command.linePrint();
        for (int i = 0; i < taskCount; i++) {
            System.out.print(Integer.toString(i + 1) + ".");
            System.out.println(tasks[i]);
        }
        Command.linePrint();
    }

    public static void greet() {
        Command.linePrint();
        System.out.println("( ´ ▽ ` )ﾉ Hi I am Psyduck! How can I help you?");
        Command.linePrint();
    }



    public static void sayBye() {
        System.out.println("Bye see you soon! (⌒ー⌒)ﾉ");
        Command.linePrint();
    }

    public static void main(String[] args) {
        greet();
        do {
            Command.processCommands();
        } while (!shouldExit);
        sayBye();
    }
}
