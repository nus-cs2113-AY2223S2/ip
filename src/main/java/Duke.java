import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {

    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Variadic print function wrapping System.out.println, printing the
     * lines out surrounded by 2 dividers and indented
     *
     * @param lines The text to be printed out. Each argument denotes a piece of
     *              text to be printed out
     *              on a new line
     */
    public void printMessage(String... lines) {
        System.out.println("\t────────────────────────────────────────────────────────────");
        for (String line : lines) {
            System.out.println("\t" + line);
        }
        System.out.println("\t────────────────────────────────────────────────────────────");
    }

    public String[] getTasksFormatted() {
        return IntStream.rangeClosed(1, tasks.size())
            .mapToObj(idx -> String.format("%d: %s", idx, tasks.get(idx - 1)))
            .toArray(String[] ::new);
    }

    public void addTask(String taskName) { tasks.add(new Task(taskName)); }

    public void run() {
        printMessage("Hello! I'm Duke", "What can I do for you?");
        // try-with-resources to close the scanner automatically, preventing resource leaks
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                String line = sc.nextLine();
                String[] tokens = line.split(" ");
                String command = tokens[0];
                switch (command) {
                case "bye":
                    printMessage("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    printMessage(getTasksFormatted());
                    break;
                case "mark": {
                    int idx = Integer.parseInt(tokens[1]) - 1;
                    Task task = tasks.get(idx);
                    task.markDone();
                    printMessage("Nice! I've marked this task as done:", "  " + task.toString());
                    break;
                }
                case "unmark": {
                    int idx = Integer.parseInt(tokens[1]) - 1;
                    Task task = tasks.get(idx);
                    task.unMarkDone();
                    printMessage("OK, I've marked this task as not done yet:", "  " + task.toString());
                    break;
                }
                default:
                    addTask(line);
                    printMessage("added: " + line);
                }
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
