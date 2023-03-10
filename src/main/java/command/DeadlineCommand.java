package command;


import task.Deadline;
import task.TaskList;
import ui.Ui;
import utils.Storage;



/**
 *  Command for adding a deadline task.
 *  Format: deadline [description] /by [time]
 */
public class DeadlineCommand extends Command{
    protected String todoString;
    protected String deadlineString;



    public DeadlineCommand(String todoString, String deadlineString) {
        this.todoString = todoString;
        this.deadlineString = deadlineString;
    }

    /**
     * Execute the deadline command. Add a new deadline object to the task list,
     * and write it to the disk.
     * @param tasks The task list.
     * @param ui The ui module to show messages.
     * @param storage The reading and writing tool.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Deadline newDeadlineObject = new Deadline(todoString, deadlineString);
        tasks.add(newDeadlineObject);
        storage.addNewObjectToFile(newDeadlineObject, ui);

        ui.showDeadlineTask(newDeadlineObject, tasks);
    }

    public boolean isExit(){
        return false;
    }
}
