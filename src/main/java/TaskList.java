/**
 TaskList is a class that handles the manipulation of tasks in an ArrayList.
 It contains methods to add, delete, mark as done, and find tasks.
 */
import java.util.ArrayList;
public class TaskList {
	ArrayList<Task> tasks = new ArrayList<>();
	public TaskList() {
	}
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	/**
	 Marks the task at the given index as completed.
	 @param index The index of the task to be marked.
	 */
	public void markAtIndex(Integer index) {
		tasks.get(index).isCompleted = true;
	}
	/**
	 Marks the task at the given index as incomplete.
	 @param index The index of the task to be unmarked.
	 */
	public void unmarkAtIndex(Integer index) {
		tasks.get(index).isCompleted = false;
	}
	/**
	 Adds a Todo task to the list.
	 @param description The description of the Todo task.
	 @throws DukeException If description is an empty string.
	 */
	public void addTodoToList(String description) throws DukeException {
		if(description.equals("")){
			throw new DukeException(new IllegalArgumentException());
		}
		tasks.add(new Todo(description));
	}
	/**
	 Adds a Deadline task to the list.
	 @param description The description of the Deadline task.
	 @param byDate The date and time by which the Deadline task should be completed.
	 */
	public void addDeadlineToList(String description, String byDate) {
		tasks.add(new Deadline(description, byDate));
	}
	/**
	 Adds an Event task to the list.
	 @param description The description of the Event task.
	 @param fromDate The date and time from which the Event task starts.
	 @param toDate The date and time at which the Event task ends.
	 */
	public void addEventToList(String description, String fromDate, String toDate) {
		tasks.add(new Event(description, fromDate, toDate));
	}
	/**
	 Deletes the task at the given index from the list.
	 @param index The index of the task to be deleted.
	 @return The name of the deleted task.
	 */
	public String deleteTaskAtIndex(Integer index){
		String nameOfToBeDeletedTask = tasks.get(index).name;
		tasks.remove((int)index);
		return nameOfToBeDeletedTask;
	}
	/**
	 Finds tasks in the list that match the given description.
	 @param description The description to search for in the tasks.
	 @return An ArrayList of tasks that match the description.
	 */
	public ArrayList<Task> findTasks(String description){
		ArrayList<Task> matchedTasks = new ArrayList<>();
		for(Task s:tasks){
			if (s.name.contains(description)){
				matchedTasks.add(s);
			}
		}
		return matchedTasks;
	}
}

