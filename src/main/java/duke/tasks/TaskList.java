package duke.tasks;

import java.util.ArrayList;

/**
 * Represent a task list
 */
public class TaskList {

	private final String TASK_COUNTED_START =
			"There are ";
	private final String TASK_COUNTED_ENDED =
			" tasks in your list.";

	public ArrayList<Task> tasks = new ArrayList<>();

	public TaskList() {
	}

	/**
	 * Show the task according to the index
	 *
	 * @param taskIndex the task index number
	 * @return message the message line
	 */
	public String showTaskMessage(int taskIndex) {
		String message = tasks.get(taskIndex).showTask();
		return message;
	}

	/**
	 * Show the total number of tasks
	 *
	 * @return message the message line to show the task list number
	 */
	public String showTaskNumber() {
		String message = TASK_COUNTED_START + tasks.size() + TASK_COUNTED_ENDED;
		return message;
	}

	/**
	 * Get the total number of tasks in the list
	 *
	 * @return task.size() the total number of tasks in the list
	 */

	public int countTaskNumber() {
		return tasks.size();
	}

	/**
	 * Print the task list
	 */
	public void printList() {
		for (int i = 1; i <= tasks.size(); i += 1) {
			System.out.println(i + ". " + tasks.get(i - 1).showTask());
		}
	}

	/**
	 * Find tasks which have description containing the keywords
	 *
	 * @param taskIndex Search term to find within tasks' description
	 * @return a String representing the list of items that contain the search term
	 */
	public Task findTask(int taskIndex) {
		return tasks.get(taskIndex - 1);
	}

	/**
	 * This method is used when loading the task
	 * Add the task to the task list
	 *
	 * @param task the task to be added
	 */
	public void addTasks(Task task) {
		tasks.add(task);
	}

	/**
	 * Add todo task in the task list and display the successful message
	 *
	 * @param description task description
	 * @param isCompleted status of the task
	 */
	public void addTodo(String description, boolean isCompleted) {
		tasks.add(new Todo(description, isCompleted));
		System.out.println(showTaskMessage(tasks.size() - 1));
	}

	/**
	 * Add deadline task in the task list and display the successful message
	 *
	 * @param description task description
	 * @param by          the deadline of the task
	 * @param isCompleted status of the task
	 */
	public void addDeadline(String description, String by, boolean isCompleted) {
		tasks.add(new Deadline(description, by, isCompleted));
		System.out.println(showTaskMessage(tasks.size() - 1));
	}

	/**
	 * @param description task description
	 * @param from        the event starting time
	 * @param to          the event end tim
	 * @param isCompleted status of the task
	 */
	public void addEvent(String description, String from, String to, boolean isCompleted) {
		tasks.add(new Event(description, from, to, isCompleted));
		System.out.println(showTaskMessage(tasks.size() - 1));
	}

	/**
	 * Delete the task according to the task index
	 *
	 * @param taskIndex the task index number
	 */
	public void deleteTask(int taskIndex) {
		String message = tasks.get(taskIndex - 1).showTask();
		tasks.remove(taskIndex - 1);
		System.out.println(message + "\n " + showTaskNumber());
	}

	/**
	 * Mark the task as done according to task index
	 *
	 * @param taskIndex the task index number
	 */

	public void markAsDone(int taskIndex) {
		tasks.get(taskIndex - 1).isCompleted = true;
	}

	/**
	 * Mark the task as undone according to task index
	 *
	 * @param taskIndex the task index number
	 */
	public void markAsUndone(int taskIndex) {
		tasks.get(taskIndex - 1).isCompleted = false;
	}

	/**
	 * Combine the task list into one String
	 *
	 * @return tasksToBeAdded a String of task list to be written in the file
	 */
	public String writeTaskList() {
		String tasksToBeAdded = "";
		for (Task task : tasks) {
			tasksToBeAdded = tasksToBeAdded + task.writeTask();
		}
		return tasksToBeAdded;
	}
}
