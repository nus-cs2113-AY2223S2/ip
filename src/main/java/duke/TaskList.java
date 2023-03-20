package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 **/
public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList(int capacity) {
        taskList = new ArrayList<>(capacity+10);
    }
    public TaskList() {
        taskList = new ArrayList<>();
    }

    public int getSize() {
        return taskList.size();
    }
    public Task getIndex(int index) {
        return taskList.get(index);
    }

    public void addTask (Task task) {
        taskList.add(task);
    }
    public void removeTask (int index) throws DukeException{
        if (index >= 0 && index < taskList.size()) {
            taskList.remove(index);
        }
        else {
            throw new DukeException("Please enter a valid index!")
        }
    }

    public void setTaskAsDone(int index) throws DukeException {
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.get(index);
            task.setAsDone();
        }
        else {
            throw new DukeException("Please enter a valid index!")
        }
    }
    public void setTaskAsNotDone(int index) throws DukeException {
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.get(index);
            task.setAsNotDone();
        }
        else {
            throw new DukeException("Please enter a valid index!")
        }
    }

    public String listMessages() {
        String messages = "";

        if (taskList.size() != 0) {
            for (int index = 0; index < taskList.size(); index += 1) {
                Task task = taskList.get(index);
                if (index != taskList.size() - 1) {
                    messages = messages.concat((index + 1) + ". " + task.toString() + "\n");
                }
                else {
                    messages = messages.concat((index + 1) + ". " + task.toString());
                }
            }
        }
        else {
            messages = "No items in list!";
        }
        return messages;
    }

    public TaskList search(String keyword) {
        TaskList searchResult = new TaskList();

        for (int index = 0; index < taskList.size(); index += 1) {
            Task task = taskList.get(index);
            if (task.contains(keyword)) {
                searchResult.addTask(task);
            }
        }
    }
}
