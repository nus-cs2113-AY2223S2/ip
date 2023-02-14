package duke.tasks;

import duke.exceptions.TaskDoneException;
import duke.exceptions.TaskException;
import duke.exceptions.TaskOutOfBoundsException;
import duke.exceptions.TaskUndoneException;

import java.util.ArrayList;

public class TaskList {

	private final String TASK_COUNTED_START =
			"There are ";
	private final String TASK_COUNTED_ENDED =
			" tasks in your list.";
	private final String TASK_ADDED =
			"Got it. I've added this task: ";
	private final String TASK_DELETED =
			"Got it. I've removed this task: ";
	private final String TASK_DONE =
			"Nice! I've marked this task as done: ";


	public ArrayList<Task> tasks = new ArrayList<>();

	public TaskList() {
	}

	public String showAcknowledgement(String fixedMessage, int taskIndex) {
		return fixedMessage + "\n " + tasks.get(taskIndex).showTask();
	}

	public String countTaskNumber() {
		return TASK_COUNTED_START + tasks.size() + TASK_COUNTED_ENDED;
	}

	public void printList() throws TaskException {
		if (tasks.size() == 0) {
			throw new TaskException();
		}

		for (int i = 1; i <= tasks.size(); i += 1) {
			System.out.println(i + ". " + tasks.get(i - 1).showTask());
		}

	}

	public Task findTask(int taskIndex) throws TaskException {
		if (tasks.size() == 0) {
			throw new TaskException();
		} else if (taskIndex > tasks.size()) {
			throw new TaskException();
		}

		return tasks.get(taskIndex-1);
	}

	public String addTodo(String description) {
		tasks.add(new Todo(description));
		return showAcknowledgement(TASK_ADDED, tasks.size() - 1);
	}

	public String addDeadline(String description, String by) {
		tasks.add(new Deadline(description, by));
		return showAcknowledgement(TASK_ADDED, tasks.size() - 1);
	}

	public String addEvent(String description, String from, String to) {
		tasks.add(new Event(description, from, to));
		return showAcknowledgement(TASK_ADDED, tasks.size() - 1);
	}

	public String deleteTask(int taskIndex) throws TaskOutOfBoundsException {
		if (taskIndex > tasks.size() || taskIndex <= 0) {
			throw new TaskOutOfBoundsException();
		}
		String message = tasks.get(taskIndex-1).showTask();
		tasks.remove(taskIndex-1);
		return TASK_DELETED + "\n " + message + "\n " + countTaskNumber();
	}

	public void markAsDone(int taskIndex) throws TaskException, TaskDoneException {
		if (taskIndex > tasks.size()) {
			throw new TaskException();
		} else if (tasks.get(taskIndex-1).isCompleted == true) {
			throw new TaskDoneException();
		}
		tasks.get(taskIndex-1).isCompleted = true;
	}

	public void markAsUndone(int taskIndex) throws TaskException, TaskUndoneException {
		if (taskIndex > tasks.size()) {
			throw new TaskException();
		} else if (tasks.get(taskIndex-1).isCompleted == true) {
			throw new TaskUndoneException();
		}
		tasks.get(taskIndex-1).isCompleted = true;
	}
}
