package duke;

public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String userInput) {
        super();
        String[] userInputArray;
        userInputArray = userInput.split("/start", 2);
        String eventDescription = userInputArray[0].split("/event", 2)[1].trim();
        String eventStartTime = userInputArray[1].split("/end", 2)[0].trim();
        String eventEndTime = userInputArray[1].split("/end", 2)[1].trim();
        super.description = eventDescription;
        this.startTime = eventStartTime;
        this.endTime = eventEndTime;
    }

    @Override
    public String formattedString() {
        String formatted = "Event:" + super.isDone + ":" + super.description + ":" + startTime + ":" + endTime;
        return formatted;
    }

    @Override
    public String toString() {
        return "[EVENT]\n" + super.toString() + " (From: " + startTime + " | To: " + endTime + ")";
    }
}
