public class Event extends Task{
    protected String eventDetailsPartOne;
    protected String eventDetailsPartTwo;

    public Event(String description, String eventDetailsPartOne, String eventDetailsPartTwo) {
        super(description);
        this.eventDetailsPartOne = eventDetailsPartOne;
        this.eventDetailsPartTwo = eventDetailsPartTwo;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventDetailsPartOne + " to: " + eventDetailsPartTwo + ")";
    }
}
