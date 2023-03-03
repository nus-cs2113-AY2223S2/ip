package duke;

/**
 * Represents a <code>Task</code> task that can be added to the list. A <code>Task</code> object only stores a
 * description of the task.
 */

public class Todo extends Task {
    public boolean isDone;
    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    public void setDone(boolean status) {
        isDone = status;
    }

    public boolean isDone() {
        return isDone;
    }

    public void print() {
        System.out.println("    _________________________________________");
        System.out.println("    " + "added: " + description);
        System.out.println("    _________________________________________");
        System.out.println("    ");
    }

    public void printInList() {
        if (isDone) {
            System.out.println(" " + "[T][X] " + description);
        } else {
            System.out.println(" " + "[T][ ] " + description);
        }
    }
}
