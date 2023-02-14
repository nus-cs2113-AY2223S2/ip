package duke.tasks;


public class Task {
	protected String description;
	protected boolean isCompleted;

	public Task(String description) {
		this.description = description;
		this.isCompleted = false;
	}

	public String getDescription() {
		return description;
	}
	public boolean isCompleted() {
		return isCompleted;
	}

	public String showTask(){
		return getDescription();
	}
}

