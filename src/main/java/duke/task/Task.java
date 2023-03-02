package duke.task;

import java.util.StringJoiner;

/**
 * Represents a task element to be in the list. A <code>Task</code> object corresponds to
 * any of these 3 types of tasks. (To-do, Deadline, Event)
 */
public class Task {
    public static final String LINE_BREAK = "---------------------------------------------";
    public String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public String getTypeIcon() {
        return "[]";
    }

    public void printTask(int itemNumber) {
        System.out.println(itemNumber + "." + this.getTypeIcon() + this.getStatusIcon() + this.description);
        System.out.println(LINE_BREAK);
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.getStatusIcon() + this.description);
        System.out.println(LINE_BREAK);
    }

    public void markAsDoneSilent() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(this.getStatusIcon() + this.description);
        System.out.println(LINE_BREAK);
    }

    public String toSaveString(String... taskParameters) {
        StringJoiner saveString = new StringJoiner("|");
        for (String string : taskParameters) {
            saveString.add(string);
        }
        return saveString.toString();
    }
}
