package duke.tasklist;

import duke.exceptions.CorruptSaveDataException;
import duke.exceptions.InvalidInputIDException;
import duke.exceptions.NoTaskException;
import duke.parser.json.JsonParser;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A list used to store and access all tasks created.
 */
public class TaskList {
    private static final String MESSAGE_TASKS_AVAILABLE = "Here are the tasks in your list:";
    private static final String MESSAGE_TASKS_MARKED = "Nice! I've marked this task as done:";
    private static final String MESSAGE_TASKS_UNMARKED = "OK, I've marked this task as not done yet:";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialise the object using JSON string.
     *
     * @param tasksJson String to be deserialized
     */
    public TaskList(String tasksJson) throws CorruptSaveDataException {
        tasks = JsonParser.fromJson(tasksJson);
    }

    /**
     * Add a task to the list.
     *
     * @param taskObj Task object to be added
     */
    public void add(Task taskObj) {
        tasks.add(taskObj);
    }

    /**
     * Delete a task from the list.
     *
     * @param taskId 1-based ID corresponding to the task
     * @return Copy of the task deleted
     * @throws InvalidInputIDException If the given ID is invalid
     */
    public Task delete(int taskId) throws InvalidInputIDException {
        boolean isInvalidID = taskId < 1 || taskId > tasks.size();
        if (isInvalidID) {
            throw new InvalidInputIDException();
        }
        Task deletedTask = tasks.get(taskId - 1);
        tasks.remove(taskId - 1);
        return deletedTask;
    }

    /**
     * Searches for the keyword specified by the user.
     *
     * @param userQuery The string to be searched (Supports RegEx format, search is case-insensitive)
     * @return String containing matching tasks
     * @throws NoTaskException If there are no tasks in the list
     */
    public String findTasks(String userQuery) throws NoTaskException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        Pattern pattern = Pattern.compile(userQuery, Pattern.CASE_INSENSITIVE);
        for (Task task : tasks) {
            Matcher matcher = pattern.matcher(task.describeTask());
            if (matcher.find()) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.size() == 0) {
            throw new NoTaskException();
        }

        return listAll(matchingTasks);
    }

    /**
     * Lists all the tasks available in the list with their corresponding id
     *
     * @return String containing the tasks
     * @throws NoTaskException If the list is empty
     */
    public String listAll() throws NoTaskException {
        return listAll(tasks);
    }

    /**
     * Lists all the tasks available in the list with their corresponding id
     *
     * @param tasks ArrayList containing the tasks
     * @return String containing the tasks
     * @throws NoTaskException If the list is empty
     */
    public static String listAll(ArrayList<Task> tasks) throws NoTaskException {
        if (tasks.size() == 0) {
            throw new NoTaskException();
        }

        // adds tasks to output, if any
        // combine details of tasks into a single string
        StringBuilder output = new StringBuilder(MESSAGE_TASKS_AVAILABLE);
        output.append(System.lineSeparator());

        for (int i = 0; i < tasks.size(); ++i) {
            output.append(i + 1)
                  .append(".") // number
                  .append(tasks.get(i).describeTask())
                  .append(System.lineSeparator());
        }

        return output.toString();
    }

    /**
     * Set the completion status of a task
     *
     * @param taskId          1-based ID corresponding to the task
     * @param isCompleted The completion status
     * @return String describing the action completed and task changed
     * @throws NoTaskException         If the list is empty
     * @throws InvalidInputIDException If the given ID is invalid
     */
    public String setStatus(int taskId, boolean isCompleted) throws NoTaskException, InvalidInputIDException {
        try {
            if (tasks.size() == 0) {
                throw new NoTaskException();
            }
            tasks.get(taskId).setIsCompleted(isCompleted);
            String output = isCompleted
                            ? MESSAGE_TASKS_MARKED + System.lineSeparator()
                            : MESSAGE_TASKS_UNMARKED + System.lineSeparator();
            output += tasks.get(taskId).describeTask();
            return output;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputIDException();
        }
    }

    /**
     * Get the size of the list.
     *
     * @return Integer
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Serializes the stored tasks into JSON format.
     *
     * @return String in JSON format
     */
    public String toJson() {
        return JsonParser.toJson(tasks);
    }
}
