/**
 Represents a task with a deadline.
 Inherits from the Task class.
 */
public class Deadline extends Task {
	protected String due;
	/**
	 Constructs a Deadline object with the specified information and deadline.
	 @param info the description of the task.
	 @param due the deadline of the task.
	 */
	public Deadline(String info, String due) {
		super(info);
		this.due = due;
	}
	@Override
	public String toString(){
		return "[D]" + super.toString() + "(by: " + due + ")";
	}
}
