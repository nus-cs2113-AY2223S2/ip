package tasks;

import exceptions.TaskListEmptyError;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasksList;
    private int currTaskNumber;

    public TaskList(ArrayList<Task> taskList) {
        tasksList = taskList;
        currTaskNumber = taskList.size();
    }
    public TaskList(){
        tasksList = new ArrayList<>();
        currTaskNumber = 0;
    }

    public ArrayList<Task> getTasksList() {
        return tasksList;
    }
    public int getCurrTaskNumber() {
        return currTaskNumber;
    }
    public void addNewTask(Task task) {
        tasksList.add(task);
        currTaskNumber++;
    }
    public void deleteTask(int index) throws TaskListEmptyError {
        if (tasksList.isEmpty()) {
            throw new TaskListEmptyError();
        }
        tasksList.remove(index - 1);
        currTaskNumber--;
    }
    public Task getNewestTask() {
        return tasksList.get(currTaskNumber - 1);
    }
    public Task getTaskFromList(int index) {
        return tasksList.get(index - 1);
    }
    public boolean checkTaskDone(int index) {
        return tasksList.get(index - 1).getIsDone();
    }
    public void setTaskAsDone(int index) {
        tasksList.get(index - 1).markAsDone();
    }
    public void setTaskAsUndone(int index) {
        tasksList.get(index - 1).markAsUndone();
    }
}
