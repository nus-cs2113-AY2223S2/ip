package wilsonoh.sagyo;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

import wilsonoh.sagyo.parser.TaskParser;
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
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Runner function of the class, executes the main loop
     */
    public void run() {
        boolean isRunning = true;
        TaskParser parser = new TaskParser();
        TextFormatter ui = new TextFormatter(2, 120);
        ui.printLines("Welcome to sagyo, your task manager!", "What can I do for you?");
        // try-with-resources to close the scanner automatically, preventing resource leaks
        try (Scanner sc = new Scanner(System.in)) {
            while (isRunning) {
                System.out.print("> ");
                String line = sc.nextLine();
                String[] tokens = line.split(" ");
                String command = tokens[0];
                switch (command) {
                case "bye": {
                    ui.printLines("Bye. Hope to see you again soon!");
                    isRunning = false;
                    break;
                }
                case "list": {
                    ui.printLines(getTasksFormatted());
                    break;
                }
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
                default: {
                    Optional<Task> task = parser.parseInput(line);
                    if (task.isEmpty()) {
                        ui.printLines(String.format("%s is not a valid command or task syntax", line),
                                      "Please try again.");
                    } else {
                        addTask(task.get());
                        ui.printLines("added task: " + task.get());
                    }
                }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
