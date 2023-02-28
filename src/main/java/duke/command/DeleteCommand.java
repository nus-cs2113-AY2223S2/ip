package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;



public class DeleteCommand extends Command {

    private int taskNumber;
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @ Override
    public void execute(TaskList tasks, Storage storage, UI ui) {
        try {
            Task task = tasks.getTask(taskNumber);
            ui.printDeleteTask();
            ui.printTask(task);
            tasks.removeTask(taskNumber);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("The given task number does not exist. ):");
        } catch (NumberFormatException e) {
            System.out.println("The task index must be numeric.");
        }
    }
}
