package ChatTPG;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int task_count = 0;

    public static ToDo createToDo(String command, boolean isDone) {
        ToDo todo = new ToDo(command.substring(5), isDone);
        return todo;
    }

    public static Deadline createDeadline(String command, boolean isDone) {
        int separator_idx = command.indexOf("/by");
        String description = command.substring(9, separator_idx - 1);
        String by = command.substring(separator_idx + 4);
        Deadline deadline = new Deadline(description, isDone, by);
        return deadline;
    }

    public static Event createEvent(String command, boolean isDone) {
        int from_idx = command.indexOf("/from");
        int to_idx = command.indexOf("/to");
        String description = command.substring(6, from_idx - 1);
        String from = command.substring(from_idx + 6, to_idx - 1);
        String to = command.substring(to_idx + 4);
        Event event = new Event(description, isDone, from, to);
        return event;
    }

    public static void addToList(Task task) {
        tasks.add(task);
        task_count++;
        Storage.saveData(tasks);
    }

    public static void notifyTaskAdded(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        displayNumberOfTasks();
    }

    public static void displayNumberOfTasks() {
        if (task_count == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.\n", task_count);
        }
    }

    public static void markTask(int task_number) {
        tasks.get(task_number).setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(task_number).toString());
        Storage.saveData(tasks);
    }

    public static void unmarkTask(int task_number) {
        tasks.get(task_number).setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(task_number).toString());
        Storage.saveData(tasks);
    }

    public static void deleteTask(int task_number) {
        String description = tasks.get(task_number).toString();
        tasks.remove(task_number);
        System.out.println("Noted. I've removed this task:");
        System.out.println(description);
        task_count--;
        displayNumberOfTasks();
        Storage.saveData(tasks);
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int idx = 0; idx < task_count; idx++) {
            System.out.println(tasks.get(idx).toString());
        }
    }
}
