import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<Task>();


    public TaskList() {
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }
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
    public String deleteTaskAtIndex(Integer index){
        String nameOfToBeDeletedTask = tasks.get(index).name;
        tasks.remove((int)index);
        return nameOfToBeDeletedTask;
    }

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
