package task;

import UI.Ui;

import java.util.ArrayList;

/**
 * this class manages the list of tasks that Duke is using
 * Supports adding and deleting different types of tasks
 */
public class TaskList {
    public static ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public static void addTodo(String taskDescription, String status) {
        ToDo existingToDo = new ToDo(taskDescription);
        existingToDo.setType("T");
        if(status.equals("X")) {
            existingToDo.markDone();
        }
        list.add(existingToDo);
        Ui.printConfirmation(existingToDo, "add");
    }

    public static void addDeadline(String taskDescription, String deadline, String status) {
        Deadline existingDeadline = new Deadline(taskDescription, deadline);
        existingDeadline.setType("D");
        if(status.equals("X")) {
            existingDeadline.markDone();
        }
        list.add(existingDeadline);
        Ui.printConfirmation(existingDeadline, "add");
    }

    public static void addEvent(String taskDescription, String from, String to, String status) {
        Event existingEvent = new Event(taskDescription, from, to);
        existingEvent.setType("E");
        if(status.equals("X")) {
            existingEvent.markDone();
        }
        list.add(existingEvent);
        Ui.printConfirmation(existingEvent, "add");
    }

    public static void deleteTask(int index) {
        Task taskToRemove = list.get(index);
        list.remove(index);
        Ui.printConfirmation(taskToRemove, "delete");
    }
}
