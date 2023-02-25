public class Task {
	protected String name;
	protected boolean isCompleted;
	public Task(String info) {
		this.name = info;
	}
	public String toString () {
		return(isCompleted?"[X]"+this.name:"[ ]"+this.name);
	}
}