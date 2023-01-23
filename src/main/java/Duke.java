import java.util.Scanner;

public class Duke {

    private static final Task[] tasks = new Task[100];
    private static int counter = 0;

    /**
     * Prints out the status of the task and the task name
     */
    private static void printTasks() {
        System.out.println(Message.LIST_TASKS.getMessage());
        for (int i = 0; i < counter; ++i) {
            boolean isDone = tasks[i].isDone();
            String symbol = isDone ? "X" : " ";
            System.out.printf(
                    "%d.[%s] %s\n",
                    (i + 1),
                    symbol,
                    tasks[i].getTaskName()
            );
        }
    }

    /**
     * Mark or unmark a task based on the command and index
     *
     * @param line Command to mark or un-mark
     * @param num  Index to mark or un-mark
     */
    private static void markOrUnmarkTask(String line, String num) {
        // Get the integer form of the index
        int index = Integer.parseInt(num) - 1;

        // Check if the command is to mark
        boolean isMark = line.equals("mark");

        // Determine the comment to make
        String remark = isMark
                ? Message.MARKED.getMessage()
                : Message.UNMARKED.getMessage();

        // Set the task status based on the command
        tasks[index].setDone(isMark);

        // Check whether status is now marked
        boolean isDone = tasks[index].isDone();

        // Get the correct symbol
        String symbol = isDone ? "X" : " ";

        // Print
        System.out.printf(
                "%s\n[%s] %s\n",
                remark,
                symbol,
                tasks[index].getTaskName()
        );
    }

    /**
     * Adds a new task into the list
     *
     * @param line The task to be added into the list
     */
    private static void addTask(String line) {
        Task newTask = new Task(line);
        tasks[counter] = newTask;
        counter += 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;
        System.out.println(Message.WELCOME.getMessage());

        while (isRunning) {
            if (!in.hasNextLine()) { // Important to avoid error
                break;
            }
            String line = in.nextLine().trim();
            String[] instructions = line.split(" ", 2);
            switch (instructions[0]) {
            case "list":
                printTasks();
                break;
            case "mark":
            case "unmark":
                markOrUnmarkTask(instructions[0], instructions[1]);
                break;
            case "bye":
                isRunning = false;
                break;
            default:
                addTask(line);
                System.out.println("added: " + line);
                break;
            }
        }
        System.out.println(Message.GOODBYE.getMessage());
        in.close(); // Closed to prevent memory leak
    }
}
