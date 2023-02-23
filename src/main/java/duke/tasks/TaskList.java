package duke.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import duke.exceptions.InvalidInputIDException;
import duke.exceptions.NoTaskException;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();
    private static final String MESSAGE_TASKS_MARKED = "Nice! I've marked this task as done:";
    private static final String MESSAGE_TASKS_UNMARKED = "OK, I've marked this task as not done yet:";
    private static final String MESSAGE_TASKS_AVAILABLE = "Here are the tasks in your list:";
    private static final String TASK_TODO = "TODO";
    private static final String TASK_DEADLINE = "DEADLINE";
    private static final String TASK_EVENT = "EVENT";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(String json) {
        this.tasks = fromJson(json);
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

    public String listAll() throws NoTaskException {
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

    public ArrayList<Task> fromJson(String json) {
        Scanner scanner = new Scanner(json);
        ArrayList<Task> savedTasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String task = scanner.nextLine();
            savedTasks.add(deserializeJSON(task));
        }
        return savedTasks;
    }

    public String toJson() {
        StringBuilder saveData = new StringBuilder();
        for (Task t : tasks) {
            String json = gson.toJson(t);
            saveData.append(json);
            saveData.append(System.lineSeparator());
        }
        return saveData.toString();
    }

    private static Task deserializeJSON(String json) {
        JsonObject j = gson.fromJson(json, JsonObject.class);
        Task t;
        String taskType = j.get("type").getAsString();
        switch (taskType) {
        case TASK_TODO:
            t = gson.fromJson(j, ToDo.class);
            break;
        case TASK_EVENT:
            t = gson.fromJson(j, Event.class);
            break;
        case TASK_DEADLINE:
            t = gson.fromJson(j, Deadline.class);
            break;
        default:
            t = gson.fromJson(j, Task.class);
        }
        return t;
    }
}
