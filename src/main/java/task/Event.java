package task;

public class Event extends Task {
    private String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + time + ")";
    }
    @Override
    public String saveStringToFile() {
        return "E|" + super.saveStringToFile() + "|" + time;
    }
}
