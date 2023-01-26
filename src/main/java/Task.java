
public class Task {
	private String name;
	private boolean isDone;
	
	public Task(String name) {
		this.name = name;
		this.isDone = false;
	}
	
	public String getName(){
		return name;
	}
	
	public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
	
	public void setStatus(boolean status) {
		this.isDone = status;
	}
	
	public void printAddTask() {
		System.out.println("\t____________________________________________________________\r\n"
	        		+ "\t added: " + getName() +"\r\n"
	        		+ "\t____________________________________________________________\r\n");
	}
	
	public void printMarkMessage() {
		System.out.println("\t____________________________________________________________\r\n"
				+ "\t Nice! I've marked this task as done:\r\n"
				+ "\t [X] " + getName() +"\r\n"
				+ "\t____________________________________________________________\r\n");
	}
	
	public void printUnmarkMessage() {
		System.out.println("\t____________________________________________________________\r\n"
				+ "\t OK, I've marked this task as not done yet:\r\n"
				+ "\t [ ] " + getName() +"\r\n"
				+ "\t____________________________________________________________\r\n");
	}
}
