package duke.command;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The FindTask class finds
 * a listed task with a given keyword
 */
public class FindTask {
    /**
     * This method searches for tasks with a given keyword
     *
     * @param keyWord User's input keyword
     * @param storedUserTasks ArrayList containing listed Tasks
     */
    public static void searchForKeyword(String keyWord, ArrayList<Task> storedUserTasks) {

        ArrayList<Integer> taskIndexesWithKeyword = new ArrayList<>();
        for (int i = 0; i < storedUserTasks.size(); i++) {
            if(storedUserTasks.get(i).toString().contains(keyWord)){
                taskIndexesWithKeyword.add(i);
            }
        }
        Display.displayTasksWithKeyword(storedUserTasks, taskIndexesWithKeyword);
    }

}
