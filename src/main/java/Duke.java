import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    /***
     * MAX_TASK_NUM shows the maximum number of tasks it can store
     */
    public static final int MAX_TASK_NUM = 100;
    /***
     * Ensures that a fixed line formatting is made.
     */
    public static final String LINE_FORMATTING =
            "____________________________________________________________\n";
    /***
     * The fixed number of spaces when processing commands involving "by" in
     * the input.
     */
    public static final int REMOVE_BY_NUM = 4;
    /***
     * The fixed number of spaces when processing commands involving "todo" in
     * the input.
     */
    public static final int REMOVE_TODO_NUM = 5;
    /***
     * The fixed number of spaces when processing commands involving "event" in
     * the input.
     */
    public static final int REMOVE_EVENT_NUM = 6;
    /***
     * The fixed number of spaces when processing commands involving "from" in
     * the input.
     */
    public static final int REMOVE_FROM_NUM = 6;
    /***
     * The fixed number of spaces when processing commands involving "to" in
     * the input.
     */
    public static final int REMOVE_TO_NUM = 4;
    /***
     * The fixed number of spaces when processing commands involving "unmark" in
     * the input.
     */
    public static final int REMOVE_UNMARK_NUM = 7;
    /***
     * The fixed number of spaces when processing commands involving "mark" in
     * the input.
     */
    public static final int REMOVE_MARK_NUM = 5;

    /***
     * Main function greets the user and runs processInputs().
     */
    public static void main(String[] args) {
        showGreetings();
        processInputs();
        showGoodbye();
    }

    /***
     * Processes the inputs by the user and run commands accordingly.
     * If the command is not recognised, user is prompted to try again.
     */
    private static void processInputs() {
        Scanner in = new Scanner (System.in);
        Task[] storedValues = new Task[MAX_TASK_NUM];
        int taskNum = 0;
        String line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList(storedValues, taskNum);
            } else if (line.startsWith("mark ")) {
                markItem(storedValues, line);
            } else if (line.startsWith("unmark ")) {
                unmarkItem(storedValues, line);
            } else if (line.startsWith("deadline")) {
                taskNum = processDeadline(storedValues, taskNum, line);
            } else if (line.startsWith("todo")) {
                taskNum = processToDo(storedValues, taskNum, line);
            } else if (line.startsWith("event")) {
                taskNum = processEvent(storedValues, taskNum, line);
            } else {
                // Commands that are not listed above
                System.out.println("Invalid command, try again! \n");
            }
            line = in.nextLine();
        }
    }

    /***
     * Prior to adding events to the list, processEvent separates the important details
     * within the input for further data storage.
     * @param storedValues List of tasks from user inputs.
     * @param taskNum The existing position in the list.
     * @param line User input to be processed.
     * @return Updated position in the list.
     */
    private static int processEvent(Task[] storedValues, int taskNum, String line) {
        int firstForwardSlash = line.indexOf('/');
        String taskName = line.substring(REMOVE_EVENT_NUM,firstForwardSlash-1);
        String duration = line.substring(firstForwardSlash+REMOVE_FROM_NUM);

        int secondForwardSlash = duration.indexOf('/');
        String startingTime = duration.substring(0,secondForwardSlash-1);
        String endingTime = duration.substring(secondForwardSlash+REMOVE_TO_NUM);
        Event eventInput = new Event(taskName, startingTime, endingTime);

        taskNum = addTask(storedValues, taskNum, eventInput);
        return taskNum;
    }

    /***
     * Prior to adding events with command toDo to the list, processtoDo separates the
     * important details within the input for data storage.
     * @param storedValues List of tasks from user inputs.
     * @param taskNum The existing position in the list.
     * @param line User input to be processed.
     * @return Updated position in the list.
     */
    private static int processToDo(Task[] storedValues, int taskNum, String line) {
        String removeCommand = line.substring(REMOVE_TODO_NUM);
        Todo todoInput= new Todo(removeCommand);
        taskNum = addTask(storedValues, taskNum, todoInput);
        return taskNum;
    }

    /***
     * Prior to adding events with command deadline to the list, processDeadline separates the
     * important details within the input for data storage.
     * @param storedValues List of tasks from user inputs.
     * @param taskNum The existing position in the list.
     * @param line User input to be processed.
     * @return Updated position in the list.
     */
    private static int processDeadline(Task[] storedValues, int taskNum, String line) {
        int forwardSlash = line.indexOf('/');
        int endOfTask = forwardSlash - 1;
        int dates = forwardSlash + REMOVE_BY_NUM;
        Deadline deadlineInput = new Deadline(line.substring(9,endOfTask), line.substring(dates));
        taskNum = addTask(storedValues, taskNum, deadlineInput);
        return taskNum;
    }

    /***
     * Adds processed user inputs into the list of stored values.
     * @param storedValues List of tasks from user inputs.
     * @param taskNum The existing position in the list.
     * @param line User input to be processed.
     * @return Updated position in the list.
     */
    private static int addTask(Task[] storedValues, int taskNum, Task line) {
        formattingLine();
        System.out.println("Got it. I've added this task: \n" + line.toString() + "\n"+
                "Now you have " + (taskNum + 1) + " tasks in the list.\n");
        formattingLine();
        // Store value in line into list
        Task newInput = line;
        storedValues[taskNum] = newInput;
        taskNum += 1;
        return taskNum;
    }

    /***
     * Unmarks the task in the list when called.
     * @param storedValues List of tasks from user inputs
     * @param line User input to be processed.
     */
    private static void unmarkItem(Task[] storedValues, String line) {
        int length = line.length();
        String itemToMark = line.substring(REMOVE_UNMARK_NUM, length);
        int numToMark = Integer.parseInt(itemToMark);
        // Unmark the item
        storedValues[numToMark-1].unmarkAsDone();
        formattingLine();
        System.out.println("OK, I've marked this task as not done yet: \n" +
                storedValues[numToMark-1].toString() + "\n");
        formattingLine();
    }

    /***
     * Marks the task in the list when called.
     * @param storedValues List of tasks from user inputs.
     * @param line User input to be processed.
     */
    private static void markItem(Task[] storedValues, String line) {
        int length = line.length();
        String itemToMark = line.substring(REMOVE_MARK_NUM, length);
        int numToMark = Integer.parseInt(itemToMark);
        // Mark the item as complete
        storedValues[numToMark-1].markAsDone();
        formattingLine();
        System.out.println("Nice! I've marked this task as done: \n"
                + storedValues[numToMark-1].toString() + "\n");
        formattingLine();
    }

    /***
     * Print all items within the list of stored tasks.
     * @param storedValues List of tasks from user inputs.
     * @param taskNum The existing position in the list.
     */
    private static void printList(Task[] storedValues, int taskNum) {
        int currValue = 0;
        Task[] existingValues = Arrays.copyOf(storedValues, taskNum);
        formattingLine();
        System.out.println("Here are the tasks in your list:");
        for (Task value : existingValues) {
            System.out.println((currValue+1) + "." + value.toString());
            currValue += 1;
        }
        formattingLine();
    }

    /***
     * Outputs the goodbye formatting and message to the user when bye
     * command is called.
     */
    private static void showGoodbye() {
        String bye = "Bye. Hope to see you again soon!\n";
        formattingLine();
        System.out.println(bye);
        formattingLine();
    }

    /**
     * Shows the formatted greetings to the user when called.
     */
    private static void showGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String hello = " Hello! I'm Duke\n" +
                " What can I do for you?\n";

        System.out.println("Hello from\n" + logo);
        formattingLine();
        System.out.println(hello);
        formattingLine();
    }

    /***
     * Prints the fixed formatting line when called.
     */
    private static void formattingLine() {
        System.out.println(LINE_FORMATTING);
    }
}
