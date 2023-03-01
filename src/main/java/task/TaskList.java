package task;

import java.util.ArrayList;
import exceptions.DukeException;

public class TaskList {
    private static final String LINE = "____________________________________________________________";
    private static final String errorMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String todoError = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public static void deleteTask(int index) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + list.get(index).toString());
        list.remove(index);
        int size = list.size();
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public static void markTask(int index) {
        System.out.println(LINE);
        list.get(index).markDone();
        System.out.println("Awesome! I've mark this task as done:");
        System.out.println("[" + list.get(index).getStatusIcon() + "] " + list.get(index).description.split(" ", 2)[1]);
    }

    public static void unmarkTask(int index) {
        System.out.println(LINE);
        list.get(index).markUndone();
        System.out.println("What!?!? OK, I've marked this task as not done yet:");
        System.out.println("[" + list.get(index).getStatusIcon() + "] " + list.get(index).description.split(" ", 2)[1]);
    }

    public static void addTodo(String input) throws DukeException {
        try {
            Todo task = new Todo(input);
            task.setTaskType("T");
            list.add(task);
            if (task.description.split(" ").length < 2) {
                throw new DukeException(todoError);
            } else {
                System.out.println(LINE);
                System.out.println("Roger! The Todo task has been added: \n" + task.toString());
                System.out.println("Now you have " + list.size() + " in the list");
                System.out.println(LINE);
            }
        } catch (DukeException e) {
            printError(e);
        }
    }

    public static void addDeadline(String input) {
        Deadline task = new Deadline(input);
        task.setTaskType("D");
        list.add(task);
        System.out.println(LINE);
        System.out.println("Roger! The Deadline task has been added: \n    " + task.toString());
        System.out.println("Now you have " + list.size() + " in the list");
        System.out.println(LINE);
    }

    public static void addEvent(String input) {
        Event task = new Event(input);
        task.setTaskType("E");
        list.add(task);
        System.out.println(LINE);
        System.out.println("Roger! The Deadline task has been added: \n    " + task.toString());
        System.out.println("Now you have " + list.size() + " in the list");
        System.out.println(LINE);
    }

    public static void addTodoData(String input, String status) {
            Todo task = new Todo(input);
            if (status.equals("X")) {
                task.markDone();
            }
            task.setTaskType("T");
            list.add(task);
    }

    public static void addDeadlineData(String input, String status) {
        Deadline task = new Deadline(input);
        if (status.equals("X")) {
            task.markDone();
        }
        task.setTaskType("D");
        list.add(task);
    }

    public static void addEventData(String input, String status) {
        Event task = new Event(input);
        if (status.equals("X")) {
            task.markDone();
        }
        task.setTaskType("E");
        list.add(task);
    }

    public static void printList() {
        System.out.println(LINE);
        int numTask = list.size();
        if (numTask == 0) {
            System.out.println("No task added yet");
            System.out.println(LINE);
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i+1) + "." + list.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    public static void loadSaveList() {
        System.out.println(LINE);
        int numTask = list.size();
        if (numTask == 0) {
            System.out.println("No saved task recorded");
            System.out.println(LINE);
        } else {
            System.out.println("Here are the tasks in your save list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i+1) + "." + list.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    private static void printFoundList(ArrayList<Task> foundTasks) {
        System.out.println(LINE);
        int numTask = foundTasks.size();
        if (numTask == 0) {
            System.out.println("No task found");
            System.out.println(LINE);
        } else {
            System.out.println("Here are the matching tasks in your save list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i+1) + "." + foundTasks.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    public static void findTask(String input) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for(Task t : list) {
            String task = t.description;
            if (task.contains(input)) {
                foundTasks.add(t);
            }
        }
        printFoundList(foundTasks);
    }

    public static void printError(DukeException e) {
        System.out.println(LINE + System.lineSeparator() + e.getMessage() + System.lineSeparator() + LINE );
    }
}
