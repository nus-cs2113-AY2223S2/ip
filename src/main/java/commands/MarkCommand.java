package commands;

import enums.DialogueTypes;
import enums.ErrorDialogueTypes;
import errors.TaskNumberOutOfRangeException;
import managers.OutputDialogueManager;
import managers.SaveManager;
import managers.TaskManager;
import tasks.Task;

/**
 * Represents a command to mark or unmark a specific Task as done.
 */
public class MarkCommand extends Command {
    private boolean isDone;
    private int indexToMark;

    /**
     * Constructs a MarkCommand, specifying the state to mark and which task to mark.
     *
     * @param isDone A boolean function to mark the state of the specified Task to.
     * @param indexToMark An integer representing the position of the Task to be marked in
     *                    a list of Tasks starting from 1.
     */
    public MarkCommand(boolean isDone, int indexToMark) {
        this.isDone = isDone;
        this.indexToMark = indexToMark;
    }

    @Override
    public void execute(TaskManager taskList, SaveManager storage, OutputDialogueManager display) {
        try {
            Task markedTask = markTask(taskList, display);
            printMarkedTaskWithIndex(markedTask);
            super.saveAllTasks(taskList, storage, display);
        } catch (TaskNumberOutOfRangeException e) {
            display.printErrorDialogue(ErrorDialogueTypes.TASK_NUMBER_OUT_OF_RANGE);
            taskList.listAllItems(display);
        }
    }

    private void printMarkedTaskWithIndex(Task markedTask) {
        System.out.print(indexToMark + 1);
        markedTask.printTask();
    }

    private Task markTask(TaskManager taskList, OutputDialogueManager display) throws TaskNumberOutOfRangeException {
        Task markedTask;
        if (isDone) {
            markedTask = taskList.changeStateOfTask(indexToMark, true);
            display.printInteraction(DialogueTypes.MARK_TASK);
        } else {
            markedTask = taskList.changeStateOfTask(indexToMark, false);
            display.printInteraction(DialogueTypes.UNMARK_TASK);
        }
        return markedTask;
    }
}
