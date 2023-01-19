import java.util.*;

public class Task {

  private ArrayList<String> list = new ArrayList<String>();
  private ArrayList<Boolean> listDone = new ArrayList<Boolean>();

  Task() {}

  public void add(String task) {
    list.add(task);
    listDone.add(false);
  }

  public String markDone(int taskIdx) {
    taskIdx = taskIdx - 1;
    if (list.get(taskIdx) != null && !listDone.get(taskIdx)) {
      listDone.set(taskIdx, true);
      return String.format("[X] %s\n", list.get(taskIdx));
    } else return "Task has not been added or task is already marked done.";
  }

  public String markUndone(int taskIdx) {
    taskIdx = taskIdx - 1;
    if (list.get(taskIdx) != null && listDone.get(taskIdx)) {
      listDone.set(taskIdx, false);
      return String.format("[ ] %s\n", list.get(taskIdx));
    } else return "Task has not been added or task is not done.";
  }

  public void printList() {
    String s = "";
    for (int i = 0; i < list.size(); i++) {
      s +=
        String.format(
          "%d.[%s] %s\n\t",
          i + 1,
          listDone.get(i) ? "X" : " ",
          list.get(i)
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
