package duke.command;

import duke.Parser;
import duke.task.Task;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * The FindTask class finds
 * a listed task with a given keyword
 */
public class FindTask {
    /**
     * This method searches for tasks with a given keyword
     *
     * @param userCommand User's command
     * @param storedUserTasks ArrayList containing listed Tasks
     * @throws ParseException A ParseException has occurred
     */
    public static void searchForKeyword(String userCommand, ArrayList<Task> storedUserTasks) throws ParseException {
        String keyWord = Parser.extractInfo(userCommand, "keyword", "none");
        ArrayList<Integer> taskIndexesWithKeyword = new ArrayList<>();
        for (int i = 0; i < storedUserTasks.size(); i++) {
            if(storedUserTasks.get(i).toString().contains(keyWord)){
                taskIndexesWithKeyword.add(i);
            }
        }
        Display.displayTasksWithKeyword(storedUserTasks, taskIndexesWithKeyword);
    }

}
