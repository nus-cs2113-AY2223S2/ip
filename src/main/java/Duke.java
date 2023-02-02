import java.util.ArrayList;
import java.util.List;


public class Duke {
    private List<Task> taskList = new ArrayList();
    public void addTask(String taskName){
        Task t = new Task(taskName);
        taskList.add(t);
        System.out.printf(String.format("added: %s\n", taskName));
    }

    public void changeTaskState(boolean doneState, Integer index){
        index --;
        if (doneState){
            taskList.get(index).markAsDone();
        }
        else{
            taskList.get(index).markAsUndone();
        }
    }

    public void list(){
        for (int i = 0; i < taskList.size(); i ++){
            System.out.printf(String.format("%d.%s\n", i + 1, taskList.get(i).toString()));
        }
    }
}
