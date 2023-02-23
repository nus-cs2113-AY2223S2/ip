package tasks;

import tasks.Task;

public class Deadline extends Task {
	protected String by;
	
	public Deadline(String description, String by) {
		super(description);
		this.by = by;
	}
	
	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + by + ")";
	}
	
	@Override
	public String getTaskType() {
		return "D";
	}
	
	@Override
	public String getTaskContent() {
		String output = description + " / " + by;
		return output;
	}
}
