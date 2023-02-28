import java.util.ArrayList;

/**
 * contains and performs operations on the task list.
 * Operations include adding, deleting and marking tasks etc.
 */
public class TaskList {
    ArrayList<Task> tasks = new ArrayList<Task>();


    public TaskList() {
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Add [T] tasks to list
     * @param description name of the task
     * @throws DukeException if description is empty
     */
    public void addTodoToList(String description) throws DukeException {
        if(description==""){
            throw new DukeException(new IllegalArgumentException());
        }
        tasks.add(new Todo(description));
    }

    public void markTaskAtIndex(Integer index) {
        tasks.get(index).isDone = true;
    }
    public void unmarkTaskAtIndex(Integer index) {

        tasks.get(index).isDone = false;
    }

    public void addDeadlineToList(String description, String byDate) {
        tasks.add(new Deadline(description, byDate));
    }

    public void addEventToList(String description, String fromDate, String toDate) {
        tasks.add(new Event(description, fromDate, toDate));
    }

    /**
     * Delete task from list at specific index.
     * @param index index of task to be deleted from the list.
     * @return name of deleted task.
     */
    public String deleteTaskAtIndex(Integer index){
        String nameOfToBeDeletedTask = tasks.get(index).name;
        tasks.remove((int)index);
        return nameOfToBeDeletedTask;
    }

    /**
     * Find tasks from list that contains a substring <code>description</code>
     * @param description substring requested by user
     * @return list of tasks that contains <code>description</code>
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
