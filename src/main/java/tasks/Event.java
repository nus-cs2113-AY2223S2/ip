package tasks;

/**
 * Represents a task of type event.
 * Contains a description, from and to.
 */
public class Event extends Task {
	public static final String TO_STRING = " to: ";
	public static final String FROM_STRING = " (from: ";
	public static final String CLOSING_BRACKET = ")";
	public static final String EVENT_SYMBOL = "[E]";
	public static final String EVENT_SYMBOL_SAVE_FILE = "E";
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
		return EVENT_SYMBOL + super.toString() + FROM_STRING + from + TO_STRING + to + CLOSING_BRACKET;
	}
	
	@Override
	public String getTaskType() {
		return EVENT_SYMBOL_SAVE_FILE;
	}
	
	@Override
	public String getTaskContent() {
		String output = description + " / " + from + " / " + to;
		return output;
	}
}