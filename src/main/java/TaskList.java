//Solution below adapted and reused from Student Oh Yi Xiu Wilson
// with modifications made by Wilson Lee Jun Wei

import alltasks.Task;
import java.util.ArrayList;

/**
 * This class represents CoffeeBot performing actions in relation to the tasks
 * in the tasks list, based on users' inputs.
 */

public class TaskList {
    private static ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskArrayList.add(task);
    }

    public Task deleteTask(int index) {
        return this.taskArrayList.remove(index);
    }

    public String findTasks(String wordFind) {
            StringBuilder builtString = new StringBuilder("Here are the matching keyword(s) in your list:\n");
            for (int i = 0; i < this.taskArrayList.size(); i++) {
                if (this.taskArrayList.get(i).getDescription().contains(wordFind)) {
                    builtString.append(i + 1);
                    builtString.append(". ");
                    builtString.append(this.taskArrayList.get(i));
                    builtString.append("\n");
                }
            }
            return builtString.toString();
    }

    public String listTasks() {
        StringBuilder builtString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.taskArrayList.size(); i = i + 1) {
            Task item = this.taskArrayList.get(i);
            builtString.append((i + 1) + ". " + item + '\n');
        }
        return builtString.toString();
    }

    public int size() {
        return this.taskArrayList.size();
    }

    public Task getTask(int index) {
        return this.taskArrayList.get(index);
    }

    public void getTasksFromStorage(Storage storage) {
        this.taskArrayList.addAll(storage.getTasksFromFile());
    }

    public ArrayList<Task> getTasks() {
        return this.taskArrayList;
    }
}
//@@ Student Oh Yi Xiu Wilson