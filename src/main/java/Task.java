import java.util.ArrayList;

public class Task {

  private ArrayList<String> items = new ArrayList<String>();
  private ArrayList<Boolean> doneItems = new ArrayList<Boolean>();

  Task() {}

  public void add(String task) {
    items.add(task);
    doneItems.add(false);
  }

  public String markDone(int taskIdx) {
    taskIdx = taskIdx - 1;
    if (list.get(taskIdx) != null && !doneItems.get(taskIdx)) {
      doneItems.set(taskIdx, true);
      return String.format("[X] %s\n", items.get(taskIdx));
    } else return "Task has not been added or task is already marked done.";
  }

  public String markUndone(int taskIdx) {
    taskIdx = taskIdx - 1;
    if (list.get(taskIdx) != null && doneItems.get(taskIdx)) {
      doneItems.set(taskIdx, false);
      return String.format("[ ] %s\n", items.get(taskIdx));
    } else return "Task has not been added or task is not done.";
  }

  public void printList() {
    String s = "";
    for (int i = 0; i < items.size(); i++) {
      s +=
        String.format(
          "%d.[%s] %s\n\t",
          i + 1,
          doneItems.get(i) ? "X" : " ",
          items.get(i)
        );
    }
    System.out.printf(
      "\n\t____________________________________________________________\n\t" +
      s +
      "\n\t" +
      "____________________________________________________________\n"
    );
  }
}
