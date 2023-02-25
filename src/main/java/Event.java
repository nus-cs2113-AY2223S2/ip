public class Event extends Task {
	protected String startTime;
	protected String endTime;
	public Event(String info, String startTime, String endTime) {
		super(info);
		this.startTime = startTime;
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "[E]" + super.toString() + "(from: "  + startTime + " to: " + endTime + ")";
	}
}
