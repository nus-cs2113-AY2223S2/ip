package DukeManager.data;

import DukeManager.data.DukeErrors.DuplicateDataException;
import DukeManager.data.Tasks.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TaskList {
	private final List<Task> internalList = new ArrayList<>();
	public TaskList() {};


	/**
	 * Checks if the list contains an equivalent person as the given argument.
	 * The {@link Task#isSameTask} method is used for this comparison, which
	 * defines a weaker notion of equality.
	 */
	public boolean contains(Task toCheck) {
		for (Task task : internalList) {
			if (task.isSameTask(toCheck)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Adds a task to the list.
	 *
	 * @throws DuplicateTaskException if the task to add is a duplicate of an existing task in the list.
	 *     The @link{Task#isSameTask} method is used for this comparison.
	 */
	public void addTask(Task toAdd) throws DuplicateTaskException {
		if (contains(toAdd)) {
			throw new DuplicateTaskException();
		}
		internalList.add(toAdd);
	}

	/**
	 * Removes the equivalent task from the list.
	 *
	 * @throws TaskNotFoundException if no such task could be found in the list.
	 */
	public void removeTask(Task toRemove) throws TaskNotFoundException {
		final boolean TaskFoundAndDeleted = internalList.remove(toRemove);
		if (!TaskFoundAndDeleted) {
			throw new TaskNotFoundException();
		}
	}

	/**
	 * Signals that an operation would have violated the 'no duplicates' property of the list.
	 */
	public static class DuplicateTaskException extends DuplicateDataException {
		protected DuplicateTaskException() {
			super("\t  Operation would result in duplicate tasks");
		}
	}

	/**
	 * Signals that an operation targeting a specified task in the list would fail because
	 * there is no such matching task in the list.
	 */
	public static class TaskNotFoundException extends Exception {}
}
