package tasks;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Manages all the Tasks, including Deadlines, Events and Todos. Provides utility
 * functions to modify the state of the tasks and the state of the task list.
 */
public class TaskManager {

  private ArrayList<Task> tasks;

  /**
   * Task Manager Constructor
   */
  public TaskManager() {
    this.tasks = new ArrayList<Task>();
  }

  /**
   * This method is a getter used to get all the tasks in the Task Manager.
   * @return ArrayList<Task> This returns all the tasks in the Task Manager.
   */
  public ArrayList<Task> getAllTasks() {
    return this.tasks;
  }

  /**
   * This method is used to add a task to the Task Manager
   * @param task This is the task to be added to the Task Manager
   */
  public void addTask(Task task) {
    tasks.add(task);
  }

  /**
   * This method is used to mark a task as done by its index
   * @param index This is the index of the task to be marked done.
   */
  public void markDone(int index) {
    tasks.get(index).markDone();
  }

  /**
   * This method is used to mark a task as undone by its index
   * @param index This is the index of the task to be marked undone.
   */
  public void markUndone(int index) {
    tasks.get(index).markUndone();
  }

  /**
   * This method is used to get a task by its index
   * @param index This is the index of the task to be gotten.
   * @return Task This returns the task at index given by the param index.
   */
  public Task getTask(int index) {
    return tasks.get(index);
  }

  /**
   * This method is used to get the number of tasks.
   * @return int This is the number of tasks in the Task Manager.
   */
  public int getNumberOfTasks() {
    return tasks.size();
  }

  /**
   * This method is used to remove a task by its index
   * @param index This is the index of the task to be removed.
   */
  public void removeTask(int index) {
    tasks.remove(index);
  }

  /**
   * This method is used to print a task based on a given string.
   * @param findText This is the string we use to search in the Task Manager for.
   * @return ArrayList<Task> This is the list of tasks in the Task Manager found using
   * the search term.
   */
  public ArrayList<Task> getTasksFromFind(String findText) {
    ArrayList<Task> filteredTasks = new ArrayList<Task>(
      this.tasks.stream()
        .filter(t ->
          t.getDescription().toLowerCase().contains(findText.toLowerCase())
        )
        .collect(Collectors.toList())
    );
    return filteredTasks;
  }

  /**
   * This method is used to convert a TaskManager Instance into a String.
   * @return String This is the output we wish to get when we process a TaskManager
   * instance as a String.
   */
  @Override
  public String toString() {
    String toPrint = "";
    for (int i = 0; i < tasks.size(); i++) {
      toPrint += String.format("%d. %s\t", i + 1, tasks.get(i));
    }
    return toPrint;
  }
}
