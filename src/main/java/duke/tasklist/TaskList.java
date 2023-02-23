package duke.tasklist;

import duke.exceptions.InvalidInputIDException;
import duke.exceptions.NoTaskException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A list used to store and access all tasks created.
 */
public class TaskList {
    private static final String MESSAGE_TASKS_MARKED = "Nice! I've marked this task as done:";
    private static final String MESSAGE_TASKS_UNMARKED = "OK, I've marked this task as not done yet:";
    private static final String MESSAGE_TASKS_AVAILABLE = "Here are the tasks in your list:";
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Initialise the object using JSON string.
     *
     * @param json String to be deserialized
     */
    public TaskList(String json) {
        tasks = JsonParser.fromJson(json);
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
     * Get the size of the list.
     *
     * @return Integer
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Delete a task from the list.
     *
     * @param id 1-based ID corresponding to the task
     * @return Copy of the task deleted
     * @throws InvalidInputIDException If the given ID is invalid
     */
    public Task delete(int id) throws InvalidInputIDException {
        if (id < 1 || id > tasks.size()) {
            throw new InvalidInputIDException();
        }
        Task temp = tasks.get(id - 1);
        tasks.remove(id - 1);
        return temp;
    }

    /**
     * Set the completion status of a task
     *
     * @param id          1-based ID corresponding to the task
     * @param isCompleted The completion status
     * @return String describing the action completed and task changed
     * @throws NoTaskException         If the list is empty
     * @throws InvalidInputIDException If the given ID is invalid
     */
    public String setStatus(int id, boolean isCompleted) throws NoTaskException, InvalidInputIDException {
        try {
            if (tasks.size() == 0) {
                throw new NoTaskException();
            }
            tasks.get(id).setIsCompleted(isCompleted);
            String output = isCompleted
                            ? MESSAGE_TASKS_MARKED + "\n"
                            : MESSAGE_TASKS_UNMARKED + "\n";
            output += tasks.get(id).describe();
            return output;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputIDException();
        }
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
                  .append(tasks.get(i).describe())
                  .append(System.lineSeparator());
        }

        return output.toString();
    }

    /**
     * Searches for the keyword specified by the user.
     *
     * @param keyword The string to be searched (Supports RegEx format, search is case-insensitive)
     * @return String containing matching tasks
     * @throws NoTaskException If there are no tasks in the list
     */
    public String find(String keyword) throws NoTaskException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        for (Task task : tasks) {
            Matcher matcher = pattern.matcher(task.describe());
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
     * Serializes the stored tasks into JSON format.
     *
     * @return String in JSON format
     */
    public String toJson() {
        return JsonParser.toJson(tasks);
    }
}
