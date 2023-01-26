import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
    	ArrayList<Task> taskList = new ArrayList<Task>();
    	String taskName="";
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("\t____________________________________________________________\r\n"
        		+ "\t Hello! I'm Duke\r\n"
        		+ "\t What can I do for you?\r\n"
        		+ "\t____________________________________________________________\r\n");
    	
    	while (!taskName.equals("bye")) {
    		taskName = sc.next();
    		if (taskName.equals("list")) {
    			 System.out.println("\t____________________________________________________________\r\n");
    			 for(int i=0; i<taskList.size(); i++) {
    				 System.out.println("\t "+(i+1)+". "+taskList.get(i).getName());
    			 }
    			 System.out.println("\n\t____________________________________________________________\r\n");
    		} else if (!taskName.equals("bye")) {
				Task newTask = new Task(taskName);
				newTask.printAddTask();
				taskList.add(newTask);
    		}
    	}
        System.out.println("\t____________________________________________________________\r\n"
        		+ "\t Bye. Hope to see you again soon!\r\n"
        		+ "\t____________________________________________________________");
    }
}
