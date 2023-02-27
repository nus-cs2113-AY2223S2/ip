package duke.tasks;

import java.util.ArrayList;

public class TaskList {

	private final String TASK_COUNTED_START =
			"There are ";
	private final String TASK_COUNTED_ENDED =
			" tasks in your list.";

	public ArrayList<Task> tasks = new ArrayList<>();

	public TaskList() {
	}


	public String showTaskMessage(int taskIndex) {
		return tasks.get(taskIndex).showTask();
	}

	public String showTaskNumber() {
		return TASK_COUNTED_START + tasks.size() + TASK_COUNTED_ENDED;
	}

	public int countTaskNumber() {
		return tasks.size();
	}

	public void printList() {
		for (int i = 1; i <= tasks.size(); i += 1) {
			System.out.println(i + ". " + tasks.get(i - 1).showTask());
		}
	}

	public Task findTask(int taskIndex) {
		return tasks.get(taskIndex - 1);
	}

	public void addTasks(Task task) {
		tasks.add(task);
	}

	public void addTodo(String description, boolean isCompleted) {
		tasks.add(new Todo(description, isCompleted));
		System.out.println(showTaskMessage(tasks.size() - 1));
	}

	public void addDeadline(String description, String by, boolean isCompleted) {
		tasks.add(new Deadline(description, by, isCompleted));
		System.out.println(showTaskMessage(tasks.size() - 1));
	}

	public void addEvent(String description, String from, String to, boolean isCompleted) {
		tasks.add(new Event(description, from, to, isCompleted));
		System.out.println(showTaskMessage(tasks.size() - 1));
	}

	public void deleteTask(int taskIndex) {
		String message = tasks.get(taskIndex - 1).showTask();
		tasks.remove(taskIndex - 1);
		System.out.println(message + "\n " + showTaskNumber());
	}

	public void markAsDone(int taskIndex) {
		tasks.get(taskIndex - 1).isCompleted = true;
	}

	public void markAsUndone(int taskIndex) {
		tasks.get(taskIndex - 1).isCompleted = false;
	}

	public String writeTaskList() {
		String tasksToBeAdded = "";
		for (Task task : tasks) {
			tasksToBeAdded = tasksToBeAdded + task.writeTask();
		}
		return tasksToBeAdded;
	}
}
