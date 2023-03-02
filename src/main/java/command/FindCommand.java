package command;

import task.Deadline;
import task.Task;
import task.TaskList;
import ui.Ui;
import utils.Storage;

import java.util.ArrayList;

public class FindCommand extends Command{
    protected String key;
    public FindCommand(String key){
        this.key = key;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ArrayList<Task> resultList = new ArrayList<Task>();
        ArrayList<Task> taskList = tasks.getTasks();
        for(Task task: taskList){
            if(task.getDescription().contains(key)){
                resultList.add(task);
            }
        }
        TaskList resultTasks = new TaskList(resultList);

        if(resultList.size()==0){
            ui.showFindEmpty();
            return;
        }
        ui.showFindNotEmpty();
        ui.showTaskList(resultTasks);
    }
}
