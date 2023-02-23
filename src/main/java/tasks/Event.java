package tasks;

import tasks.Task;

public class Event extends Task {
	protected String from;
	protected String to;
	
	public Event(String description, String from, String to) {
		super(description);
		this.from = from;
		this.to = to;
	}
	
	@Override
	public String toString() {
		return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
	}
	
	@Override
	public String getTaskType() {
		return "E";
	}
	
	@Override
	public String getTaskContent() {
		String output = description + " / " + from + " / " + to;
		return output;
	}
}