package duke.tasks;

public class Deadline extends Task {
	private String by;

	public Deadline(String description, String by) {
		super(description);
		this.by = by;
		this.isCompleted = false;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String showTask() {
		String taskStatus;
		if (isCompleted) {
			taskStatus = "[D][√] ";
		} else {
			taskStatus = "[D][ ] ";
		}
		return taskStatus + getDescription() + " " + getBy();
	}
}