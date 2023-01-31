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

    public void UnmarkTask(int taskNumber, Display UI) {
        if (taskNumber <= 0 || taskNumber > list.size()) {
            UI.TaskNotFound();
        } else if (!list.get(taskNumber - 1).IsCompleted()) {
            UI.InvalidUnmark();
        } else {
            list.get(taskNumber - 1).SetCompleted(false);
            UI.ValidUnmark(list, taskNumber);
        }
    }

    public void MarkTask(int taskNumber, Display UI) {
        if (taskNumber <= 0 || taskNumber > list.size()) {
            UI.TaskNotFound();
        } else if (list.get(taskNumber - 1).IsCompleted()) {
            UI.InvalidMark();
        } else {
            list.get(taskNumber - 1).SetCompleted(true);
            UI.ValidMark(list, taskNumber);
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

