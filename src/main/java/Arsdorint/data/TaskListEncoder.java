package Arsdorint.data;
import Arsdorint.task.Task;

import java.util.ArrayList;
import java.util.List;
/**
 * Represent the class that handles encode task from TaskList to file
 */

public class TaskListEncoder {
    private static final String SEPARATOR = " | ";

    /**
     * Encode the tasklist
     *
     * @param list tasklist to be represent in strings
     * @return ArrayList of String (each string is a task to be saved)
     */

    public static List<String> encodeList(TaskList list) {
        final List<String> encodedTasks = new ArrayList<>();
        list.data.stream().forEach(task -> encodedTasks.add(encodeTask(task)));
        return encodedTasks;
    }

    private static String encodeTask(Task task) {
        final StringBuilder encodedTaskBuilder = new StringBuilder();

        encodedTaskBuilder.append(task.getType());
        encodedTaskBuilder.append(SEPARATOR);
        encodedTaskBuilder.append(task.isDone() ? "true" : "false");
        encodedTaskBuilder.append(SEPARATOR);
        encodedTaskBuilder.append(task.description);
        encodedTaskBuilder.append(SEPARATOR);
        encodedTaskBuilder.append(task.isDateNull() ? task.getDate() : "");
        return encodedTaskBuilder.toString();
    }
}
