package duke.tasks;

import java.time.format.DateTimeFormatter;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;

/**
 * Represent a task whenever task is added
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Construct a task object with taskName and task is marked as undone by default
     *
     * @param description
     */

    private String type;
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy ha");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTaskName() {
        return this.description;
    }

    /**
     * @return the task type ( todo, deadline , event)
     */
    public String getType() {
        return "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * message print whenever new task is added
     */
    public void printAdded() {
        printHorizontalLine();
        System.out.println(
                "Got it. I've added this task:\n" + this + "\nNow you have " + (taskCount + 1) + " in the list");
        printHorizontalLine();
    }
}