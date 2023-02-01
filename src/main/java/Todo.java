public class Todo extends Task {
	public Todo (String info) {
		super (info);
	}
	public String toString() {
		return "[T]" + super.toString();
	}
}
