import java.util.ArrayList;

public class Tasks {
    private ArrayList<Task> taskList;

    Tasks() {
        this.taskList = new ArrayList<Task>();
    }

    String addTask(String task) {
        taskList.add(new Task(task));
        return "added: " + task;
    }

    String mark(int index) {
        taskList.get(index - 1).markAsComplete();
        return "Nice! I've marked this task as done:\n  " + taskList.get(index - 1).toString();
    }

    String unmark(int index) {
        taskList.get(index - 1).markAsIncomplete();
        return "OK, I've marked this task as not done yet:\n  " + taskList.get(index - 1).toString();
    }

    String listTasks() {

        String list = "Here are the tasks in your list:\n";
        int counter = 1;
        for (Task task : taskList) {
            list += "  " + Integer.toString(counter) + ". ";
            list += task;
            list += "\n";
            ++counter;
        }
        return list;
    }
}
