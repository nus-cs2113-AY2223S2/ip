public class Task {
	protected String name;
	protected boolean isCompleted;
	public Task(String name, boolean isCompleted) {
		this.name = name;
		isCompleted = false;
	}

	public String markTask (){
		return(isCompleted?"[X]":"[ ]");
	}
}

