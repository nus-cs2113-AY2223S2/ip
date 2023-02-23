package duke.task;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> allTasks;

    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public static ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public static ArrayList<Task> getTasksOnDate(LocalDate date) {
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.isOnDate(date)) {
                tasksOnDate.add(task);
            }
        }
        return tasksOnDate;
    }

    public static ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.description.contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    public static int getSize() {
        return allTasks.size();
    }

    public static void markDone(int idx) {
        allTasks.get(idx).setDone(true);
    }

    public static void markNotDone(int idx) {
        allTasks.get(idx).setDone(false);
    }

    public static void deleteTask(int idx) {
        allTasks.remove(idx);
    }

    public static void addToDo(String param) {
        allTasks.add(new ToDo(param));
    }

    public static void addDeadline(String param, String by) {
        allTasks.add(new Deadline(param, by));
    }

    public static void addEvent(String param, String from, String to) {
        allTasks.add(new Event(param, from, to));
    }
}
