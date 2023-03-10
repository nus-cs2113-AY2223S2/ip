package command;

import task.Task;
import task.TaskList;
import ui.Ui;
import utils.Storage;

/**
 *  Command for marking/unmarking a task.
 *  Format: mark/unmark [index]
 */
public class MarkCommand extends Command{
    protected String taskToMarkIndexString;
    protected int index;
    protected boolean isMarkAsDone;

    public MarkCommand(String taskToMarkIndexString, boolean isMarkAsDone){
        this.taskToMarkIndexString = taskToMarkIndexString;
        this.isMarkAsDone = isMarkAsDone;
    }

    /**
     * Execute the mark/unmark command. Mark/unmark the object according to the index.
     * and update the local file in the disk.
     * @param tasks The task list.
     * @param ui The ui module to show messages.
     * @param storage The reading and writing tool.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        /* Input cannot format into an Index */
        try{
            index = Integer.parseInt(taskToMarkIndexString);
        }catch (NumberFormatException nfe){
            ui.showTaskIndexFormatError();
            return;
        }

        /* Input Index out of range */
        index = index - 1;
        if(index < 0 || index >= tasks.size()){
            ui.showTaskIndexNotFoundError();
            return;
        }

        /* set mark or unmark status, and get feedback */
        Task taskToMark = tasks.get(index);
        taskToMark.setStatus(isMarkAsDone);

        storage.rewrite(tasks, ui);

        ui.showMarkUnmarkTaskDone(taskToMark, isMarkAsDone);

    }
    public boolean isExit(){
        return false;
    }
}
