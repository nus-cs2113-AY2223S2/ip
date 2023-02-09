package duke.tasks;

public class Todo extends Task {
	public Todo(String description) {
		super(description);
	}

	public void printTask(){
		if (isCompleted) {
			System.out.println("[T][√]" + getDescription());
		} else {
			System.out.println("[T][ ]" + getDescription());
		}
	}
}