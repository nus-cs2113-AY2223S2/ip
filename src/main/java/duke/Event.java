package duke;

public class Event extends Task {

    private String date;

    public Event(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + date + ")" ;
    }
}
