public class Deadline extends Task {
	protected String due;
	public Deadline(String info, String due) {
		super(info);
		this.due = due;
	}
	@Override
	public String toString(){
		return "[D]" + super.toString() + "(by: " + due + ")";
	}
}
