import java.util.Objects;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount += 1;
        DukePrinter.printMessage("added: " + task.getDescription());
    }
    public static void markTask(int taskIndex) {
        //TODO: ensure taskIndex is not <= 0
        tasks[taskIndex - 1].setDone(true);
        DukePrinter.printMessage("Nice! I've marked this task as done:\n" + "    " + tasks[taskIndex - 1].getTaskString());
    }
    public static void unmarkTask(int taskIndex) {
        //TODO: ensure taskIndex is not <= 0
        tasks[taskIndex - 1].setDone(false);
        DukePrinter.printMessage("OK, I've marked this task as not done yet:\n" + "    " + tasks[taskIndex - 1].getTaskString());
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DukePrinter.printWelcomeMessage();
        String user_input = scanner.nextLine();
        String[] user_input_words = user_input.split(" ");
        while (!Objects.equals(user_input, "bye")) {
            switch (user_input_words[0]) {
            case "list":
                DukePrinter.printTasks(tasks, taskCount);
                break;
            case "mark":
                markTask(parseInt(user_input_words[1]));
                break;
            case "unmark":
                unmarkTask(parseInt(user_input_words[1]));
                break;
            default:
                addTask(new Task(user_input));
                break;
            }
            user_input = scanner.nextLine();
            user_input_words = user_input.split(" ");
        }
        DukePrinter.printFarewellMessage();
    }
}
