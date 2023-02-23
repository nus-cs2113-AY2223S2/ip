package duke.tasks;

import java.time.format.DateTimeFormatter;

import static duke.main.Duke.printHorizontalLine;
import static duke.main.Duke.taskCount;

public class Task {
    private String description;
    private boolean isDone;

    private String type;
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy ha");


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTaskName() {
        return this.description;
    }

    public String getType() {
        return type;
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

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void printAdded() {
        printHorizontalLine();
        System.out.println(
                "Got it. I've added this task:\n" + this + "\nNow you have " + (taskCount + 1) + " in the list");
        printHorizontalLine();
    }
}