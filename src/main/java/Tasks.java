import java.util.ArrayList;

public class Tasks {
    private ArrayList<String> taskList;

    Tasks() {
        this.taskList = new ArrayList<String>();
    }

    String addTask(String task) {
        taskList.add(task);
        return "added: " + task;
    }

    String listTasks() {
        String list = "";
        int counter = 1;
        for (String task : taskList) {
            list += Integer.toString(counter) + ". ";
            list += task;
            if (counter != taskList.size()) list += "\n  ";
            ++counter;
        }
        return list;
    }
}
