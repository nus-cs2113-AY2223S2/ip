/**
 * @author Wen Jun
 * @version 0.0.4
 */
import java.util.Scanner;

public class Duke {

    private static final int MAX_TASKS = 100;

    private static final Task[] tasks = new Task[MAX_TASKS];

    private static int counter = 0;

    private static boolean isRunning = true;

    /**
     * Prints out all the tasks in the tasks array
     */
    private static void printTasks() {
        System.out.println(Message.LIST_TASKS.message);
        for (int i = 0; i < counter; ++i) {
            System.out.printf("%d. %s\n", (i + 1), tasks[i].getDescriptionText());
        }
    }

    /**
     * Adds a todo task into the array and prints out the 
     * corresponding message
     *
     * @param line The user input text
     */
    private static void addTodoTask(String line) {
        int START_INDEX_OF_TODO_TASK = 5;
        String taskDescription = line.substring(START_INDEX_OF_TODO_TASK);
        Todo newTodo = new Todo(taskDescription);
        tasks[counter] = newTodo;
        counter += 1;
        System.out.printf(
                Message.TODO_TASK_ADDED.message,
                newTodo.getDescriptionText(),
                counter
        );
    }

    /**
     * 
     * @param description A string after removing the command
     */
    private static void addDeadlineTask(String line) {
        int indexOfEndDate = line.indexOf("/by");
        int LENGTH_OF_END_DATE_COMMAND = 4;

        String endDate = line.substring(indexOfEndDate + LENGTH_OF_END_DATE_COMMAND);
        String task = line.substring(line.indexOf("deadline") + 9, indexOfEndDate);
        Deadline newDeadline = new Deadline(task, endDate);
        tasks[counter] = newDeadline;
        counter += 1;
    }

    /**
     * Toggles the task as mark or un-mark based on the user
     * request. Combined the two tasks together to abide by
     * Single Responsibility Principle and Don't Repeat Yourself
     * 
     * @param isMark Whether the user wants to mark the task
     * @param index The index of the task to be marked
     */
    private static void toggleMark(boolean isMark, int index) {
        tasks[index].setDone(isMark);
        System.out.printf(
                "%s\n%s\n",
                isMark ? Message.MARKED.message : Message.UNMARKED.message,
                tasks[index].getDescriptionText()
        );
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(Message.WELCOME.message);

        while (isRunning) {
            if (!in.hasNextLine()) {
                break;
            }

            String line = in.nextLine().trim();
            String[] words = line.split(" ", 2);
            String command = words[0];
            int index;

            switch (command) {
            case "list":
                printTasks();
                break;
            case "deadline":
                addDeadlineTask(line);
                break;
            case "todo":
                addTodoTask(line);
                break;
            case "mark":
            case "unmark":
                index = Integer.parseInt(words[1]) - 1;
                boolean isMark = words[0].equals("mark");
                toggleMark(isMark, index);
                break;
            case "bye":
                isRunning = false;
                break;
            default:
                System.out.println("An invalid command has been provided.");
            }
        }
        System.out.println(Message.GOODBYE.message);
        in.close(); // Closed to prevent memory leak
    }
}
