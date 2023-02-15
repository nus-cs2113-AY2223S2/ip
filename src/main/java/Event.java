public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        initialize();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    public void initialize() {
        TaskArray.count++;
        System.out.println(UI.LINE +
                "     Got it. I've added this task:\n" +
                "        " + this +
                "\n     Now you have " + TaskArray.count + " tasks in the list.\n" +
                UI.LINE);
    }

    public String toFile() {
        return this.isDone + " | " +"e"+ this.description + " from " + this.from + " to " + this.to + "\n";
    }
}
