/**
 Represents a task that is an event with a start time and end time.
 Inherits from the Task class.
 */
public class Event extends Task {
	protected String startTime;
	protected String endTime;
	/**
	 Constructs an Event object with the specified information, start time and end time.
	 @param info the description of the event.
	 @param startTime the start time of the event.
	 @param endTime the end time of the event.
	 */
	public Event(String info, String startTime, String endTime) {
		super(info);
		this.startTime = startTime;
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "[E]" + super.toString() + "(from: "  + startTime + " to: " + endTime + ")";
	}
}
