/**
 Represents a general task with a name and completion status.
 */
public class Task {
	protected String name;
	protected boolean isCompleted;
	/**
	 Constructs a Task object with the specified name.
	 @param info the name of the task.
	 */
	public Task(String info) {
		this.name = info;
	}
	public String toString () {
		return(isCompleted?"[X]"+this.name:"[ ]"+this.name);
	}
}