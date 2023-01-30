import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    public void AddTask(String taskName) {
        Todo t = new Todo(taskName);
        list.add(t);
    }

    public void AddTask(String taskName, String byWhen) {
        Deadline d = new Deadline(taskName, byWhen);
        list.add(d);
    }

    public void AddTask(String taskName, String startWhen, String endWhen) {
        Event e = new Event(taskName, startWhen, endWhen);
        list.add(e);
    }

    public boolean MarkTask(int taskNumber, boolean mark) {
        if (taskNumber <= 0 || taskNumber > list.size()) {
            return false;
        } else {
            if (mark && list.get(taskNumber - 1).IsCompleted()) {
                System.out.println("Task already marked as completed!");
            } else if (!mark && !list.get(taskNumber - 1).IsCompleted()) {
                System.out.println("Task already marked as not completed!");
            } else {
                list.get(taskNumber - 1).SetCompleted(mark);
                if (mark) {
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + list.get(taskNumber - 1).GetTaskName());
                } else {
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[ ] " + list.get(taskNumber - 1).GetTaskName());
                }
            }
            return true;
        }
    }

    public void ListTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(i + 1);
            System.out.println(list.get(i).toString());
        }
    }

    public int GetTaskCount() {
        return list.size();
    }
}

