package Task;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, String start, String end, boolean isDone){
        super(description, isDone);
        this.start=start;
        this.end=end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + start + " " + end + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | "
                + start + " | " + end;
    }

    public static Event fromFileString(String line) {
        String[] parts = line.split(" \\| ");
        boolean isDone = Integer.parseInt(parts[1]) == 1;
        String description = parts[2];
        String start = parts[3];
        String end=parts[4];
        return new Event(description, start, end, isDone);
    }
}
