package util;

import errors.MarkTaskError;
import errors.ErrorMessages;
import tasks.Task;

import java.util.ArrayList;

public class Marker extends ErrorMessages{
    private static final String BLANK = " ";
    private static final String MARK_TASK = "mark";
    private static final String UNMARK_TASK = "unmark";

    //LOAD_FROM_SAVE_DATA is what controls whether data will be printed as an output or not
    public void handleMarkUnmarkAction(ArrayList<Task> taskList, String input, boolean loadFromSaveData) {
        String[] markActions = (input.split(BLANK, 2));
        try {
            if (markActions.length != 2){
                throw new MarkTaskError(provideNoNumberText());
            }
            int indexToMark = Integer.parseInt(markActions[1]);
            if (markActions[0].equals(MARK_TASK)) {
                markTaskDone(taskList, indexToMark, loadFromSaveData);
            } else if (markActions[0].equals(UNMARK_TASK)) {
                unmarkTaskUndone(taskList, indexToMark, loadFromSaveData);
            }
        }
        catch (MarkTaskError e) {
            if (loadFromSaveData){
                System.out.println("A specific line in data is corrupted, not adding corrupted data");
            }
            else{
                System.out.println(e.getMessage());
            }
        }
        catch (NumberFormatException e){
            if (loadFromSaveData){
                System.out.println("A specific line in data is corrupted, not adding corrupted data");
            }
            else{
                System.out.println(provideStringAsNumber());
            }
        }
    }

    private void markTaskDone(ArrayList<Task> taskList, int indexToMark, boolean hideOutput) throws MarkTaskError{
        OutputUI outputUI = new OutputUI();
        if (verifyMarkAction(taskList, indexToMark)){
            markTaskDone(taskList.get(indexToMark - 1));
            if (!hideOutput){
                outputUI.markTaskMessage(taskList.get(indexToMark - 1));
            }
        }
    }

    private boolean verifyMarkAction(ArrayList<Task> taskList, int indexToMark) throws MarkTaskError{
        if (taskList.size() == 0){
            throw new MarkTaskError(provideEmptyListText());
        }
        else if (indexToMark > taskList.size()){
            throw new MarkTaskError((provideExceedListLengthText(taskList.size())));
        }else if (indexToMark <= 0){
            throw new MarkTaskError(provideInvalidNumberText());
        }
        return true;
    }

    private void unmarkTaskUndone(ArrayList<Task> taskList, int indexToMark, boolean hideOutput) throws MarkTaskError{
        OutputUI outputUI = new OutputUI();
        verifyMarkAction(taskList, indexToMark);
        unmarkTaskUndone(taskList.get(indexToMark - 1));
        if (!hideOutput){
            outputUI.unmarkTaskMessage(taskList.get(indexToMark - 1));
        }
    }
    public void markTaskDone(Task task){
        task.done = true;
    }
    public void unmarkTaskUndone(Task task){
        task.done = false;
    }
}
