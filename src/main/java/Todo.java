import java.util.ArrayList;

/**
 * Represents a Todo task.
 */

public class Todo extends Task {
    static final int PARTITION_TO_TWO = 2;

    public Todo(String description) {
        super(description);
        String[] lineComponents = description.split(" ", PARTITION_TO_TWO);
        this.description = lineComponents[1];
        this.taskLabel = "[T]";
    }

    public Todo() {
    }

    public static int add(String line, ArrayList<Task> list, int currentNumber) {
        System.out.println("     Got it. I've added this task:");
        Todo newTask = new Todo(line);
        System.out.println("       " + newTask.taskLabel + newTask.getStatusIcon()
                + " " + newTask.description);
        list.add(newTask);
        ++currentNumber;
        System.out.println("     Now you have " + currentNumber + " tasks in the list.");
        return currentNumber;
    }
}
