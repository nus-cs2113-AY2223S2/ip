public class Event extends Task{

    protected String eventDetails;
    protected String eventDetailsPartOne;// = eventDetails.substring(0,index);
    protected String eventDetailsPartTwo;// = eventDetailsPartOne.substring(index + 4);

    public Event(String description, String eventDetailsPartOne, String eventDetailsPartTwo) {
        super(description);
        this.eventDetailsPartOne = eventDetailsPartOne;
        this.eventDetailsPartTwo = eventDetailsPartTwo;
    }

    // Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventDetailsPartOne + " to: " + eventDetailsPartTwo + ")";
    }
}
