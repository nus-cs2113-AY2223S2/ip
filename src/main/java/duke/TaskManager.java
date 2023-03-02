package duke;

import java.util.*;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String name, boolean isDone) {
        tasks.add(new Task(name, isDone));
    }

    public void addDeadline(String name, String deadline, boolean isDone) {
        tasks.add(new Deadline(name, isDone, deadline));
    }

    public void addEvent(String eventName, String startTime, String finishTime, boolean isDone) {
        tasks.add(new Event(eventName, isDone, startTime, finishTime));
    }

    public void markTask(int id) {
        tasks.get(id).setIsDone(true);
        System.out.println("This item has been marked as done!");
        System.out.println("[X] " + tasks.get(id).getName());
    }

    public void unmarkTask(int id) {
        tasks.get(id).setIsDone(false);
        System.out.println("The item has been marked as NOT done!");
        System.out.println("[ ] " + tasks.get(id).getName());
    }

    public void listTask() {
        if (tasks.size() == 0) {
            System.out.println("The list is currently empty!");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1);
            tasks.get(i).print();
        }
    }

    public void deleteTask(int id) {
        System.out.println("This item has been deleted!");
        System.out.println("[ ] " + tasks.get(id).getName());
        tasks.remove(id);
    }

    public int getSize() {
        return tasks.size();
    }

    public void clearData() {
        tasks.clear();
        System.out.println("Your list has been cleared !");
    }

    public Task getId(int id) {
        return tasks.get(id);
    }

}
