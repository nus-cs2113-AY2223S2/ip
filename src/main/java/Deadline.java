public class Deadline extends Task {
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getDescription() {
        String desc = "[D]" + super.getDescription();
        desc = desc.concat(" (by: " + this.dueDate + ")");
        return desc;
    }
}
