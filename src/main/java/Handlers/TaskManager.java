package Handlers;

import Tasks.Task;
import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public static void addTask(Task t) {
        taskList.add(t);
    }

    public static void deleteTask(int index) {
        System.out.println("Noted. I've removed this task:\n" + taskList.get(index - 1).describeTask() + "\n");
        taskList.remove(index - 1);
    }

    public static int getTaskCount() {
        return taskList.size();
    }

    public static void listTask() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks in your list yet!");
            return;
        }

        int existingTaskCount = 1;
        for (Task item : taskList) {
            System.out.println(existingTaskCount + ". " + item.describeTask());
            existingTaskCount++;
        }

        System.out.println("\nYou have " + getTaskCount() + " tasks in the list.\n");
    }

    public static void markTask(int index) {
        if (index > 0 && index <= getTaskCount()) {
            ArrayList<Task> list = getTaskList();
            Task task = list.get(index - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + task.describeTask() + "\n");
        }
    }

    public static void unmarkTask(int index) {
        if (index > 0 && index <= getTaskCount()) {
            ArrayList<Task> list = getTaskList();
            Task task = list.get(index - 1);
            task.unmarkAsDone();
            System.out.println("OK, Ive marked this task as not done yet:\n" + task.describeTask() + "\n");
        }
    }

    public static void findTask(String description) {
        ArrayList<Task> list = getTaskList();
        int existingTaskCount = 1;
        for (Task item : list) {
            if (item.describeTask().contains(description)) {
                System.out.println(existingTaskCount + ". " + item.describeTask());
                existingTaskCount++;
            }
        }
    }

    public static void readTaskFromFile(String line) {
        String[] taskDetails = line.split("\\|");
        String taskType = taskDetails[0].trim();
        String taskStatus = taskDetails[1].trim();
        String taskDescription = taskDetails[2].trim();

        switch (taskType) {
        case "T":
            Task todo = new Tasks.Todo(taskDescription);
            if (taskStatus.equals("1")) {
                todo.markAsDone();
            }
            addTask(todo);
            break;
        case "D":
            String taskDeadline = taskDetails[3].trim();
            Task deadline = new Tasks.Deadline(taskDescription, taskDeadline);
            if (taskStatus.equals("1")) {
                deadline.markAsDone();
            }
            addTask(deadline);
            break;
        case "E":
            String[] taskDate = taskDetails[3].split("-");
            String taskDateFrom = taskDate[0].trim();
            String taskDateTo = taskDate[1].trim();
            Task event = new Tasks.Event(taskDescription, taskDateFrom, taskDateTo);
            if (taskStatus.equals("1")) {
                event.markAsDone();
            }
            addTask(event);
            break;
        default:
            System.out.println("Error reading file");
            break;
        }
    }
}
