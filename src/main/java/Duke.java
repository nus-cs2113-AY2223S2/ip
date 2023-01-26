import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
    	String taskName="";
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("\t____________________________________________________________\r\n"
        		+ "\t Hello! I'm Duke\r\n"
        		+ "\t What can I do for you?\r\n"
        		+ "\t____________________________________________________________\r\n");
    	
    	taskName = sc.next();
    	while (!taskName.equals("bye")) {
    		Task newTask = new Task(taskName);
    		newTask.printTask();
    		taskName = sc.next();
    	}
    	
        System.out.println("\t____________________________________________________________\r\n"
        		+ "\t Bye. Hope to see you again soon!\r\n"
        		+ "\t____________________________________________________________");
    }
}
