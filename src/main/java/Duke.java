import java.util.Scanner;

public class Duke {

    /**
     * Simplifies the default Java System.out.print method
     *
     * @param str Input string to print
     */
    public static void print(String str) {
        System.out.print(str);
    }

    /**
     * Simplifies the default Java System.out.println method
     *
     * @param str Input string to print with a newline character
     *            at the end
     */
    public static void println(String str) {
        System.out.println(str);
    }

    /**
     * Prints out one line of underscores, with a newline character at the end
     * <p>
     * 4 spaces is included at the front of the string
     */
    public static void printOneLine() {
        System.out.println("    ____________________________________");
    }


    /**
     * Prints the type of task and the marked-done status of the task
     *
     * @param selectedTask The selected task to print its task Type and Done status
     */
    public static void printTypeAndStatus(Task selectedTask) {
        print("       [" + selectedTask.getType() + "]" + "[" + selectedTask.getDoneIcon() + "] ");
    }

    /**
     * Prints the details of a newly added task
     *
     * @param task      The task to print the details of.
     * @param taskIndex The index of the current last task, to determine the current number of tasks in the list
     */
    private static void printAddingOneTask(Task task, int taskIndex) {
        printOneLine();
        println("     Got it. I've added this task:");
        print("       [" + task.getType() + "]" + "[" + task.getDoneIcon() + "] ");

        if (task.isTodo) {
            print(task.description);

        } else if (task.isDeadline) {

            print(task.description);
            print(" (by:" + task.getBy() + ")");

        } else if (task.isEvent) {

            print(task.description);
            print("(from: " + task.getFrom() + " to:" + task.getTo() + ")");

        }
        println("");

        println("     Now you have " + (taskIndex + 1) + " tasks in the list");

        printOneLine();
    }


    public static void main(String[] args) {
        showWelcomeMessage();

        Task[] taskList = new Task[100];
        int taskIndex = 0;

        Scanner input = new Scanner(System.in);

        while (true) {
            String text = input.nextLine();

            if ("bye".equalsIgnoreCase(text)) {
                break;
            }

            String[] userInput = text.split(" ", 2);

            String keyword = userInput[0];

            switch (keyword) {
                case "list":
                    printTaskList(taskList);
                    break;

                case "mark":
                    String taskNumberToMark = userInput[1];
                    markSelectedTask(taskList, taskNumberToMark);
                    break;

                case "unmark":
                    String taskNumberToUnmark = userInput[1];
                    unmarkSelectedTask(taskList, taskNumberToUnmark);
                    break;

                case "deadline":
                    addDeadline(taskList, taskIndex, userInput);

                    taskIndex += 1;
                    break;

                case "event":
                    addEvent(taskList, taskIndex, userInput);

                    taskIndex += 1;
                    break;

                case "todo":
                    addTodo(taskList, taskIndex, userInput);

                    taskIndex += 1;
                    break;
            }
        }

        showExitMessage();
    }


    /**
     * Creates a new task, classified as a todo task
     *
     * @param taskList  The list to insert the task into
     * @param taskIndex The current index to insert the task in the list
     * @param userInput The details of the task to be added
     */
    private static void addTodo(Task[] taskList, int taskIndex, String[] userInput) {
        Todo td = new Todo(userInput[1]);

        taskList[taskIndex] = td;

        printAddingOneTask(td, taskIndex);
    }

    /**
     * Creates a new task, classified as a event task
     *
     * @param taskList  The list to insert the task into
     * @param taskIndex The current index to insert the task in the list
     * @param userInput The details of the task to be added
     */
    private static void addEvent(Task[] taskList, int taskIndex, String[] userInput) {
        String[] eventDetails = userInput[1].split("/from | /to");

        Event e = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);

        taskList[taskIndex] = e;

        printAddingOneTask(e, taskIndex);
    }

    /**
     * Creates a new task, classified as a deadline task
     *
     * @param taskList  The list to insert the task into
     * @param taskIndex The current index to insert the task in the list
     * @param userInput The details of the task to be added
     */
    private static void addDeadline(Task[] taskList, int taskIndex, String[] userInput) {
        String[] taskDetails = userInput[1].split(" /by", 2);

        String taskName = taskDetails[0];
        String taskDueDate = taskDetails[1];

        Deadline d = new Deadline(taskName, taskDueDate);

        taskList[taskIndex] = d;

        printAddingOneTask(d, taskIndex);
    }

    /**
     * Sets a specified task as not done
     *
     * @param taskList   The list of tasks that contains the task that needs to be marked as not done
     * @param taskNumber The number of the task to mark as not done
     */
    private static void unmarkSelectedTask(Task[] taskList, String taskNumber) {

        int taskNumberToUnmark = Integer.parseInt(taskNumber);
        taskNumberToUnmark -= 1;

        Task selectedTask = taskList[taskNumberToUnmark];
        selectedTask.setNotDone();

        printOneLine();
        println("     OK, I've marked this task as not done yet:");

        printTypeAndStatus(selectedTask);
        print(selectedTask.description);

        switch (selectedTask.getType()) {
            case "D":
                println(" (by:" + selectedTask.getBy() + ")");
                break;

            case "E":
                println("(from: " + selectedTask.getFrom() + " to:" + selectedTask.getTo() + ")");
                break;

            default:
                println("");
        }

        printOneLine();
    }

    /**
     * Sets a specified task as done
     *
     * @param taskList   The list of tasks that contains the task that needs to be marked as done
     * @param taskNumber The number of the task to mark as done
     */
    private static void markSelectedTask(Task[] taskList, String taskNumber) {
        int taskNumberToMark = Integer.parseInt(taskNumber);
        taskNumberToMark -= 1;

        Task selectedTask = taskList[taskNumberToMark];
        selectedTask.setDone();

        printOneLine();
        println("     Nice! I've marked this task as done:");

        printTypeAndStatus(selectedTask);
        print(selectedTask.description);

        switch (selectedTask.getType()) {
            case "D":
                println(" (by:" + selectedTask.getBy() + ")");
                break;

            case "E":
                println("(from: " + selectedTask.getFrom() + " to:" + selectedTask.getTo() + ")");
                break;

            default:
                println("");
        }

        printOneLine();
    }


    /**
     * Prints the list of tasks
     *
     * @param taskList the list of tasks to print out
     */
    private static void printTaskList(Task[] taskList) {
        printOneLine();
        println("     Here are the tasks in your list:");

        for (int i = 0; i < taskList.length; i += 1) {
            if (taskList[i] == null) {
                break;
            }

            print("     " + (i + 1) + ".");
            print("[" + taskList[i].getType() + "]");
            print("[" + taskList[i].getDoneIcon() + "] " + taskList[i].description);

            switch (taskList[i].getType()) {
                case "D":
                    println(" (by:" + taskList[i].getBy() + ")");
                    break;

                case "E":
                    println("(from: " + taskList[i].getFrom() + " to:" + taskList[i].getTo() + ")");
                    break;

                case "T":
                    println("");
            }
        }
        printOneLine();
    }


    /**
     * Prints welcome message at the start of the program
     */
    private static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        println("Hello from\n" + logo);

        printOneLine();
        println("    Hello! I'm Duke");
        println("    What can I do for you?");
        printOneLine();
    }


    /**
     * Prints exit message at the end of the program
     */
    private static void showExitMessage() {
        printOneLine();
        println("    Bye. Hope to see you again soon!");
        printOneLine();
    }
}