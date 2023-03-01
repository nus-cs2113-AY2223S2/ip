package util;

import java.util.ArrayList;

import errors.DeleteTaskError;
import errors.ErrorMessages;
import tasks.Task;

/**
 * Handles Deleting tasks from a list
 * */
public class TaskDeleter extends ErrorMessages {
    private static final String BLANK = " ";

    /**
    * Takes in input for a delete command and deletes accordingly
    * Handles index out of taskList bounds or incorrect input (e.g. input as String)
    * */
    public void handleDeleteAction(ArrayList<Task> listOfTasks, String input) {
        String[] deleteActions = (input.split(BLANK, 2));
        try {
            if (listOfTasks.size() <= 0) {
                throw new DeleteTaskError(provideEmptyListText());
            }
            if (deleteActions.length != 2) {
                throw new DeleteTaskError(provideNoNumberText());
            }
            int indexToDelete = Integer.parseInt(deleteActions[1]);
            OutputUI outputUI = new OutputUI();
            outputUI.printDeleteTaskMessage(listOfTasks.get(indexToDelete - 1), listOfTasks.size() - 1);
            deleteTask(listOfTasks, indexToDelete);
        } catch (DeleteTaskError e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(provideStringAsNumber());
        }
    }


    private void deleteTask(ArrayList<Task> listOfTasks, int indexToDelete) throws DeleteTaskError {
        if (verifyMarkAction(listOfTasks, indexToDelete)) {
            listOfTasks.remove(indexToDelete - 1);
        }
    }

    private boolean verifyMarkAction(ArrayList<Task> taskList, int indexToDelete) throws DeleteTaskError {
        if (taskList.size() == 0) {
            throw new DeleteTaskError(provideEmptyListText());
        } else if (indexToDelete > taskList.size()) {
            throw new DeleteTaskError((provideExceedListLengthText(taskList.size())));
        } else if (indexToDelete <= 0) {
            throw new DeleteTaskError(provideInvalidNumberText());
        }
        return true;
    }
}
