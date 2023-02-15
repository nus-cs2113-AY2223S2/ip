public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        initialize();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public void initialize() {
        TaskArray.count++;
        System.out.println(UI.LINE +
                "     Got it. I've added this task:\n" + this +
                "\n     Now you have " + TaskArray.count + " tasks in the list.\n" +
                UI.LINE);
    }

    public String toFile() {
        return this.isDone + " | " +"d"+ this.description + " by " + this.by + "\n";
    }
}
