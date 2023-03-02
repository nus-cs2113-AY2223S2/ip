package util;

import java.util.ArrayList;

import errors.ErrorMessages;
import errors.MarkTaskError;
import tasks.Task;


/**
 * Marker handles all marking and unmarking tasks
 */
public class Marker extends ErrorMessages {
    private static final String BLANK = " ";
    private static final String MARK_TASK = "mark";
    private static final String UNMARK_TASK = "unmark";
    private static final boolean MARK_TASK_AS_DONE = true;
    private static final boolean MARK_TASK_AS_NOT_DONE = false;

    /**
     * @param taskList This is the list to mark/unmark a task from
     * @param input This is the input that could possibly contain the index to mark/unmark
     * @param loadFromSaveData This input handles whether to print out an output or not
     *                         If it is loaded from save data, we would not want to flood the
     *                         screen with a whole list of inputs, hence we mute it upon starting Duke
     *                         Otherwise we print output as per normal
     */
    public void markOrUnamrkTask(ArrayList<Task> taskList, String input, boolean loadFromSaveData) {
        String[] markActions = (input.split(BLANK, 2));
        try {
            if (markActions.length != 2) {
                throw new MarkTaskError(errorNoNumberText());
            }
            int indexToMark = Integer.parseInt(markActions[1]);
            if (markActions[0].equals(MARK_TASK)) {
                markTaskDoneAndOutput(taskList, indexToMark, loadFromSaveData);
            } else if (markActions[0].equals(UNMARK_TASK)) {
                unmarkTaskUndoneAndPrint(taskList, indexToMark, loadFromSaveData);
            }
        } catch (MarkTaskError e) {
            if (loadFromSaveData) {
                System.out.println(errorCorruptDataText());
            } else {
                System.out.println(e.getMessage());
            }
        } catch (NumberFormatException e) {
            if (loadFromSaveData) {
                System.out.println(errorCorruptDataText());
            } else {
                System.out.println(errorStringAsNumber());
            }
        }
    }

    private void markTaskDoneAndOutput(ArrayList<Task> taskList, int indexToMark, boolean hideOutput)
            throws MarkTaskError {
        OutputUI outputUI = new OutputUI();
        if (verifyMarkAction(taskList, indexToMark)) {
            markTaskDone(taskList.get(indexToMark - 1));
            if (!hideOutput) {
                outputUI.markTaskMessage(taskList.get(indexToMark - 1));
            }
        }
    }
    public void markTaskDone(Task task) {
        task.markThisTask(MARK_TASK_AS_DONE);
    }

    private boolean verifyMarkAction(ArrayList<Task> taskList, int indexToMark) throws MarkTaskError {
        if (taskList.size() == 0) {
            throw new MarkTaskError(errorEmptyListText());
        } else if (indexToMark > taskList.size()) {
            throw new MarkTaskError((errorExceedListLengthText(taskList.size())));
        } else if (indexToMark <= 0) {
            throw new MarkTaskError(errorInvalidNumberText());
        }
        return true;
    }

    private void unmarkTaskUndoneAndPrint(ArrayList<Task> taskList, int indexToMark, boolean hideOutput)
            throws MarkTaskError {
        OutputUI outputUI = new OutputUI();
        verifyMarkAction(taskList, indexToMark);
        unmarkTaskUndone(taskList.get(indexToMark - 1));
        if (!hideOutput) {
            outputUI.unmarkTaskMessage(taskList.get(indexToMark - 1));
        }
    }
    public void unmarkTaskUndone(Task task) {
        task.markThisTask(MARK_TASK_AS_NOT_DONE);
    }



}
