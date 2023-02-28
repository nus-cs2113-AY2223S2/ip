package tasks;

/**
 * Represents a task of type deadline.
 * Contains a description and by.
 */
public class Deadline extends Task {
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
		return "[D]" + super.toString() + " (by: " + by + ")";
	}
	
	@Override
	public String getTaskType() {
		return "D";
	}
	
	@Override
	public String getTaskContent() {
		String output = description + " / " + by;
		return output;
	}
}
