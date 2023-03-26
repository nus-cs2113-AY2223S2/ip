package Duke.string;

import java.util.ArrayList;

import Duke.task.DukeTask;

/**
 * Represents a class that converts a task list to a string.
 */
public class TaskListToString {
    
    public static String taskListToString(ArrayList<DukeTask> taskList) {
        String textToWrite = "";
        for (DukeTask task : taskList) {
            textToWrite += TaskToString.taskToString(task);
        }
        return textToWrite;
    }

}
