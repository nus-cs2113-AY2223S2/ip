public class Deadline extends Task {
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getDescription() {
        String description = "[D]" + super.getDescription();
        description = description.concat(" (by: " + this.dueDate + ")");
        return description;
    }
}
