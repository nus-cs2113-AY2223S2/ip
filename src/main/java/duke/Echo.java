package duke;//import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;

public class Echo {
    public static void Echo() {
        //Task[] tasks = new Task[100];
        ArrayList<Task> tasks = new ArrayList<>();
        int count = 0;

        Scanner in = new Scanner(System.in);
        String readInput = in.nextLine();
        String readInputAsArray[] = readInput.split(" ", 2);
        String command = readInputAsArray[0];
        String task = " ";
        if (readInputAsArray.length > 1) {
            task = readInputAsArray[1];
        }

        while (!(command.equals("Bye") || command.equals("bye"))) {
            if (command.equals("list")) {
                list_Input(tasks, count);
            } else if (command.equals("unmark")) {
                try {
                    unmark_Input(tasks, task);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! Which task do you want to unmark?");
                }
            } else if (command.equals("mark")) {
                try {
                    mark_Input(tasks, task);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! Which task do you want to mark?");
                }
            } else if (command.equals("delete")) {
                try {
                    delete(tasks, task);
                } catch (DukeException e){
                    System.out.println("☹ OOPS!!! Which task do you want to delete?");
                }
            } else if (command.equals("todo")) {
                try {
                    count = todo_Input(tasks, count, task);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (command.equals("deadline")) {
                try {
                    count = deadline_Input(tasks, count, task);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (command.equals("event")) {
                try {
                    count = event_Input(tasks, count, task);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            readInput = in.nextLine();
            readInputAsArray = readInput.split(" ", 2);
            command = readInputAsArray[0];
            task = " ";
            if (readInputAsArray.length > 1) {
                task = readInputAsArray[1];
            }
        }

        System.out.println("Bye. Hope to see you again!");
    }

    private static int event_Input(ArrayList<Task> tasks, int count, String task) throws DukeException {
        if (task == " ") {
            throw new DukeException();
        }
        System.out.println("Got it. I've added this task:");
        String taskAsArray[] = task.split("/");
        tasks.add(new Event(taskAsArray[0], taskAsArray[1], taskAsArray[2]));
        System.out.println(tasks.get(count).toString());
        ++count;
        System.out.println("Now you have " + count + " tasks in the list.");
        return count;
    }

    private static int deadline_Input(ArrayList<Task> tasks, int count, String task) throws DukeException {
        if (task == " ") {
            throw new DukeException();
        }
        System.out.println("Got it. I've added this task:");
        String taskAsArray[] = task.split("/");
        tasks.add(new Deadline(taskAsArray[0], taskAsArray[1]));
        System.out.println(tasks.get(count).toString());
        ++count;
        System.out.println("Now you have " + count + " tasks in the list.");
        return count;
    }

    private static int todo_Input(ArrayList<Task> tasks, int count, String task) throws DukeException {
        if (task == " ") {
            throw new DukeException();
        }
        System.out.println("Got it. I've added this task:");
        tasks.add(new Todo(task));
        System.out.println(tasks.get(count).toString());
        ++count;
        System.out.println("Now you have " + count + " tasks in the list.");
        return count;
    }

    private static void mark_Input(ArrayList<Task> tasks, String task) throws DukeException {
        if (task == " ") {
            throw new DukeException();
        }
        int taskNoInt = Integer.parseInt(task);
        tasks.get(taskNoInt - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNoInt - 1).toString());
    }

    private static void unmark_Input(ArrayList<Task> tasks, String task) throws DukeException {
        if (task == " ") {
            throw new DukeException();
        }
        int taskNoInt = Integer.parseInt(task);
        tasks.get(taskNoInt - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNoInt - 1).toString());
    }

    private static void delete(ArrayList<Task> tasks, String task) throws DukeException {
        if (task == " ") {
            throw new DukeException();
        }
        int taskNoInt = Integer.parseInt(task);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(taskNoInt - 1).toString());
        tasks.remove(taskNoInt - 1);
        System.out.println("Now you have " + tasks.size() +" tasks in the list.");
    }

    private static void list_Input(ArrayList<Task> tasks, int count) {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + "." + task.toString());
            ++index;
        }
    }
}
