package duke.tasklist;

import duke.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import static duke.common.Messages.LIST_TASKS_MESSAGE;

public class TaskList {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static int numOfTask = 0;


    public TaskList(ArrayList<Task> tasksList){
        tasks = new ArrayList<>(tasksList);
        numOfTask = tasks.size();
    }

    public void printTasks(){
        System.out.println(LIST_TASKS_MESSAGE);
        for(int i = 1; i <= numOfTask; i+= 1){
            System.out.println(i + "." +"[" + tasks.get(i-1).getType() + "]" + "[" + tasks.get(i-1).getStatusIcon() + "] "+ tasks.get(i-1).getDescription()
                    + tasks.get(i-1).getDeadline() + tasks.get(i-1).getPeriod());
        }
        System.out.println("You have a total of " + numOfTask + " tasks.");
    }
    public int getNumOfTask(){
        return numOfTask;
    }

    public void addTask(Task task){
        tasks.add(task);
    }
    public void removeTask(int num){
        tasks.remove(num);
    }
    public Task getTask(int num){
        return tasks.get(num);
    }
    public ArrayList<Task> getTaskList(){
        return tasks;
    }
    public void updateTaskLists(int num, ArrayList<Task> taskList){
        numOfTask = num;
        tasks = taskList;
    }
}
