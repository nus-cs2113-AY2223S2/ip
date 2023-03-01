package tasks;

/**
 * Represents a task of type todo.
 * Contains a description.
 */
public class Todo extends Task {
	
	public static final String TASK_SYMBOL_SAVE_FILE = "T";
	public static final String TASK_SYMBOL = "[T]";
	
	/**
	 * Initialises todo based on task.
	 * @param description Description of todo.
	 */
	public Todo(String description) {
		super(description);
	}
	
	@Override
	public String toString() {
		return TASK_SYMBOL + super.toString();
	}
	
	@Override
	public String getTaskType() {
		return TASK_SYMBOL_SAVE_FILE;
	}
	
	@Override
	public String getTaskContent() {
		return description;
	}
}