public class TaskPrinter {
    public static void printTask(Task t) {
        System.out.printf(
            "[%s] %s\n", 
            t.isDone() ? "X" : " ",
            t.getTaskName()
        );
    }
}
