/**
 Represents a task that is a "todo item.
 Inherits from the Task class.
 */
public class Todo extends Task {
	/**
	 Constructs a Todo object with the specified information.
	 @param info the description of the todo item.
	 */
	public Todo (String info) {
		super (info);
	}
	public String toString() {
		return "[T]" + super.toString();
	}
}
