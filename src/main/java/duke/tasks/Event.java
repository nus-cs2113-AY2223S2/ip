package duke.tasks;//package Duke.java;

public class Event extends Task {
    private final String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getTask() {
        return String.format("[E]" + super.getTask() + "at" + date);
    }
}