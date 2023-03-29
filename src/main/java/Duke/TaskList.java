package Duke;

import java.util.ArrayList;
import Duke.commands.Deadline;
import Duke.commands.Event;
import Duke.commands.Task;
import Duke.commands.Todo;
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public String getDescription(int index) {
        return tasks.get(index).toString();
    }
    public int getSize(){
        return tasks.size();
    }
    public void Todo(String description){
        tasks.add(new Todo(description));
    }

    public void Deadline(String description, String deadlineTime){
        tasks.add(new Deadline(description, deadlineTime));
    }

    public void Event(String description, String fromTime, String toTime){
        tasks.add(new Event(description, fromTime, toTime));
    }

    public void DeleteTheTask(int taskNum){
        tasks.remove(taskNum);
    }

    /**
     * The method collects all the tasks that contains the given keyword in the task list.
     *
     * @param keyword the keyword the user searches
     * @return the relevant list that consists the tasks containing the keyword searched by user
     */
    public ArrayList<Task> relevantTask(String keyword){
        ArrayList<Task> relevantList = new ArrayList<>();
        for(Task task : tasks){
            if(task.getDescription().contains(keyword)){
                relevantList.add(task);
            }
        }
        return relevantList;
    }

    /**
     * Mark the task at the given index as done.
     *
     * @param taskNum the index that the user wants to mark
     */
    public void MarkTheTask(int taskNum){
        tasks.get(taskNum).markAsDone();
    }

    /**
     * Unmark the task at the given index as not done.
     *
     * @param taskNum the index that the user wants to unmark
     */
    public void UnmarkTheTask(int taskNum){
        tasks.get(taskNum).unmarkAsDone();
    }

    public ArrayList<Task> List(){
        return tasks;
    }
}
