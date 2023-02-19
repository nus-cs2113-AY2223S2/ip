package duke;

import java.util.ArrayList;
public class TaskList {
    public static ArrayList<Task> textList = new ArrayList<>();
    public TaskList() {
        this.textList = textList;
    }
    public static void addTask(Task task) {
        textList.add(task);
    }
    public static void deleteTask(int index) {
        textList.remove(index);
    }
    public static void markTask(int index) {
        textList.get(index).markAsDone();
    }
    public static void unmarkTask(int index) {
        textList.get(index).unmarkAsDone();
    }
    public static int getSize() {
        return textList.size();
    }
    public static Task getTask(int index) {
        return textList.get(index);
    }
    public static ArrayList<Task> getList() {
        return textList;
    }

    public static ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < textList.size(); i++) {
            if (textList.get(i).description.contains(keyword)) {
                foundTasks.add(textList.get(i));
            }
        }
        return foundTasks;
    }
}
