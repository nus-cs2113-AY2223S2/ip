package duke.tasks;


public class Task {
	protected String description;
	protected boolean isCompleted;

	public Task(String description, boolean isCompleted) {
		this.description = description;
		this.isCompleted = isCompleted;
	}

	public String getDescription() {
		return description;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public String showTask() {
		return getDescription();
	}

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

