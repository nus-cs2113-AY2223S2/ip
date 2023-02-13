public class Task {
	protected String description;
	protected String type;
	protected boolean isDone;

	public Task (String description) {
		this.description = description;
		this.isDone = false;
		this.type = "[T]";
	}

	public String getType () {
		return type;
	}

	@Override
	public String toString () {
		return "[" + getStatusIcon () + "] " + description;
	}


	public String getStatusIcon () {
		return (isDone ? "X" : " ");
	}

}
