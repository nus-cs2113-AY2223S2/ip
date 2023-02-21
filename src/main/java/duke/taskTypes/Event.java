package duke.taskTypes;

import duke.task.Task;

public class Event extends Task {
	protected String from;
	protected String to;

	@Override
	public void setEvent() {
		super.setEvent();
	}

	@Override
	public String getFrom() {
		return from;
	}

	@Override
	public String getTo() {
		return to;
	}

	public Event(String description, String from, String to) {
		super(description);
		this.from = from;
		this.to = to;
		this.setEvent();
	}
}
