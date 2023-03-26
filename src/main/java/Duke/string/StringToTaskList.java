package Duke.string;

import static Duke.string.Strings.LINE_SEPARATOR;

import java.util.ArrayList;

import Duke.DukeException;
import Duke.task.DukeTask;

public class StringToTaskList {

    /**
     * Converts a string to a list of tasks.
     *
     * @param savedText The string to be converted.
     * @return The list of tasks.
     * @throws DukeException If the string is not in the correct format.
     */
    public static ArrayList<DukeTask> stringToTaskList(String savedText) throws DukeException {
        String[] savedTasks = savedText.split(LINE_SEPARATOR);
        ArrayList<DukeTask> tasks = new ArrayList<>();
        for (String savedTask : savedTasks) {
            if (!savedTask.equals("")) {
                tasks.add(StringToTask.stringToTask(savedTask));
            }
        }
        return tasks;
    }
    
}
