package Duke.task;

public class Event extends Task {
	private String startDate;
	private String endDate;

	public Event (String description, String startDate, String EndDate) {
		super (description);
		this.startDate = startDate;
		this.endDate = EndDate;
		this.type = "E";
	}

	@Override
	public String toString () {

		return super.toString () + "(from:" + startDate + "to:" + endDate + ")";
	}

	@Override
	public String toFile () {

		return super.toFile () + " | " + startDate + "-" + endDate;
	}
}
