import java.util.ArrayList;

public class IO {
	public IO(){
	}
	public void printWelcome(){
		System.out.println("Hello! I'm Duke \nWhat can I do for you?");
	}
	public void printBye(){
		System.out.println("Bye. Hope to see you again soon!");
	}
	public void printAcknowledgement(ArrayList<Task> tasks) {
		int lastIndexOfTasks = tasks.size() - 1;
		System.out.println("Got it. I've added this task:");
		System.out.println(tasks.get(lastIndexOfTasks).toString());
		if (tasks.size() == 1){
			System.out.println("Now you have " + tasks.size()+ " task in the list.");
		} else {
			System.out.println("Now you have " + tasks.size() + " tasks in the list.");
		}
	}
	public void printList(ArrayList<Task> tasks) {
		for (int i = 0; i < tasks.size(); i++) {
			System.out.print(i + 1 + ".");
			System.out.println(tasks.get(i).toString());
		}
	}
	public void printMarked(ArrayList<Task> tasks, Integer index) {
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(tasks.get(index).toString());
	}
	public void printUnmarked(ArrayList<Task> tasks, Integer index) {
		System.out.println("OK, I've marked this task as not done yet:");
		System.out.println(tasks.get(index).toString());
	}
	public void printDeleted(ArrayList<Task> tasks, String deletedTask) {
		System.out.println("Noted. I've removed this task:");
		System.out.println(deletedTask);
		if (tasks.size() == 1) {
			System.out.println("Now you have " + tasks.size() + " task in the list.");
		} else {
			System.out.println("Now you have " + tasks.size() + " tasks in the list.");
		}
	}
	public void showCannotEditFile(){
		System.out.println("Unable to write/append to file.");
	}
	public void showLoadingError(){
		System.out.println("Could not read file.");
	}
	public void showInvalidCommand(){
		System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
	}
	public void showInsufficientTodo(){
		System.out.println("Unable to add todo: No tasks given.");
	}
	public void showInsufficientEvent(){
		System.out.println("Not enough commands to execute \"event\"");
	}
	public void showInsufficientDeadline(){
		System.out.println("Not enough commands to execute \"deadline\"");
	}

}