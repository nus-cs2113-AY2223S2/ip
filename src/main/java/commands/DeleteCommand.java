package commands;

import enums.DialogueTypes;
import errors.TaskNumberOutOfRangeException;
import managers.OutputDialogueManager;
import managers.SaveManager;
import managers.TaskManager;
import tasks.Task;

import java.io.IOException;

import static tasks.Task.getItemCount;

public class DeleteCommand extends Command {
    private int idToDelete;
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
            display.printErrorDialogue(DialogueTypes.TASK_NUMBER_OUT_OF_RANGE);
            taskList.listAllItems(display);
        }

    }

    private static void printDeleteResults(OutputDialogueManager display, Task toBeDeleted) {
        display.printInteraction(DialogueTypes.DELETE_TASK);
        toBeDeleted.printTaskWithoutId();
        display.printInteraction(DialogueTypes.COUNT_OF_TASKS);
        System.out.println(getItemCount());
    }
}
