package duke.tasklist;

import duke.exceptions.InvalidInputIDException;
import duke.exceptions.NoTaskException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    private static final String MESSAGE_TASKS_MARKED = "Nice! I've marked this task as done:";
    private static final String MESSAGE_TASKS_UNMARKED = "OK, I've marked this task as not done yet:";
    private static final String MESSAGE_TASKS_AVAILABLE = "Here are the tasks in your list:";
    private static final String MESSAGE_TASKS_NONE = "There are no tasks available.";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(String json) {
        tasks = JsonParser.fromJson(json);
    }

    public void add(Task taskObj) {
        tasks.add(taskObj);
    }

    public int size() {
        return tasks.size();
    }

    public Task delete(int id) throws InvalidInputIDException {
        if (id < 1 || id > tasks.size()) {
            throw new InvalidInputIDException();
        }
        Task temp = tasks.get(id - 1);
        tasks.remove(id - 1);
        return temp;
    }

    public String setStatus(int id, boolean isCompleted) throws Exception {
        try {
            if (id >= tasks.size() || id < 0) {
                throw new IndexOutOfBoundsException();
            }
            tasks.get(id).setIsCompleted(isCompleted);
            String output = isCompleted
                            ? MESSAGE_TASKS_MARKED + "\n"
                            : MESSAGE_TASKS_UNMARKED + "\n";
            output += tasks.get(id).describe();
            return output;
        } catch (IndexOutOfBoundsException e) {
            throw (tasks.size() == 0)
                  ? new NoTaskException()
                  : new InvalidInputIDException();
        }
    }

    public String listAll() {
        return listAll(tasks);
    }

    public static String listAll(ArrayList<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append(tasks.size() == 0
                      ? MESSAGE_TASKS_NONE
                      : MESSAGE_TASKS_AVAILABLE + "\n");

        // adds tasks to output, if any
        // combine details of tasks into a single string
        for (int i = 0; i < tasks.size(); ++i) {
            output.append(i + 1)
                  .append(".") // number
                  .append(tasks.get(i).describe())
                  .append("\n");
        }
        return output.toString();
    }

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

    public String toJson() {
        return JsonParser.toJson(tasks);
    }
}
