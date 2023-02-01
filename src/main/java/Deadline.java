public class Deadline extends Task {
	private String by;

	public Deadline(String description, String by) {
		super(description);
		this.by = by;
		this.isCompleted = false;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public void printTask() {
		if (isCompleted) {
			System.out.print("[D][√]" + getDescription());
		} else {
			System.out.print("[D][ ]" + getDescription());
		}
		System.out.println(" (by: " + getBy() + ")");
	}
}