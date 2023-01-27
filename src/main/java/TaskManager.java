import java.util.ArrayList;

public class TaskManager {

  private ArrayList<Task> tasks;

  public TaskManager() {
    this.tasks = new ArrayList<Task>();
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

  public void printList() {
    String s = "";
    for (int i = 0; i < tasks.size(); i++) {
      s += String.format("%d. %s\t", i + 1, tasks.get(i));
    }
    Util.printText(s);
  }
}
