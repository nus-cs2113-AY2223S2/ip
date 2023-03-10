package command;

import task.Deadline;
import task.Task;
import task.TaskList;
import ui.Ui;
import utils.Storage;

import java.util.ArrayList;

/**
 *  Command for finding a task in the tasklist.
 *  Format: find [key]
 */
public class FindCommand extends Command{
    protected String key;
    public FindCommand(String key){
        this.key = key;
    }

    /**
     * Execute the find command. Find the input keyword in the task list,
     * and return a list of task objects containing the keyword.
     * @param tasks The task list.
     * @param ui The ui module to show messages.
     * @param storage The reading and writing tool.
     */
    @Override
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
