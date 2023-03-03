package util;

import java.util.ArrayList;

import errors.DeleteTaskError;
import errors.ErrorMessages;
import tasks.Task;

/**
 * Handles Deleting tasks from a list
 */
public class TaskDeleter extends ErrorMessages {
    private static final String BLANK = " ";
    private static final Integer CORRECT_INPUT_FORMAT = 2;
    private static final Integer ZERO = 0;
    private static final Integer MANAGE_ZERO_INDEXING = 1;

    /**
     * Takes in input for a delete command and deletes accordingly
     * Handles index out of taskList bounds or incorrect input (e.g. input as String)
     * @param  listOfTasks contains the list of tasks to delete a task from
     * @param input Contains the index for deletion. Throws error otherwise
     */
    public void attemptToDeleteTask(ArrayList<Task> listOfTasks, String input) {
        String[] deleteActions = (input.split(BLANK, CORRECT_INPUT_FORMAT));
        try {
            if (listOfTasks.size() <= ZERO) {
                throw new DeleteTaskError(errorEmptyListText());
            }
            if (deleteActions.length != CORRECT_INPUT_FORMAT) {
                throw new DeleteTaskError(errorNoNumberText());
            }
            int indexToDelete = Integer.parseInt(deleteActions[1]);
            if (indexToDelete > listOfTasks.size()) {
                throw new DeleteTaskError((errorExceedListLengthText(listOfTasks.size())));
            } else if (indexToDelete <= ZERO) {
                throw new DeleteTaskError(errorInvalidNumberText());
            }
            OutputUI outputUI = new OutputUI();
            outputUI.printDeleteTaskMessage(listOfTasks.get(indexToDelete - MANAGE_ZERO_INDEXING),
                    listOfTasks.size() - MANAGE_ZERO_INDEXING);
            deleteTask(listOfTasks, indexToDelete);
        } catch (DeleteTaskError e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(errorStringAsNumber());
        }
    }


    private void deleteTask(ArrayList<Task> listOfTasks, int indexToDelete) throws DeleteTaskError {
        if (ensureIndexWithinTaskSize(listOfTasks, indexToDelete)) {
            listOfTasks.remove(indexToDelete - MANAGE_ZERO_INDEXING);
        }
    }


    /**
     * This function checks whether the index provided is within the size of the list of tasks
     * @param taskList Delete a task from this list. Only bringing the tasklist in for it's size here
     * @param indexToDelete Connotates which task to delete
     * @return Returns true if the mark index is within the size of the tasklist
     * @throws DeleteTaskError
     */
    private boolean ensureIndexWithinTaskSize(ArrayList<Task> taskList, int indexToDelete) throws DeleteTaskError {
        if (taskList.size() == ZERO) {
            throw new DeleteTaskError(errorEmptyListText());
        } else if (indexToDelete > taskList.size()) {
            throw new DeleteTaskError((errorExceedListLengthText(taskList.size())));
        } else if (indexToDelete <= ZERO) {
            throw new DeleteTaskError(errorInvalidNumberText());
        }
        return true;
    }
}
