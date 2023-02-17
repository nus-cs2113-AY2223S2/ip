package tasks;

import java.util.ArrayList;
import java.util.stream.Collectors;
import misc.UI;

public class TaskManager {

  private ArrayList<Task> tasks;

  public TaskManager() {
    this.tasks = new ArrayList<Task>();
  }

  public ArrayList<Task> getAllTasks() {
    return this.tasks;
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public void markDone(int index) {
    tasks.get(index).markDone();
  }

  public void markUndone(int index) {
    tasks.get(index).markUndone();
  }

  public Task getTask(int index) {
    return tasks.get(index);
  }

  public int getNumberOfTasks() {
    return tasks.size();
  }

  public void removeTask(int index) {
    tasks.remove(index);
  }

  public void printTasksFromFind(String findText) {
    ArrayList<Task> filteredTasks = new ArrayList<Task>(
      this.tasks.stream()
        .filter(t ->
          t.getDescription().toLowerCase().contains(findText.toLowerCase())
        )
        .collect(Collectors.toList())
    );
    String toPrint = "";
    if (filteredTasks.size() == 0) {
      UI.printText("There are no matching tasks found.");
      return;
    }
    for (int i = 0; i < filteredTasks.size(); i++) {
      toPrint += String.format("%d. %s\t", i + 1, tasks.get(i));
    }
    UI.printText(toPrint);
  }

  @Override
  public String toString() {
    String toPrint = "";
    for (int i = 0; i < tasks.size(); i++) {
      toPrint += String.format("%d. %s\t", i + 1, tasks.get(i));
    }
    return toPrint;
  }
}
