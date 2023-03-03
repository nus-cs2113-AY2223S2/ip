package duke.tasklist;

import duke.tasklist.task.Deadline;
import duke.tasklist.task.Event;
import duke.tasklist.task.Task;
import duke.tasklist.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {

    }

    public void addDeadlineTask(String taskName, String by) {
        tasks.add(new Deadline(taskName, by));
    }

    public void addEventTask(String taskName, String from, String to) {
        tasks.add(new Event(taskName, from, to));
    }

    public void addTodoTask(String taskName) {
        tasks.add(new Todo(taskName));
    }

    public void deleteTask(int taskNo) {
        tasks.remove(tasks.get(taskNo));
    }

    public void markTask(int taskNo) {
        tasks.get(taskNo).setCompleted();
    }

    public void unmarkTask(int taskNo) {
        tasks.get(taskNo).setIncomplete();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}