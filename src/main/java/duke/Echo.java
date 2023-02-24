package duke;//import java.util.Scanner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import static java.util.stream.Collectors.toList;

public class Echo {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void Echo() {

        //loading up contents into program
        FileHandling.loadContents();


        Scanner in = new Scanner(System.in);
        String readInput = in.nextLine();
        String[] readInputAsArray = readInput.split(" ", 2);
        String command = readInputAsArray[0];
//        String task = getTaskNameOrTaskNo(readInputAsArray);
        String task = " ";
        if (readInputAsArray.length > 1) {
            task = readInputAsArray[1];
        }

        while (!(command.equals("Bye") || command.equals("bye"))) {
            if (command.equals("list")) {
                list_Input(tasks);
            } else if (command.equals("unmark")) {
                try {
                    unmark_Input(tasks, task);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! Which task do you want to unmark?");
                } catch (IOException i) {
                    System.out.println("An error occurred with txt file");
                }
            } else if (command.equals("mark")) {
                try {
                    mark_Input(tasks, task);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! Which task do you want to mark?");
                } catch (IOException i) {
                    System.out.println("An error occurred with txt file");
                }
            } else if (command.equals("delete")) {
                try {
                    delete(tasks, task);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! Which task do you want to delete?");
                } catch (IOException i) {
                    System.out.println("An error occurred with txt file");
                }
            } else if (command.equals("todo")) {
                try {
                    todo_Input(tasks, task);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                } catch (IOException i) {
                    System.out.println("An error occurred with txt file");
                }
            } else if (command.equals("deadline")) {
                try {
                    deadline_Input(tasks, task);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                } catch (IOException i) {
                    System.out.println("An error occurred with txt file");
                }
            } else if (command.equals("event")) {
                try {
                    event_Input(tasks, task);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                } catch (IOException i) {
                    System.out.println("An error occurred with txt file");
                }
            } else if (command.equals("find")) {
                try {
                    ArrayList<Task> tasksFound = find_Input(tasks, task);
                    list_Input(tasksFound);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!!  What are you trying to find?");
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

    private static ArrayList<Task> find_Input(ArrayList<Task> tasks, String task) throws DukeException {
        if (task == " ") {
            throw new DukeException();
        }
        return (ArrayList<Task>) tasks.stream()
                .filter(t -> t.description.contains(task))
                .collect(toList());

    }

    private static void event_Input(ArrayList<Task> tasks, String task) throws DukeException, IOException {
        if (task == " ") {
            throw new DukeException();
        }
        System.out.println("Got it. I've added this task:");
        String taskAsArray[] = task.split("/");
        Event obj = new Event(taskAsArray[0], taskAsArray[1], taskAsArray[2]);
        tasks.add(obj); //.toString is automatically called;
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        FileHandling.saveContents();
    }

    private static void deadline_Input(ArrayList<Task> tasks, String task) throws DukeException, IOException {
        if (task == " ") {
            throw new DukeException();
        }
        System.out.println("Got it. I've added this task:");
        String taskAsArray[] = task.split("/");
        Deadline obj = new Deadline(taskAsArray[0], taskAsArray[1]);
        tasks.add(obj);
        System.out.println(obj); //.toString is automatically called;
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        FileHandling.saveContents();

    }

    private static void todo_Input(ArrayList<Task> tasks, String task) throws DukeException, IOException {
        if (task == " ") {
            throw new DukeException();
        }
        System.out.println("Got it. I've added this task:");
        Todo obj = new Todo(task);
        tasks.add(obj);
        System.out.println(obj); //.toString is automatically called;
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        FileHandling.saveContents();

    }

    private static void mark_Input(ArrayList<Task> tasks, String task) throws DukeException, IOException {
        if (task == " ") {
            throw new DukeException();
        }
        int taskNoInt = Integer.parseInt(task);
        tasks.get(taskNoInt - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNoInt - 1).toString());
        FileHandling.saveContents();
    }

    private static void unmark_Input(ArrayList<Task> tasks, String task) throws DukeException, IOException {
        if (task == " ") {
            throw new DukeException();
        }
        int taskNoInt = Integer.parseInt(task);
        tasks.get(taskNoInt - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNoInt - 1).toString());
        FileHandling.saveContents();
    }

    private static void delete(ArrayList<Task> tasks, String task) throws DukeException, IOException {
        if (task == " ") {
            throw new DukeException();
        }
        int taskNoInt = Integer.parseInt(task);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(taskNoInt - 1).toString());
        tasks.remove(taskNoInt - 1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        FileHandling.saveContents();
    }

    private static void list_Input(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + "." + task.toString());
            ++index;
        }
    }
}
