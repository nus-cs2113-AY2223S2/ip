package duke.tasks;

/**
 * Represents a deadline task type
 */
public class Deadline extends Task {
	private String by;

	/**
	 * Constructor of a Deadline object
	 *
	 * @param description task description
	 * @param by          deadline
	 * @param isComplete  task status
	 */
	public Deadline(String description, String by, boolean isComplete) {
		super(description, isComplete);
		this.by = by;
	}

	/**
	 * Get the deadline
	 *
	 * @return by the deadline
	 */
	public String getBy() {
		return by;
	}

	/**
	 * Format the task into certain format
	 *
	 * @return taskLine a complete task line to be displayed
	 */
	public String showTask() {
		String taskStatus;
		if (isCompleted == true) {
			taskStatus = "[D][√] ";
		} else {
			taskStatus = "[D][ ] ";
		}
		return taskStatus + getDescription() + " " + getBy();
	}

	/**
	 * Format the task into " D | task status | description | deadline"
	 *
	 * @return completeTaskLine a complete message line to be written in the file
	 */
	public String writeTask() {
		String taskStatus;
		if (isCompleted == false) {
			taskStatus = "0";
		} else {
			taskStatus = "1";
		}
		String completeTaskLine = "D | " + taskStatus + " | " + getDescription() + " | " + getBy() + "\n";
		return completeTaskLine;
	}
}