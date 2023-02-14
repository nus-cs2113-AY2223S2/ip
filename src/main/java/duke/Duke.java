package duke;

import duke.exceptions.ExcessInputsException;
import duke.exceptions.MissingDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String LINE = "    ____________________________________________________________\n";
    public static final String GREET = "     Hello! I'm Duke\n" +
            "     Welcome to Your To-do List!\n" +
            "     Enter \"todo 'task-name'\" to add a task.\n" +
            "     Enter \"deadline 'task-name' /by 'deadline'\" to add a task with a deadline.\n" +
            "     Enter \"event 'task-name' /from 'start-date' /to 'end-date'\" to add a task with start and end dates.\n" +
            "     Enter \"mark 'task-index'\" to mark a task as done.\n" +
            "     Enter \"unmark 'task-index'\" to mark a task as not done yet.\n" +
            "     Enter \"list\" to obtain a list of all your tasks!.\n";
    public static final String BYE = "     Bye. Hope to see you again soon!\n";
    public static final String NOT_DONE = "    OK :(, I've marked this task as not done yet: \n    ";
    public static final String DONE = "    Nice! I've marked this task as done: \n    ";
    public static final String ERROR = "    Invalid command! :( Check your input and try again! \n";

    private static void addEvent(ArrayList<Task> tasks, String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] description = words[1].split(" /from ");
        if (description.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] dates = description[1].split(" /to ");
        if (dates.length != 2) {
            throw new MissingDescriptionException();
        }
        Event event = new Event(description[0], "E", dates[0], dates[1]);
        tasks.add(event);
    }

    private static void addDeadline(ArrayList<Task> tasks, String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String[] description = words[1].split(" /by ");
        if (description.length != 2) {
            throw new MissingDescriptionException();
        }
        Deadline deadline = new Deadline(description[0], "D", description[1]);
        tasks.add(deadline);
    }

    private static void addTodo(ArrayList<Task> tasks, String[] words) throws MissingDescriptionException {
        if (words.length != 2) {
            throw new MissingDescriptionException();
        }
        String description = words[1];
        Task todo = new Task(description, "T");
        tasks.add(todo);
    }

    private static void deleteTask(ArrayList<Task> tasks, String[] words) {
        int index = Integer.parseInt(words[1]) - 1;
        System.out.print(LINE + "    Noted. I've removed this task:\n      " +
                tasks.get(index).toString());
        tasks.remove(index);
        System.out.print("\n    Now you have " + tasks.size() + " tasks in the list.\n" +
                LINE);
    }

    private static void editMarkStatus(ArrayList<Task> tasks, String[] words, String command) {
        int index = Integer.parseInt(words[1]) - 1;
        if (command.equals("unmark")) {
            tasks.get(index).setDone(false);
            System.out.print(LINE + NOT_DONE);
        } else {
            tasks.get(index).setDone(true);
            System.out.print(LINE + DONE);
        }
        System.out.print(tasks.get(index).toString() + "\n" + LINE);
    }

    private static void printList(ArrayList<Task> tasks, String[] words) throws ExcessInputsException {
        if (words.length > 1) {
            throw new ExcessInputsException();
        }
        System.out.println(LINE + "Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("    " + i + "." + tasks.get(i-1).toString());
        }
        System.out.print(LINE);
    }

    private static void processCommands(String line, Scanner in) {
        ArrayList<Task> tasks = new ArrayList<>();
        while (!line.equals("bye")) {
            String[] words = line.split(" ", 2);
            String command = words[0];
            if (command.equals("list")) {
                try {
                    printList(tasks, words);
                } catch (ExcessInputsException e) {
                    System.out.println(LINE + "Too many inputs! Please only enter \"list\"\n" + LINE);
                }
            } else if (command.contains("mark")) {
                try {
                    editMarkStatus(tasks, words, command);
                } catch (NumberFormatException e){
                    System.out.println(LINE + "Mark/Unmark must only be followed by the index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(LINE + "Missing/Invalid task index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                } catch (NullPointerException e) {
                    System.out.println(LINE + "Missing/Invalid task index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                }
            } else if (command.equals("delete")) {
                try {
                    deleteTask(tasks, words);
                } catch (NumberFormatException e){
                    System.out.println(LINE + "Delete must only be followed by the index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(LINE + "Missing/Invalid task index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                } catch (NullPointerException e) {
                    System.out.println(LINE + "Missing/Invalid task index number!\n" +
                            "Enter \"list\" to check the index.\n" + LINE);
                }
            } else {
                try {
                    if (command.equals("todo")) {
                        addTodo(tasks, words);
                    } else if (command.equals("deadline")) {
                        addDeadline(tasks, words);
                    } else if (command.equals("event")) {
                        addEvent(tasks, words);
                    } else {
                        System.out.print(LINE + ERROR + LINE);
                        line = in.nextLine();
                        continue;
                    }
                    System.out.print(LINE + "    Got it. I've added this task:\n      " +
                            tasks.get(tasks.size()-1).toString() + "\n    Now you have " + tasks.size() + " tasks in the list.\n" +
                            LINE);
                } catch (MissingDescriptionException e){
                    System.out.println(LINE + "Missing task description details.\n" +
                            "Please check the input format for adding events\n" + LINE);
                }
            }
            line = in.nextLine();
        }
    }

    private static void readInputs() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        processCommands(line, in);
    }

    public static void main(String[] args) {
        System.out.print(LINE + GREET + LINE);
        readInputs();
        System.out.print(LINE + BYE + LINE);
    }
}