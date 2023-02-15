package duke.tasks;

public class Todo extends Task {

    public Todo (String description) {
        super(description);
    }

    @Override
    public void printTask() {
        System.out.println("[T][" + getStatusIcon() + "] " + description);
    }
}