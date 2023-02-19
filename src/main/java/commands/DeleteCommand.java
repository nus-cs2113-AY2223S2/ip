package commands;

import enums.DialogueTypes;
import enums.ErrorDialogueTypes;
import errors.TaskNumberOutOfRangeException;
import managers.OutputDialogueManager;
import managers.SaveManager;
import managers.TaskManager;
import tasks.Task;
import static tasks.Task.getItemCount;

/**
 * Represents a command that intends to delete an item from the list of Tasks.
 */
public class DeleteCommand extends Command {
    private int idToDelete;

    /**
     * Constructs a DeleteCommand Object with the index of the Task to be deleted.
     *
     * @param idToDelete A integer representing the index of the Task that is going to be deleted.
     */
    public DeleteCommand(int idToDelete) {
        this.idToDelete = idToDelete;

    }

    @Override
    public void execute(TaskManager taskList, SaveManager storage, OutputDialogueManager display) {
        try {
            Task toBeDeleted = taskList.deleteItem(this.idToDelete);
            printDeleteResults(display, toBeDeleted);
            super.saveAllTasks(taskList, storage, display);
        } catch (TaskNumberOutOfRangeException e) {
            display.printErrorDialogue(ErrorDialogueTypes.TASK_NUMBER_OUT_OF_RANGE);
            taskList.listAllItems(display);
        }

    }

    private void printDeleteResults(OutputDialogueManager display, Task toBeDeleted) {
        display.printInteraction(DialogueTypes.DELETE_TASK);
        toBeDeleted.printTaskWithoutId();
        display.printInteraction(DialogueTypes.COUNT_OF_TASKS);
        System.out.println(getItemCount());
    }
}
