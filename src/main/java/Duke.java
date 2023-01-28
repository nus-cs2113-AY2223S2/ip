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
     * @param taskDescription A string after removing the
     *                        command
     */
    private static void addTodoTask(String taskDescription) {
        Todo newTodo = new Todo(taskDescription);
        tasks[counter] = newTodo;
        counter += 1;
        System.out.printf(
                Message.TASK_ADDED.message,
                newTodo.getDescriptionText(),
                counter
        );
    }

    /**
     * Adds a deadline task into the array and prints out
     * the corresponding message
     *
     * @param taskDescription A string after removing the
     *                        command
     */
    private static void addDeadlineTask(String taskDescription) {
        int index = taskDescription.indexOf("/by");
        String description = taskDescription.substring(0, index);
        String endDuration = taskDescription.substring(index + "/by ".length());
        Deadline newDeadline = new Deadline(description, endDuration);
        tasks[counter] = newDeadline;
        counter += 1;
        System.out.printf(
                Message.TASK_ADDED.message,
                newDeadline.getDescriptionText(),
                counter
        );
    }

    /**
     * Adds an event task into the tasks array and prints
     * out the corresponding message
     *
     * @param taskDescription A string after removing the
     *                        command
     */
    private static void addEventTask(String taskDescription) {
        int indexOfFrom = taskDescription.indexOf("/from");
        int indexOfTo = taskDescription.indexOf("/to");
        String description = taskDescription.substring(0, indexOfFrom);
        String from = taskDescription.substring(
                indexOfFrom + "/from ".length(),
                indexOfTo
        );
        String to = taskDescription.substring(indexOfTo + "/to ".length());
        Event newEvent = new Event(description, from, to);
        tasks[counter] = newEvent;
        counter += 1;
        System.out.printf(
                Message.TASK_ADDED.message,
                newEvent.getDescriptionText(),
                counter
        );
    }

    /**
     * Toggles the task as mark or un-mark based on the user
     * request. Combined the two tasks together to abide by
     * Single Responsibility Principle and Don't Repeat
     * Yourself
     *
     * @param isMark Whether the user wants to mark the task
     * @param index  The index of the task to be marked
     */
    private static void toggleMark(boolean isMark, int index) {
        tasks[index].setDone(isMark);
        System.out.printf(
                "%s\n%s\n",
                isMark ? Message.MARKED.message : Message.UNMARKED.message,
                tasks[index].getDescriptionText()
        );
    }

    /**
     * Terminates the programme if the /bye command
     * is given
     */
    private static void terminate() {
        isRunning = false;
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
            String taskDescription;

            switch (command) {
            case "list":
                printTasks();
                break;
            case "deadline":
                taskDescription = words[1];
                addDeadlineTask(taskDescription);
                break;
            case "todo":
                taskDescription = words[1];
                addTodoTask(taskDescription);
                break;
            case "event":
                taskDescription = words[1];
                addEventTask(taskDescription);
                break;
            case "mark":
            case "unmark":
                int index = Integer.parseInt(words[1]) - 1;
                boolean isMark = words[0].equals("mark");
                toggleMark(isMark, index);
                break;
            case "bye":
                terminate();
                break;
            default:
                System.out.println("An invalid command has been provided.");
            }
        }
        System.out.println(Message.GOODBYE.message);
        in.close(); // Closed to prevent memory leak
    }
}
