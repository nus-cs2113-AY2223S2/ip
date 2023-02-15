package duke.classes;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static void checkError(String input) throws DukeException {
        if (Objects.equals(input, "event") || Objects.equals(input, "todo") || Objects.equals(input, "deadline")) {
            throw new DukeException("The description of the body cannot be empty! Please enter a proper input.");
        } else if (Objects.equals(input, "")) {
            throw new DukeException("You did not enter any input! Please enter a proper input.");
        } else {
            throw new DukeException("I'm sorry, but i don't know what that means. Please enter a proper input.");
        }
    }

    private static ArrayList<Task> list_of_tasks = new ArrayList<Task>();

    private static void addTask(Task task) {
        list_of_tasks.add(task);
    }

    private static void markTask(Task task) {
        task.isDone = true;
    }

    private static void unmarkTask(Task task) {
        task.isDone = false;
    }
    private static void printTasks() {
        int order = 1;
        for(int i = 0; i < list_of_tasks.size(); i++) {
            System.out.println(order + "." + list_of_tasks.get(i));
            order++;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you\n");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        boolean isBye = false;
        int count = 0;
        while (!isBye) {

            if (Objects.equals(input, "bye")) {
                isBye = true;
                break;

            } else if (Objects.equals(input, "list")) {
                System.out.println("Here are the tasks in your list:");
                printTasks();

            } else if (input.length() > 5 && (input.substring(0,5)).equals("mark ") && input.substring(5, input.length()).matches("[0-9]+")) {
                    Integer order = Integer.valueOf(input.substring(5, input.length()));
                    Task task = list_of_tasks.get(order - 1);
                    markTask(task);
                    System.out.println("Nice! I've marked this task as done:\n" + task);

            } else if (input.length() > 7 && (input.substring(0,7)).equals("unmark ") && input.substring(7, input.length()).matches("[0-9]+")) {
                    Integer order = Integer.valueOf(input.substring(7, input.length()));
                    Task task = list_of_tasks.get(order - 1);
                    unmarkTask(task);
                System.out.println("OK, I've marked this task as not done yet:\n" + task);

            } else if (input.length() > 7 && input.substring(0,7).equals("delete ") && input.substring(7, input.length()).matches("[0-9]+")) {
                Integer order = Integer.valueOf(input.substring(7, input.length()));
                System.out.println("Noted, I've removed this task\n" + list_of_tasks.get(order - 1));
                list_of_tasks.remove(order - 1);
                count--;
                System.out.println("Now you have " + count + " tasks in the list");

            } else if (Objects.equals(input, "event") || Objects.equals(input, "todo") || Objects.equals(input, "deadline")) {
                try {
                    checkError(input);
                } catch(DukeException e) {
                    System.out.println("Error: " + e);
                }
            } else {
                if (input.length() > 3 && input.substring(0,4).equals("todo")) {
                    String info = input.substring(5,input.length());
                    Todo task = new Todo(info);
                    task.isDone = false;
                    addTask(task);
                    System.out.println("Got it. I've added this task: \n" + task + "\nNow you have " + (count + 1) + " tasks in your list." );
                    count++;
                } else if (input.length() > 7 && input.substring(0,8).equals("deadline")) {
                    String info = input.substring(9,input.indexOf("/"));
                    String timeBy = input.substring(input.indexOf("/")+1, input.length());
                    Deadline task = new Deadline(info, timeBy);
                    task.isDone = false;
                    addTask(task);
                    System.out.println("Got it. I've added this task: \n" + task + "\nNow you have " + (count + 1) + " tasks in your list." );
                    count++;
                } else if (input.length() > 4 && input.substring(0,5).equals("event")) {
                    String info = input.substring(6,input.indexOf("/"));
                    String timeFrom = input.substring(input.indexOf("/")+1, input.lastIndexOf("/") - 1);
                    String timeBy = input.substring(input.lastIndexOf("/")+1, input.length());
                    Event task = new Event(info, timeFrom, timeBy);
                    task.isDone = false;
                    addTask(task);
                    System.out.println("Got it. I've added this task: \n" + task + "\nNow you have " + (count + 1) + " tasks in your list." );
                    count++;
                } else {
                    try {
                        checkError(input);
                    } catch(DukeException e) {
                        System.out.println("Error: " + e);
                    }
                }
            }
            input = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
