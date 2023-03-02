/**
 Handles Input/Output operations for Duke.
 */
import java.util.ArrayList;
public class IO {
	public IO(){
	}
	/**
	 * Prints a welcome message.
	 */
	public void printWelcome(){
		System.out.println("Hello! I'm Duke \nWhat can I do for you?");
	}
	/**
	 * Prints a goodbye message.
	 */
	public void printBye(){
		System.out.println("Bye. Hope to see you again soon!");
		System.exit(0);
	}
	/**
	 *Prints an acknowledgement message for adding a task to the list.
	 *@param "tasks" the current task list.
	 */
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
	/**
	 * Prints the current task list.
	 *
	 * @param tasks the current task list.
	 */
	public void printList(ArrayList<Task> tasks) {
		for (int i = 0; i < tasks.size(); i++) {
			System.out.print(i + 1 + ".");
			System.out.println(tasks.get(i).toString());
		}
	}
	/**
	 * Prints a message confirming that a task has been marked as completed.
	 *
	 * @param tasks the current task list.
	 * @param index the index of the completed task in the list.
	 */
	public void printMarked(ArrayList<Task> tasks, Integer index) {
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(tasks.get(index).toString());
	}
	/**
	 * Prints a message confirming that a task has been marked as incomplete.
	 *
	 * @param tasks the current task list.
	 * @param index the index of the incomplete task in the list.
	 */
	public void printUnmarked(ArrayList<Task> tasks, Integer index) {
		System.out.println("OK, I've marked this task as not done yet:");
		System.out.println(tasks.get(index).toString());
	}
	/**
	 * Prints a message confirming that a task has been deleted from the list.
	 *
	 * @param tasks the current task list.
	 * @param deletedTask the deleted task.
	 */
	public void printDeleted(ArrayList<Task> tasks, String deletedTask) {
		System.out.println("Noted. I've removed this task:");
		System.out.println(deletedTask);
		if (tasks.size() == 1) {
			System.out.println("Now you have " + tasks.size() + " task in the list.");
		} else {
			System.out.println("Now you have " + tasks.size() + " tasks in the list.");
		}
	}
	/**
	 * Prints a message indicating that matching tasks were found.
	 */
	public void printFind() {
		System.out.println("Here are the matching tasks in your list:");
	}
	/**
	 * Prints an error message indicating that the program is unable to write or append to the file.
	 */
	public void showCannotEditFile(){
		System.out.println("Unable to write/append to file.");
	}
	/**
	 * Prints an error message indicating that the program is unable to read from the file.
	 */
	public void showLoadingError(){
		System.out.println("Could not read file.");
	}
	/**
	 * Prints an error message indicating that the user has entered an invalid command.
	 */
	public void showInvalidCommand(){
		System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
	}
	/**
	 * Prints an error message indicating that the user has not provided any description for a todo task.
	 */
	public void showInsufficientTodo(){
		System.out.println("Unable to add todo: No tasks given.");
	}
	/**
	 * Prints an error message indicating that the user has not provided enough information to create an event task.
	 */
	public void showInsufficientEvent(){
		System.out.println("Not enough commands to execute \"event\"");
	}
	/**
	 * Prints an error message indicating that the user has not provided enough information to create a deadline task.
	 */
	public void showInsufficientDeadline(){
		System.out.println("Not enough commands to execute \"deadline\"");
	}

}