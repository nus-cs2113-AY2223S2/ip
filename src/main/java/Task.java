
public class Task {
	private String name;
	
	public Task(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void printAddTask() {
		 System.out.println("\t____________________________________________________________\r\n"
	        		+ "\t added: "+getName()+"\r\n"
	        		+ "\t____________________________________________________________\r\n");
	}
}
