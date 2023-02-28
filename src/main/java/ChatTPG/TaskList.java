package ChatTPG;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int taskCount = 0;

    public static ToDo createToDo(String command, boolean isDone) {
        ToDo todo = new ToDo(command.substring(5), isDone);
        return todo;
    }

    public static Deadline createDeadline(String command, boolean isDone, boolean processed)
            throws InvalidDateFormat {
        int separatorIndex = command.indexOf("/by");
        String description = command.substring(9, separatorIndex - 1);
        String by = command.substring(separatorIndex + 5);
        String end = DateTimeParser.processDateTime(by, processed);
        Deadline deadline = new Deadline(description, isDone, end);
        return deadline;
    }

    public static Event createEvent(String command, boolean isDone, boolean processed)
            throws InvalidDateFormat, InvalidStartEnd {
        int fromIndex = command.indexOf("/from");
        int toIndex = command.indexOf("/to");
        String description = command.substring(6, fromIndex - 1);
        String from = command.substring(fromIndex + 7, toIndex - 1);
        String to = command.substring(toIndex + 5);
        String begin = DateTimeParser.processDateTime(from, processed);
        String end = DateTimeParser.processDateTime(to, processed);
        Event event = new Event(description, isDone, begin, end);
        return event;
    }

    public static void addToList(Task task) {
        tasks.add(task);
        taskCount++;
        Storage.saveData(tasks);
    }

    public static void notifyTaskAdded(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        displayNumberOfTasks();
    }

    public static void displayNumberOfTasks() {
        if (taskCount == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.\n", taskCount);
        }
    }

    public static void markTask(int taskNumber) {
        tasks.get(taskNumber).setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNumber).toString());
        Storage.saveData(tasks);
    }

    public static void unmarkTask(int taskNumber) {
        tasks.get(taskNumber).setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNumber).toString());
        Storage.saveData(tasks);
    }

    public static void deleteTask(int taskNumber) {
        String description = tasks.get(taskNumber).toString();
        tasks.remove(taskNumber);
        System.out.println("Noted. I've removed this task:");
        System.out.println(description);
        taskCount--;
        displayNumberOfTasks();
        Storage.saveData(tasks);
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int idx = 0; idx < taskCount; idx++) {
            System.out.println(tasks.get(idx).toString());
        }
    }
}
