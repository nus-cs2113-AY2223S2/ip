package duke.taskTypes;

import duke.task.Task;

public class Deadline extends Task {
	protected String by;

	@Override
	public void setDeadline() {
		super.setDeadline();
	}

	@Override
	public String getBy() {
		return by;
	}

	public Deadline(String description, String by) {
		super(description);
		this.by = by;
		this.setDeadline();
	}
}
