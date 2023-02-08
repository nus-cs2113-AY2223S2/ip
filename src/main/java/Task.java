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

	public void markAsDone() throws TaskException {
		if (isCompleted == true) {
			throw new TaskException();
		}
		isCompleted = true;
	}

	public void markAsUndone() throws TaskException {
		if (isCompleted == false) {
			throw new TaskException();
		}
		isCompleted = false;
	}
	public void printTask(){
		System.out.println(description);
	}
}

