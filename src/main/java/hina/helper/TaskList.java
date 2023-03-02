package hina.helper;

import hina.task.Deadline;
import hina.task.Event;
import hina.task.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;
    public TaskList(ArrayList<Task> savedList) {
        taskList = savedList;
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public static void listTasks() {
        if (taskList.size() == 0) {
            System.out.println("There are no items on the list :o");
        }
        for (Task task : taskList) {
            int i = taskList.indexOf(task);
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(task.toString());
        }
    }
    public static void getTaskCount() {
        System.out.printf("There are %d items on your list.\n", taskList.size());
    }
    public static void addTask(String description) {
        Task newTask = new Task(description);
        taskList.add(newTask);
        System.out.println("Noted! This task has been added:");
        System.out.println(newTask.toString());
        getTaskCount();
    }
    public static void addDeadline(String deadline) {
        String[] details = deadline.split("/by");
        if (details.length < 2) {
            System.out.println("Hina needs to know the deadline for this task!");
        } else {
            Deadline newDeadline = new Deadline(details[0], details[1]);
            taskList.add(newDeadline);
            System.out.println("Noted! This task has been added:");
            System.out.println(newDeadline.toString());
            getTaskCount();
        }
    }
    public static void addEvent(String event) {
        String[] details = event.split("/from");
        if (details.length < 2) {
            System.out.println("Please tell Hina when this event starts!");
        } else {
            if (details[1].split("/to").length < 2) {
                System.out.println("Please tell Hina when this event ends!");
            } else {
                Event newEvent = new Event(details[0], details[1].split("/to")[0].trim(), details[1].split("/to")[1].trim());
                taskList.add(newEvent);
                System.out.println("Noted! This task has been added:");
                System.out.println(newEvent.toString());
                getTaskCount();
            }
        }
    }
    public static void deleteTask(int taskIndex) {
        System.out.println("Got it! This task will be removed:");
        System.out.println(taskList.get(taskIndex - 1).toString());
        taskList.remove(taskIndex - 1);
        getTaskCount();
    }
    public static void markTask(int taskIndex) {
        taskList.get(taskIndex - 1).setDone(true);
        System.out.println("Roger that! This task is marked as done: ");
        System.out.println(taskList.get(taskIndex - 1).toString());
    }
    public static void unmarkTask(int taskIndex) {
        taskList.get(taskIndex - 1).setDone(false);
        System.out.println("Roger that! This task is marked as not done: ");
        System.out.println(taskList.get(taskIndex - 1).toString());
    }
    public static void findTask(String line) {
        String query = line.substring(4).trim();
        ArrayList<Task> matchList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(query)) {
                matchList.add(task);
            }
        }
        if (!matchList.isEmpty()) {
            Ui.taskFoundMessage();
            for (Task task : matchList) {
                int i = matchList.indexOf(task);
                System.out.print(i + 1);
                System.out.print(". ");
                System.out.println(task.toString());
            }
        } else {
            Ui.taskNotFoundMessage();
        }
    }
}
