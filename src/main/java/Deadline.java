import java.util.ArrayList;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    protected String whenDue;
    static final int PARTITION_TO_TWO = 2;

    public Deadline(String description) {
        super(description);
        String[] lineComponents = description.split("/", PARTITION_TO_TWO);
        String[] descriptionComponents = lineComponents[0].split(" ", PARTITION_TO_TWO);
        String[] dueDatePrepositions = lineComponents[1].split(" ", PARTITION_TO_TWO);
        this.whenDue = "(" + dueDatePrepositions[0] + ": " + dueDatePrepositions[1] + ")";
        this.description = descriptionComponents[1] + this.whenDue;
        this.isDone = false;
        this.taskLabel = "[D]";
    }

    public Deadline() {
    }

    public static int add(String line, ArrayList<Task> list, int currentNumber) {
        System.out.println("     Got it. I've added this task:");
        Deadline newTask = new Deadline(line);
        System.out.println("       " + newTask.taskLabel + newTask.getStatusIcon() + " " + newTask.description);
        list.add(newTask);
        ++currentNumber;
        System.out.println("     Now you have " + currentNumber + " tasks in the list.");
        return currentNumber;
    }

}

