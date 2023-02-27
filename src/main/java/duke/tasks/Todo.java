package duke.tasks;

public class Todo extends Task {
    protected String type = "todo";
    public Todo (String description) {
        super(description);
    }

    @Override
    public void printTask() {
        System.out.println("[T][" + getStatusIcon() + "] " + description);
    }
}
