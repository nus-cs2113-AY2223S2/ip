package tasks;

/**
 * Represents a task of type event.
 * Contains a description, from and to.
 */
public class Event extends Task {
	protected String from;
	protected String to;
	
	/**
	 * Initialises event based on task with additional from and to.
	 * @param description Description of event.
	 * @param from Start of event.
	 * @param to End of event.
	 */
	public Event(String description, String from, String to) {
		super(description);
		this.from = from;
		this.to = to;
	}
	
	@Override
	public String toString() {
		return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
	}
	
	@Override
	public String getTaskType() {
		return "E";
	}
	
	@Override
	public String getTaskContent() {
		String output = description + " / " + from + " / " + to;
		return output;
	}
}