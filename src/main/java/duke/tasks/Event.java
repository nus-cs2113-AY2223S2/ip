package duke.tasks;

public class Event extends Task {
	private String from;
	private String to;


	public Event(String description, String startTime, String endTime) {
		super(description);
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
	public void printTask() {
		if (isCompleted) {
			System.out.println("[E][√]" + getDescription());
		} else {
			System.out.println("[E][ ]" + getDescription());
		}
		System.out.print("(from: " + getStartTime());
		System.out.println(" to: " + getEndTime() + ")");
	}
}