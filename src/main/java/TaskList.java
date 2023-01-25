import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> TaskList = new ArrayList<>();

    public void AddTask(String taskName) {
        Task t = new Task(taskName);
        TaskList.add(t);
    }

    public boolean MarkTask(int taskNumber, boolean mark) {
        if (taskNumber <= 0 || taskNumber > TaskList.size()) {
            return false;
        } else {
            if (mark && TaskList.get(taskNumber - 1).IsCompleted()) {
                System.out.println("Task already marked as completed!");
            } else if (!mark && !TaskList.get(taskNumber - 1).IsCompleted()) {
                System.out.println("Task already marked as not completed!");
            } else {
                TaskList.get(taskNumber - 1).SetCompleted(mark);
                if (mark) {
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + TaskList.get(taskNumber - 1).GetTaskName());
                } else {
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[ ] " + TaskList.get(taskNumber - 1).GetTaskName());
                }
            }
            return true;
        }
    }

    public void ListTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TaskList.size(); i++) {
            if (TaskList.get(i).IsCompleted()) {
                System.out.println(i + 1 + ".[X] " + TaskList.get(i).GetTaskName());
            } else {
                System.out.println(i + 1 + ".[ ] " + TaskList.get(i).GetTaskName());
            }

        }
    }
}

