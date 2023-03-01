package tasks;

/**
 * Represents a task of type deadline.
 * Contains a description and by.
 */
public class Deadline extends Task {
	public static final String DEADLINE_SYMBOL = "[D]";
	public static final String DEADLINE_SYMBOL_SAVE_FILE = "D";
	public static final String BY_STRING = " (by: ";
	public static final String CLOSING_BRACKET = ")";
	protected String by;
	
	/**
	 * Initialises deadline based on task with additional by.
	 * @param description Description of deadline.
	 * @param by Deadline of deadline task.
	 */
	public Deadline(String description, String by) {
		super(description);
		this.by = by;
	}
	
	@Override
	public String toString() {
		return DEADLINE_SYMBOL + super.toString() + BY_STRING + by + CLOSING_BRACKET;
	}
	
	@Override
	public String getTaskType() {
		return DEADLINE_SYMBOL_SAVE_FILE;
	}
	
	@Override
	public String getTaskContent() {
		String output = description + " / " + by;
		return output;
	}
}
