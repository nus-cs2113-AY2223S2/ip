import java.util.ArrayList;
public class TaskList {
	ArrayList<Task> tasks = new ArrayList<>();
	public TaskList() {
	}
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	public void markAtIndex(Integer index) {
		tasks.get(index).isCompleted = true;
	}
	public void unmarkAtIndex(Integer index) {
		tasks.get(index).isCompleted = false;
	}
	public void addTodoToList(String description) throws DukeException {
		if(description.equals("")){
			throw new DukeException(new IllegalArgumentException());
		}
		tasks.add(new Todo(description));
	}
	public void addDeadlineToList(String description, String byDate) {
		tasks.add(new Deadline(description, byDate));
	}
	public void addEventToList(String description, String fromDate, String toDate) {
		tasks.add(new Event(description, fromDate, toDate));
	}
	public String deleteTaskAtIndex(Integer index){
		String nameOfToBeDeletedTask = tasks.get(index).name;
		tasks.remove((int)index);
		return nameOfToBeDeletedTask;
	}
}
