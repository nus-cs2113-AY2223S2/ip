import java.util.Objects;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class DukeNUS {
    private static Task[] tasks = new Task[100]; //Assuming the user will not have more than 100 tasks.
    private static int taskCount = 0; //The count of the valid objects in the tasks array that is incremented on pushing into it.

    /**
     * @param taskIndex The 1-based index of the task the user is referring to.
     * @return True if the index provided refers to a task within the bounds of the task array and false if not.
     */
    private static boolean isTaskIndexValid(int taskIndex) {
        if (taskIndex <= 0) {
            DukeNUSPrinter.printMessage("Error: The task index you have provided is not valid.");
            return false;
        }
        if (taskIndex > taskCount) {
            DukeNUSPrinter.printMessage("Error: The task index you have provided returns a null task.");
            return false;
        }
        return true;
    }

    /**
     * @param todo A newly constructed todo object as a child of Task that has a user-defined description.
     */
    public static void addTodo(Todo todo) {
        tasks[taskCount] = todo;
        taskCount += 1;
        DukeNUSPrinter.printAddedTask(todo.getTaskString(), taskCount);
    }

    /**
     * @param deadline A newly constructed deadline object as a child of Task that has a user-defined description
     *                and due date.
     */
    public static void addDeadline(Deadline deadline) {
        tasks[taskCount] = deadline;
        taskCount += 1;
        DukeNUSPrinter.printAddedTask(deadline.getTaskString(), taskCount);
    }

    /**
     * @param event A newly constructed event object as a child of Task that has a user-defined description,
     *              from date, and to date.
     */
    public static void addEvent(Event event) {
        tasks[taskCount] = event;
        taskCount += 1;
        DukeNUSPrinter.printAddedTask(event.getTaskString(), taskCount);
    }

    /**
     * @param taskIndex The 1-based index of the task the user is referring to. Will cause the isDone property of the
     *                  task to be true.
     */
    public static void markTask(int taskIndex) {
        tasks[taskIndex - 1].setDone(true);
        DukeNUSPrinter.printMarkedTask(tasks[taskIndex - 1].getTaskString());
    }

    /**
     * @param taskIndex The 1-based index of the task the user is referring to. Will cause the isDone property of the
     *                  task to be false.
     */
    public static void unmarkTask(int taskIndex) {
        tasks[taskIndex - 1].setDone(false);
        DukeNUSPrinter.printUnmarkedTask(tasks[taskIndex - 1].getTaskString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DukeNUSPrinter.printWelcomeMessage();
        String userInput = scanner.nextLine();
        String[] userInputWords = userInput.split(" ", 2); //Contains 2 strings from the user delimited by spaces. The first word is the command and the subsequent words are arguments.
        //Continue reading the first word until "bye" is said.
        while (!Objects.equals(userInput, "bye")) {
            switch (userInputWords[0]) {
            case "list":
                if (taskCount == 0) {
                    DukeNUSPrinter.printMessage("You have no tasks. ¯\\_(ツ)_/¯");
                } else {
                    DukeNUSPrinter.printTasks(tasks, taskCount);
                }
                break;
            case "mark":
                if (isTaskIndexValid(parseInt(userInputWords[1]))) {
                    markTask(parseInt(userInputWords[1]));
                }
                break;
            case "unmark":
                if (isTaskIndexValid(parseInt(userInputWords[1]))) {
                    unmarkTask(parseInt(userInputWords[1]));
                }
                break;
            case "todo":
                addTodo(new Todo(userInputWords[1]));
                break;
            case "deadline":
                String[] deadlineInput = userInputWords[1].split("/", 2);
                addDeadline(new Deadline(deadlineInput[0], deadlineInput[1]));
                break;
            case "event":
                String[] eventInput = userInputWords[1].split("/", 3);
                addEvent(new Event(eventInput[0], eventInput[1], eventInput[2]));
                break;
            default:
                DukeNUSPrinter.printMessage("Unknown command. Please try again.");
                break;
            }
            userInput = scanner.nextLine();
            userInputWords = userInput.split(" ", 2);
        }
        DukeNUSPrinter.printFarewellMessage();
    }
}
