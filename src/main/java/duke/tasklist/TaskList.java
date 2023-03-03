package duke.tasklist;

import duke.tasklist.task.Deadline;
import duke.tasklist.task.Event;
import duke.tasklist.task.Task;
import duke.tasklist.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Initialises an instance of TaskList.
     * Stores a task array list into the instance of TaskList.
     *
     * @param tasks Task array list loaded from duke.txt
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Initialises an empty instance of TaskList.
     */
    public TaskList() {

    }

    /**
     * Adds a new task of deadline type into the task array list.
     *
     * @param taskName Name of the deadline task.
     * @param by End date of the deadline task.
     */
    public void addDeadlineTask(String taskName, String by) {
        tasks.add(new Deadline(taskName, by));
    }

    /**
     * Adds a new task of event type into the task array list.
     *
     * @param taskName Name of the event task.
     * @param from Start date/time of the event task.
     * @param to End date/time of the event task
     */
    public void addEventTask(String taskName, String from, String to) {
        tasks.add(new Event(taskName, from, to));
    }

    /**
     * Adds a new task if to do type into the task array list.
     *
     * @param taskName Name of the to do task.
     */
    public void addTodoTask(String taskName) {
        tasks.add(new Todo(taskName));
    }

    /**
     * Deletes an existing task from the task array list.
     *
     * @param taskNo Task no of the task array list.
     */
    public void deleteTask(int taskNo) {
        tasks.remove(tasks.get(taskNo));
    }

    /**
     * Marks an existing task from the task array list as complete.
     *
     * @param taskNo Task no of the task array list.
     */
    public void markTask(int taskNo) {
        tasks.get(taskNo).setCompleted();
    }

    /** Marks an existing task from the task array list as incomplete.
     *
     * @param taskNo Task no of the task array list.
     */
    public void unmarkTask(int taskNo) {
        tasks.get(taskNo).setIncomplete();
    }

    /**
     * Returns the task array list in the TaskList instance.
     *
     * @return Task Array List.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}