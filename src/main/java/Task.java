public class Task {
	private String description;
	private boolean isCompleted;

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

	public String getStatusIcon() {
		return (isCompleted() ? "X" : " ");
	}

	public void markAsDone() {
		isCompleted = true;
		System.out.println("Nice! I've marked this task as done: ");
	}

	public void unmarkAsUndone() {
		isCompleted = false;
		System.out.println("OK, I've marked this task as not done yet: ");
	}

	void printTask() {
		System.out.println("["
				+getStatusIcon() + "] "
				+ getDescription());
	}
}