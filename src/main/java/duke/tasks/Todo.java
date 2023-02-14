package duke.tasks;

public class Todo extends Task {
	public Todo(String description) {
		super(description);
	}

	public String showTask(){
		String taskStatus;
		if (isCompleted) {
			taskStatus= "[T][√] ";
		} else {
			taskStatus = "[T][ ] ";
		}
		return taskStatus + getDescription();
	}
}