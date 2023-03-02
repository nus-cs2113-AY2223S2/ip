package duke.tasks;

import duke.DukeException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private int numTasks;

    public TaskList() {
        tasks = new ArrayList<>();
        // setup filler task
        Task fillerTask = new Task("filler", false);
        tasks.add(fillerTask);
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= numTasks; i++) {
            Task currTask = tasks.get(i);
            System.out.println(i + ". " + currTask.toString());
        }
    }

    public void markTask(int taskNumToMark) {
        Task taskToMark = tasks.get(taskNumToMark);
        taskToMark.mark();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(taskToMark.toString());
    }

    public void unmarkTask(int taskNumToUnmark) {
        Task taskToUnmark = tasks.get(taskNumToUnmark);
        taskToUnmark.unmark();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(taskToUnmark.toString());
    }

    public void addDeadline(String taskDescription, boolean isDone) {
        int bySize = 3;
        String description = taskDescription.substring(0, taskDescription.indexOf("by") - 2);
        String by = taskDescription.substring(taskDescription.indexOf("by") + bySize);
        Deadline newDeadline = new Deadline(description, by, isDone);
        tasks.add(newDeadline);
        printTaskAddedMessage(newDeadline, false);
    }

    public void addTodo(String taskDescription, boolean isDone) {
        boolean exceptionPresent = true;
        try {
            if (taskDescription.length() == 0) {
                throw new DukeException();
            } else {
                Todo newTodo = new Todo(taskDescription, isDone);
                tasks.add(newTodo);
                printTaskAddedMessage(newTodo, !exceptionPresent);
            }
        } catch (DukeException exception) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void addEvent(String taskDescription, boolean isDone) {
        String[] dates =  taskDescription.split("/from | /to |from: | to: ");
        String description = dates[0].substring(0, dates[0].length() - 1);
        String fromDate = dates[1];;
        String toDate = dates[2];
        Event newEvent = new Event(description, fromDate, toDate, isDone);
        tasks.add(newEvent);
        printTaskAddedMessage(newEvent, false);
    }

    public void deleteTask(int taskNumToDelete) {
        Task taskToDelete = tasks.get(taskNumToDelete);
        tasks.remove(taskToDelete);
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskToDelete.toString());
        numTasks--;
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    public void printTaskAddedMessage(Task task, boolean exceptionPresent) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        if (!exceptionPresent) {
            numTasks++;
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getNumTasks() {
        return numTasks;
    }
}
