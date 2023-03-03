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

    public ArrayList<Task> relevantTask(String keyword){
        ArrayList<Task> relevantList = new ArrayList<>();
        for(Task task : tasks){
            if(task.getDescription().contains(keyword)){
                relevantList.add(task);
            }
        }
        return relevantList;
    }

    public void MarkTheTask(int taskNum){
        tasks.get(taskNum).markAsDone();
    }

    public void UnmarkTheTask(int taskNum){
        tasks.get(taskNum).unmarkAsDone();
    }

    public ArrayList<Task> List(){
        return tasks;
    }
}
