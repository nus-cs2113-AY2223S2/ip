package DukeManager.data;

import DukeManager.data.DukeErrors.DuplicateDataException;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import DukeManager.data.Tasks.Task;


public class TaskList {
	private static List<Task> allTasks = new ArrayList<>();

	public TaskList(ArrayList<Task> taskArrayList) {
		allTasks = taskArrayList;
	}
	public TaskList() {}


	/**
	 * Checks if the list contains an equivalent task as the given argument.
	 * The {@link Task#isSameTask} method is used for this comparison, which
	 * defines a weaker notion of equality.
	 */
	public boolean contains(Task toCheck) {
		for (Task task : allTasks) {
			if (task.isSameTask(toCheck)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds a task to the list.
	 *
	 * @throws DuplicateTaskException if duplicate task already exists.
	 */
	public void addTask(Task toAdd) throws DuplicateTaskException {
		if (contains(toAdd)) {
			throw new DuplicateTaskException();
		}
		allTasks.add(toAdd);
	}

	/**
	 * Removes the equivalent task from the list.
	 *
	 * @throws TaskNotFoundException if no such task could be found in the list.
	 */
	public static void removeTask(Task toRemove) throws TaskNotFoundException {
		final boolean taskFoundAndDeleted = allTasks.remove(toRemove);
		if (!taskFoundAndDeleted) {
			throw new TaskNotFoundException();
		}
	}


	/**
	 * Removes the equivalent task from the list.
	 */
	public static void markTask(Task task, boolean isDone) {
		task.setDone(isDone);
	}

	/**
	 * Returns an unmodifiable java List view with elements cast as immutable {@link Task}s.
	 * For use with other methods/libraries.
	 * Any changes to the internal list/elements are immediately visible in the returned list.
	 */
	public List<Task> getAllTasks() {
		return Collections.unmodifiableList(allTasks);
	}

    public int size() {
		return allTasks.size();
    }
	public Task getTask(int index) {
		return allTasks.get(index);
	}

	/**
	 * Signals that an operation would have violated the 'no duplicates' property of the list.
	 */
	public static class DuplicateTaskException extends DuplicateDataException {
		public DuplicateTaskException() {
			super("Operation would result in duplicate persons");
		}
	}

	/**
	 * Signals that an operation targeting a specified person in the list would fail because
	 * there is no such matching person in the list.
	 */
	public static class TaskNotFoundException extends Exception {}
}
