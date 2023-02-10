package util;

import errors.MarkTaskError;
import errors.ErrorMessages;
import tasks.Task;

import java.util.ArrayList;

public class Marker extends ErrorMessages{
    private static final String BLANK = " ";
    private static final String MARK_TASK = "mark";
    private static final String UNMARK_TASK = "unmark";
    public void handleMarkUnmarkAction(ArrayList<Task> taskList, String input) {
        String[] markActions = (input.split(BLANK, 2));
        try {
            if (markActions.length != 2){
                throw new MarkTaskError(provideMarkNoNumberText());
            }
            int indexToMark = Integer.parseInt(markActions[1]);
            if (markActions[0].equals(MARK_TASK)) {
                markTaskDone(taskList, indexToMark);
            } else if (markActions[0].equals(UNMARK_TASK)) {
                unmarkTaskUndone(taskList, indexToMark);
            }
        }
        catch (MarkTaskError e) {
            System.out.println(e.getMessage());
        }
        catch (NumberFormatException e){
            System.out.println(provideMarkStringAsNumber());
        }
    }

    private void markTaskDone(ArrayList<Task> taskList, int indexToMark) throws MarkTaskError{
        OutputUI outputUI = new OutputUI();
        if (verifyMarkAction(taskList, indexToMark)){
            markTaskDone(taskList.get(indexToMark - 1));
            outputUI.markTaskMessage(taskList.get(indexToMark - 1));
        }
    }

    private boolean verifyMarkAction(ArrayList<Task> taskList, int indexToMark) throws MarkTaskError{
        if (taskList.size() == 0){
            throw new MarkTaskError(provideMarkEmptyListText());
        }
        else if (indexToMark > taskList.size()){
            throw new MarkTaskError((provideMarkExceedListLengthText(taskList.size())));
        }else if (indexToMark <= 0){
            throw new MarkTaskError(provideMarkInvalidNumberText());
        }
        return true;
    }

    private void unmarkTaskUndone(ArrayList<Task> taskList, int indexToMark) throws MarkTaskError{
        OutputUI outputUI = new OutputUI();
        verifyMarkAction(taskList, indexToMark);
        unmarkTaskUndone(taskList.get(indexToMark - 1));
        outputUI.unmarkTaskMessage(taskList.get(indexToMark - 1));
    }
    public void markTaskDone(Task task){
        System.out.println("I am marking the following task as done: " + task.description);
        task.done = true;
    }
    public void unmarkTaskUndone(Task task){
        System.out.println("I am marking the following task as undone: " + task.description);
        task.done = false;
    }
}
