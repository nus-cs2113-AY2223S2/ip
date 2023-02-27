package duke.tasks;

public class Event extends Task {
	private String from;
	private String to;


	public Event(String description, String startTime, String endTime, boolean isCompleted) {
		super(description, isCompleted);
		this.from = startTime;
		this.to = endTime;
	}

	public String getStartTime() {
		return from;
	}

	public String getEndTime() {
		return to;
	}

	public void setStartTime(String startTime) {
		this.from = startTime;
	}

	public void setEndTime(String endTime) {
		this.to = endTime;
	}

	// Prints task
	public String showTask() {
		String taskStatus;
		if (isCompleted == true) {
			taskStatus = "[E][√] ";
		} else {
			taskStatus = "[E][ ] ";
		}
		String time = "from: " + getStartTime() + " to: " + getEndTime();
		return taskStatus + getDescription() + " " + time;
	}

	public String writeTask() {
		String taskStatus;
		if (isCompleted == false) {
			taskStatus = "0";
		} else {
			taskStatus = "1";
		}
		return "E | " + taskStatus + " | " + getDescription() + " | " +
				getStartTime() + " | " + getEndTime() + "\n";
	}
}