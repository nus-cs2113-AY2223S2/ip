package wilsonoh.sagyo;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;
import wilsonoh.sagyo.tasks.Task;
import wilsonoh.sagyo.ui.TextFormatter;

public class Main {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Get the current tasks formatted in the form:
     * 1. Task1
     * 2. Task2
     * ...
     *
     * @return The list of task strings in formatted form
     */
    public String[] getTasksFormatted() {
        return IntStream.rangeClosed(1, tasks.size())
            .mapToObj(idx -> String.format("%d: %s", idx, tasks.get(idx - 1)))
            .toArray(String[] ::new);
    }

    /**
     * Adds a new task to the task list
     *
     * @param taskName Name of the new task
     */
    public void addTask(String taskName) { tasks.add(new Task(taskName)); }

    /**
     * Runner function of the class, executes the main loop
     */
    public void run() {
        boolean isRunning = true;
        TextFormatter ui = new TextFormatter(10);
        ui.printLines("Welcome to sagyo, your task manager!", "What can I do for you?");
        // try-with-resources to close the scanner automatically, preventing resource leaks
        try (Scanner sc = new Scanner(System.in)) {
            while (isRunning) {
                System.out.print("> ");
                String line = sc.nextLine();
                String[] tokens = line.split(" ");
                String command = tokens[0];
                switch (command) {
                case "bye":
                    ui.printLines("Bye. Hope to see you again soon!");
                    isRunning = false;
                    break;
                case "list":
                    ui.printLines(getTasksFormatted());
                    break;
                case "mark": {
                    int idx = Integer.parseInt(tokens[1]) - 1;
                    Task task = tasks.get(idx);
                    task.markDone();
                    ui.printLines("Nice! I've marked this task as done:", "  " + task.toString());
                    break;
                }
                case "unmark": {
                    int idx = Integer.parseInt(tokens[1]) - 1;
                    Task task = tasks.get(idx);
                    task.unMarkDone();
                    ui.printLines("OK, I've marked this task as not done yet:", "  " + task.toString());
                    break;
                }
                default:
                    addTask(line);
                    ui.printLines("added: " + line);
                }
            }
        }
    }

    public static void main(String[] args) { new Main().run(); }
}
