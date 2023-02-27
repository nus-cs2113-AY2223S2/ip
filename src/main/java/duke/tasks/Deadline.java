package duke.tasks;

public class Deadline extends Task {
	private String by;

	public Deadline(String description, String by, boolean isComplete) {
		super(description, isComplete);
		this.by = by;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String showTask() {
		String taskStatus;
		if (isCompleted == true) {
			taskStatus = "[D][√] ";
		} else {
			taskStatus = "[D][ ] ";
		}
		return taskStatus + getDescription() + " " + getBy();
	}

	public String writeTask() {
		String taskStatus;
		if (isCompleted == false) {
			taskStatus = "0";
		} else {
			taskStatus = "1";
		}
		return "D | " + taskStatus + " | " + getDescription() + " | " + getBy() + "\n";
	}
}