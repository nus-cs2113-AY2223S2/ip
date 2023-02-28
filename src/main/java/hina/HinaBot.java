package hina;

import hina.exceptions.HinaException;
import hina.task.Deadline;
import hina.task.Event;
import hina.task.Task;


import java.util.ArrayList;
import java.util.Scanner;


public class HinaBot {
    protected static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                line = in.nextLine();
                handleCommand(line);
            } catch (HinaException cmdException) {
                System.out.println(">.< Hina does not recognise this command!");
            } catch (StringIndexOutOfBoundsException argException) {
                System.out.println("@_@ Please give Hina more details!");
            }
        }
    }

    private static void showGreeting() {
        System.out.println("Hello master!");
        System.out.println("What are your orders?");
    }

    public static void handleCommand(String command) throws HinaException {
        if (command.equalsIgnoreCase("bye")) {
            System.out.println("Goodbye master, let's meet again soon...");
            System.exit(0);
        } else if (command.equalsIgnoreCase("list")) {
            listTasks();
        } else if (command.split(" ")[0].equalsIgnoreCase("mark")) {
            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            markTask(taskIndex);
        } else if (command.split(" ")[0].equalsIgnoreCase("unmark")) {
            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            unmarkTask(taskIndex);
        } else if (command.split(" ")[0].equalsIgnoreCase("todo")) {
            addTask(command.substring(5));
        } else if (command.split(" ")[0].equalsIgnoreCase("deadline")) {
            addDeadline(command.substring(9));
        } else if (command.split(" ")[0].equalsIgnoreCase("event")) {
            addEvent(command.substring(6));
        } else if (command.split(" ")[0].equalsIgnoreCase("delete")) {
            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            deleteTask(taskIndex);
        } else {
            throw new HinaException();
        }
    }

    public static void addTask(String description) {
        Task newTask = new Task(description);
        taskList.add(newTask);
        System.out.println("Noted! This task has been added:");
        System.out.println(newTask.toString());
        getTaskCount();
    }

    public static void listTasks() {
        for (Task task : taskList) {
            int i = taskList.indexOf(task);
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(task.toString());
        }
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

    public static void addDeadline(String deadline) {
        String[] details = deadline.split("/");
        if (details.length < 2) {
            System.out.println("Hina needs to know the deadline for this task!");
        } else {
            Deadline newDeadline = new Deadline(details[0], details[1].substring(3));
            taskList.add(newDeadline);
            System.out.println("Noted! This task has been added:");
            System.out.println(newDeadline.toString());
            getTaskCount();
        }
    }

    public static void addEvent(String event) {
        String[] details = event.split("/");
        if (details.length < 3) {
            System.out.println("Please tell Hina when this event starts and ends!");
        } else {
            Event newEvent = new Event(details[0], details[1].substring(5).trim(), details[2].substring(3));
            taskList.add(newEvent);
            System.out.println("Noted! This task has been added:");
            System.out.println(newEvent.toString());
            getTaskCount();
        }
    }

    public static void deleteTask(int taskIndex) {
        System.out.println("Got it! This task will be removed:");
        System.out.println(taskList.get(taskIndex - 1).toString());
        taskList.remove(taskIndex - 1);
    }

    public static void getTaskCount() {
        System.out.printf("There are %d items on your list.\n", taskList.size());
    }
}
