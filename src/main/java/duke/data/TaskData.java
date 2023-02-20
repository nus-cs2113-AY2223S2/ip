package duke.data;

import duke.filemanager.TaskLoader;
import duke.filemanager.TaskWriter;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Event;
import duke.exceptions.ListTooLarge;

import java.util.ArrayList;


public class TaskData {
    private ArrayList<Task> taskList;
    TaskWriter taskWriter = new TaskWriter();

    public TaskData() {
        this.taskList = new ArrayList<Task>();
        setData();
    }

    public void setData() {
        TaskLoader taskLoader = new TaskLoader();
        this.taskList = taskLoader.setClasses();
    }

    public ArrayList<Task> getReadableList() {
        return this.taskList;
    }


    public int size() {
        return taskList.size();
    }

    /**
     * Overloaded: Adds Deadline to the list to keep track
     *
     * @param task: user task to remember
     */

    public void add(Task task) {
        taskList.add(task);
        taskWriter.writeToJson(taskList);
    }

    /**
     * Overloaded: Adds Deadline to the list to keep track
     *
     * @param deadline: user Deadline to remember


    public void add(Deadline deadline) {
    taskList.add(deadline);
    taskWriter.writeToJson(taskList);
    }

    /**
     * Overloaded: Adds event to the list to keep track
     *
     * @param event: user Deadline to remember


    public void add(Event event){
    taskList.add(event);
    taskWriter.writeToJson(taskList);
    }
     */

    /**
     * Set the specified task at the given index to done
     *
     * @param taskIndex index in which the task is stored in the array
     */
    public Task markAsDone(int taskIndex) {
        taskList.get(taskIndex).setAsDone();
        taskWriter.writeToJson(taskList);
        return taskList.get(taskIndex);
    }

    /**
     * Set the specified task at the given index to undone
     *
     * @param taskIndex index in which the task is stored in the array
     */
    public Task markAsUndone(int taskIndex) {
        taskList.get(taskIndex).setAsUndone();
        taskWriter.writeToJson(taskList);
        return taskList.get(taskIndex);

    }

    /**
     * Deletes the specified index
     */
    public Task deleteTask(int taskIndex) {
        Task currentTask = taskList.get(taskIndex);
        taskList.remove(taskIndex);
        taskWriter.writeToJson(taskList);
        return currentTask;
    }


}
