public class Task {
	protected String name;
	protected boolean isCompleted;

	public Task(String info) {
		this.name = info;
	}
	public Task(String info, boolean isCompleted) {
		this.name = info;
		isCompleted = false;
	}
	public String toString () {
		return(isCompleted?"[X]"+this.name:"[ ]"+this.name);
	}
}