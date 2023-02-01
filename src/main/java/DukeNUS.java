import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class DukeNUS {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void addTodo(Todo todo) {
        tasks[taskCount] = todo;
        taskCount += 1;
        DukeNUSPrinter.printAddedTask(todo.getTaskString(), taskCount);
    }

    public static void addDeadline(Deadline deadline) {
        tasks[taskCount] = deadline;
        taskCount += 1;
        DukeNUSPrinter.printAddedTask(deadline.getTaskString(), taskCount);
    }

    public static void addEvent(Event event) {
        tasks[taskCount] = event;
        taskCount += 1;
        DukeNUSPrinter.printAddedTask(event.getTaskString(), taskCount);
    }

    public static void markTask(int taskIndex) {
        //TODO: ensure taskIndex is not <= 0
        tasks[taskIndex - 1].setDone(true);
        DukeNUSPrinter.printMessage("Congratulations! You have finished this task:\n" + "    " + tasks[taskIndex - 1].getTaskString());
    }

    public static void unmarkTask(int taskIndex) {
        //TODO: ensure taskIndex is not <= 0
        tasks[taskIndex - 1].setDone(false);
        DukeNUSPrinter.printMessage("The following task is now marked as undone:\n" + "    " + tasks[taskIndex - 1].getTaskString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DukeNUSPrinter.printWelcomeMessage();
        String user_input = scanner.nextLine();
        String[] user_input_words = user_input.split(" ", 2);
        while (!Objects.equals(user_input, "bye")) {
            switch (user_input_words[0]) {
            case "list":
                DukeNUSPrinter.printTasks(tasks, taskCount);
                break;
            case "mark":
                markTask(parseInt(user_input_words[1]));
                break;
            case "unmark":
                unmarkTask(parseInt(user_input_words[1]));
                break;
            case "todo":
                addTodo(new Todo(user_input_words[1]));
                break;
            case "deadline":
                String[] deadline_input = user_input_words[1].split("/", 2);
                addDeadline(new Deadline(deadline_input[0], deadline_input[1]));
                break;
            case "event":
                String[] event_input = user_input_words[1].split("/", 3);
                addEvent(new Event(event_input[0], event_input[1], event_input[2]));
                break;
            default:
                DukeNUSPrinter.printMessage("Unknown command. Please try again.");
                break;
            }
            user_input = scanner.nextLine();
            user_input_words = user_input.split(" ", 2);
        }
        DukeNUSPrinter.printFarewellMessage();
    }
}
