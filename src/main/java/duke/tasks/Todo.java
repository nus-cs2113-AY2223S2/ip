package duke.tasks;

public class Todo extends Task {
	public Todo(String description, boolean isCompleted) {
		super(description, isCompleted);
	}

	public String showTask() {
		String taskStatus;
		if (isCompleted) {
			taskStatus = "[T][√] ";
		} else {
			taskStatus = "[T][ ] ";
		}
		return taskStatus + getDescription();
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