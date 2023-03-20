package elzi;
import java.io.IOException;
import java.util.ArrayList;

import elzi.task.Task;



/**
 * @author : Steven A. O. Waskito
 * Class TaskList stores the Task in ArrayList
 * Has methods to add, delete, mark, getSize, getIndex, list, and search
 **/
public class TaskList {
    protected ArrayList<Task> taskList;
    /**
     * Constructor for TaskList
     * @param taskList to clone from another taskList (storage.txt)
     * @param capacity new TaskList with capacity
     */
    public TaskList(TaskList taskList) {
        this.taskList = (ArrayList<Task>) taskList.taskList.clone();
    }
    public TaskList(int capacity) {
        taskList = new ArrayList<>(capacity + 10);
    }
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Getting the size of the task list
     * @return the size of the taskList
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Gets the task in the respective index
     * @param index the index of the file to get
     * @return Task the task in index
     * @throws ElziException if index is invalid!
     */
    public Task getIndex(int index) throws ElziException {
        if (index >= 0 && index < taskList.size()) {
            return taskList.get(index);
        }
        throw new ElziException("Please enter a valid index!");
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the task from taskList
     * @param index the index of the file to remove
     * @throws ElziException if index is invalid!
     */
    public void removeTask(int index) throws ElziException {
        if (index >= 0 && index < taskList.size()) {
            taskList.remove(index);
        } else {
            throw new ElziException("Please enter a valid index!");
        }
    }
    /**
     * Set task as done
     * @param index the index of the file to mark
     * @throws ElziException if index is invalid!
     */
    public void setTaskAsDone(int index) throws ElziException {
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.get(index);
            task.setAsDone();
        } else {
            throw new ElziException("Please enter a valid index!");
        }
    }

    /**
     * Set task as not done
     * @param index the index of the file to unmark
     * @throws ElziException if index is invalid!
     */
    public void setTaskAsNotDone(int index) throws ElziException {
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.get(index);
            task.setAsNotDone();
        } else {
            throw new ElziException("Please enter a valid index!");
        }
    }

    /**
     * List the message with respect to its type
     * @param type the type of list to print
     * @return messages the message to list
     */
    public String listMessages(String type) {
        String messages = "";
        int counter = 1;
        if (taskList.size() != 0) {
            for (int index = 0; index < taskList.size(); index += 1) {
                Task task = taskList.get(index);
                if (task.getType().equals(type) || type.equals("N")) {
                    messages = messages.concat((counter) + ". " + task.toString() + "\n");
                    counter += 1;
                }
            }
        }
        if (counter == 1 || taskList.size() == 0) {
            messages = "No items in list!";
        }
        return messages;
    }
    /**
     * Finds the tasks that contains keyword
     * @param keyword the keyword to find
     * @return searchResult the TaskList of searchResult
     */
    public TaskList search(String keyword) {
        TaskList searchResult = new TaskList();

        for (int index = 0; index < taskList.size(); index += 1) {
            Task task = taskList.get(index);
            if (task.contains(keyword)) {
                searchResult.addTask(task);
            }
        }
        return searchResult;
    }
}
