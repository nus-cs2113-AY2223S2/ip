package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the task list. Contains the date of the tasks
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    /**
     * Constructs a task list with the given data
     *
     * @param taskList an array list containing the data to be added into task list
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the number of tasks in
     *
     * @return the number of tasks in task list
     */
    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Adds a task into task list
     *
     * @param toAdd task to be added into task list
     */
    public void addTask(Task toAdd) {
        taskList.add(toAdd);
    }

    /**
     * Deletes a task in the task list
     *
     * @param taskNumber task number indicating the task in the task list
     */
    public void deleteTask(int taskNumber) {
        taskList.remove(taskList.get(taskNumber));
    }

    /**
     * Generates the task information of a task in the task list to be shown to user
     *
     * @param taskNumber task number indicating the task in the task list
     * @return the string containing the full information of the task
     */
    public String getTaskFullDetails(int taskNumber) {
        return taskList.get(taskNumber).getFullTaskDetail();
    }

    /**
     * Marks the task in the task list to be done
     *
     * @param taskNumber task number indicating the task in the task list
     */
    public void markTaskDone(int taskNumber) {
        taskList.get(taskNumber).markAsDone();
    }

    /**
     * Marks the task in the task list to be not done
     *
     * @param taskNumber task number indicating the task in the task list
     */
    public void markTaskNotDone(int taskNumber) {
        taskList.get(taskNumber).markAsNotDone();
    }

    /**
     * Generates the task information of a task in the task list to be stored
     *
     * @return string containing the encoded task information
     */
    public String getTaskEncoding(int taskNumber) {
        return taskList.get(taskNumber).getEncodedData();
    }

    /**
     * Gets the tasks in the task list that contains the keyword in the task name
     *
     * @param keyword sequence of characters to be filtered
     * @return task list with tasks that contains the keyword in the task name
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList(new ArrayList<>());
        for (Task t : taskList) {
            if (t.taskName.contains(keyword)) {
                matchingTasks.addTask(t);
            }
        }
        return matchingTasks;
    }

    /**
     * Gets the tasks in the task list that occurs on a date
     *
     * @param date date of tasks to be filtered
     * @return task list containing the tasks tagged with the date given
     */
    public TaskList getDueTasks(LocalDate date) {
        TaskList dueTasks = new TaskList(new ArrayList<>());
        for (Task task : taskList) {
            if (task instanceof Deadline && ((Deadline) task).isDateOnDeadline(date)) {
                dueTasks.addTask(task);
            }
            if (task instanceof Event && (((Event) task).isDateBetweenEvent(date)
                    || ((Event) task).isDateOnStart(date) || ((Event) task).isDateOnEnd(date))) {
                dueTasks.addTask(task);
            }
        }
        return dueTasks;
    }
}
