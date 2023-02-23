package Duke.task;

public class Task {
	protected String description;
	protected String type;
	protected boolean isDone;

	public Task (String description) {
		this.description = description;
		this.isDone = false;
		this.type = "T";
	}

	public boolean getIsDone () {

		return isDone;
	}

	public void setIsDone (boolean done) {

		isDone = done;
	}

	public String getDescription () {
		return description;
	}

	public String getType () {

		return type;
	}

	@Override
	public String toString () {

		return "[" + getStatusIcon () + "] " + description;
	}

	public String toFile () {
		return getType () + " | " + (getIsDone () ? "1" : "0") + " | " + description;
	}

	public String getStatusIcon () {

		return (isDone ? "X" : " ");
	}

}
