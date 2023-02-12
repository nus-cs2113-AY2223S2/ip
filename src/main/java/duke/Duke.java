package duke;

import duke.Deadline;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();
    //public static int taskCounter = 0;

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printWelcomeMessage() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    private static void printByeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printLine();
    }

    private static void markAsUndone(String input) throws DukeException {
        String taskNum = input.substring(input.indexOf(" ") + 1);
        int taskNumber = Integer.parseInt(taskNum);
        if (taskNumber > tasks.size()) {
            throw new DukeException("This task does not exist. Please provide a valid task index");
        } else {
            printLine();
            tasks.get(taskNumber - 1).markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.print("\t  ");
            System.out.println(tasks.get(taskNumber - 1));
            printLine();
        }
    }

    private static void markAsDone(String input) throws DukeException {
        String taskNum = input.substring(input.indexOf(" ") + 1);
        int taskNumber = Integer.parseInt(taskNum);
        if (taskNumber > tasks.size()) {
            throw new DukeException("This task does not exist. Please provide a valid task index");
        } else {
            printLine();
            tasks.get(taskNumber - 1).markDone();
            System.out.println("Great! I have marked this task as done:");
            System.out.print("\t  ");
            System.out.println(tasks.get(taskNumber - 1));
            printLine();
        }
    }

    private static void deleteTask(String input) throws DukeException {
        String taskNum = input.substring(input.indexOf(" ") + 1);
        int taskNumber = Integer.parseInt(taskNum);
        if (taskNumber > tasks.size()) {
            throw new DukeException("This task does not exist. Please provide a valid task index");
        } else {
            printLine();
            Task deletedTask = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.print("\t  ");
            System.out.println(deletedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in your list.");
            printLine();
        }
    }

    private static void printList() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("Sorry, there are no tasks in the list currently.");
        } else {
            printLine();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print("\t" + (i + 1) + ".");
                System.out.println(tasks.get(i));
            }
        }
        printLine();
    }

    private static void addTodo(String input) throws DukeException {
        String[] words = input.split(" ");
        if (words.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            Todo todo = new Todo(input.substring(5));
            tasks.add(todo);
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.print("\t  ");
            System.out.println(todo);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            printLine();
        }
    }

    private static void addDeadline(String input) throws DukeException {
        String[] words = input.split(" ");
        int index = input.indexOf("/");
        if (words.length < 2 || index == -1) {
            throw new DukeException("☹ OOPS!!! The description or date of a deadline cannot be empty.");
        } else {
            String[] wordDeadline = input.substring(9).split("/");
            Deadline deadline = new Deadline(wordDeadline[0].trim(), wordDeadline[1].substring(3));
            tasks.add(deadline);
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.print("\t  ");
            System.out.println(deadline);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            printLine();
        }
    }

    private static void addEvent(String input) throws DukeException {
        String[] words = input.split(" ");
        int index = input.indexOf("/");
        if (words.length < 2 || index == -1) {
            throw new DukeException("☹ OOPS!!! The description or time of an event cannot be empty.");
        } else {
            String[] wordEvent = input.substring(5).split("/", 3);
            Event event = new Event(wordEvent[0].trim(), wordEvent[1].substring(5), wordEvent[2].substring(3));
            tasks.add(event);
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.print("\t  ");
            System.out.println(event);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            printLine();
        }
    }


    private static void runDuke() {
        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();
        while (!input.equals("bye")) {
            try {
                String[] words = input.split(" ");
                switch (words[0]) {
                case "list":
                    printList();
                    break;

                case "mark":
                    markAsDone(input);
                    break;

                case "unmark":
                    markAsUndone(input);
                    break;

                case "todo":
                    addTodo(input);
                    break;

                case "deadline":
                    addDeadline(input);
                    break;

                case "event":
                    addEvent(input);
                    break;

                case "delete":
                    deleteTask(input);
                    break;

                default:
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
            input = in.nextLine();
        }
    }


    public static void main(String[] args) {
        printWelcomeMessage();
        runDuke();
        printByeMessage();
    }
}

