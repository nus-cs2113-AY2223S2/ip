package duke.tasks;

/**
 * Represent a to do task type
 */
public class Todo extends Task {
	public Todo(String description, boolean isCompleted) {
		super(description, isCompleted);
	}

	/**
	 * To show the task status and its description
	 *
	 * @return a string of task status and its description
	 */
	public String showTask() {
		String taskStatus;
		if (isCompleted) {
			taskStatus = "[T][√] ";
		} else {
			taskStatus = "[T][ ] ";
		}
		return taskStatus + getDescription();
	}

	/**
	 * Format the task into " T | task status | description"
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
		return "T | " + taskStatus + " | " + getDescription() + "\n";
	}
}