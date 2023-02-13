package max.task;

public class Deadline extends Task {
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getDescription() {
        String description = "[" + getTaskLabel() + "]" + super.getDescription();
        description = description.concat(" (by: " + this.dueDate + ")");
        return description;
    }

    @Override
    public String getTaskLabel() {
        return "D";
    }

    public String getDueDate() {
        return dueDate;
    }
}
