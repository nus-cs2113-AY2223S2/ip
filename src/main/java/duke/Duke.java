package duke;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.TaskToMarkDoesNotExistException;
import duke.exceptions.UnknownCommandException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static void displayList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.print(i + 1 + ". ");
            tasks.get(i).printTask();
        }
    }

    private static void markTask(ArrayList<Task> tasks, String task) {
        tasks.get(Integer.parseInt(task) - 1).setDone();
        System.out.println("Nice! I've marked this task as done:");
        displayList(tasks);
    }

    private static void unmarkTask(ArrayList<Task> tasks, String task) {
        tasks.get(Integer.parseInt(task) - 1).setUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        displayList(tasks);
    }

    private static void createTodo(ArrayList<Task> tasks, String task) {
        Task todoToAdd = new Todo(task);
        tasks.add(todoToAdd);
        System.out.println("Got it. I've added this task:");
        tasks.get(tasks.size() - 1).printTask();
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }

    private static void createEvent(ArrayList<Task> tasks, String task) {
        String[] words = task.split("/from");
        String description = words[0];
        String[] words2 = words[1].split("/to");
        String start = words2[0];
        String end = words2[1];
        Task eventToAdd = new Event(description, start, end);
        tasks.add(eventToAdd);
        System.out.println("Got it. I've added this task:");
        tasks.get(tasks.size() - 1).printTask();
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }

    private static void createDeadline(ArrayList<Task> tasks, String task) {
        String[] words = task.split("/by");
        String description = words[0];
        String end = words[1];
        Task deadlineToAdd = new Deadline(description, end);
        tasks.add(deadlineToAdd);
        System.out.println("Got it. I've added this task:");
        tasks.get(tasks.size() - 1).printTask();
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }

    private static void deleteTask(ArrayList<Task> tasks, String task) {
        Task taskToDelete = tasks.get(Integer.parseInt(task) - 1);
        tasks.remove(Integer.parseInt(task) - 1);
        System.out.println("Noted. I've removed this task:");
        taskToDelete.printTask();
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }

    private static String[] getInput() {
        Scanner input = new Scanner(System.in);
        String text = input.nextLine(); // input the whole sentence into text
        return text.split(" ", 2);
    }

    private static void editList(ArrayList<Task> tasks) throws UnknownCommandException, EmptyDescriptionException, TaskToMarkDoesNotExistException {
        String[] splitText = getInput();
        while (!splitText[0].equals("bye")) {
            switch (splitText[0]) {
            case "mark":
                try {
                    markTask(tasks, splitText[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDescriptionException("marked");
                } catch (NullPointerException e) {
                    throw new TaskToMarkDoesNotExistException("mark");
                }
                break;
            case "unmark":
                try {
                    unmarkTask(tasks, splitText[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDescriptionException("unmarked");
                } catch (NullPointerException e) {
                    throw new TaskToMarkDoesNotExistException("unmark");
                }
                break;
            case "list":
                displayList(tasks);
                break;
            case "todo":
                try {
                    createTodo(tasks, splitText[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDescriptionException("todo");
                }
                break;
            case "deadline":
                try {
                    createDeadline(tasks, splitText[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDescriptionException("deadline");
                }
                break;
            case "event":
                try {
                    createEvent(tasks, splitText[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDescriptionException("event");
                }
                break;
            case "delete":
                try {
                    deleteTask(tasks, splitText[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDescriptionException("deleted");
                } catch (IndexOutOfBoundsException e) {
                    throw new TaskToMarkDoesNotExistException("delete");
                }
                break;
            default:
                throw new UnknownCommandException();
            }
            splitText = getInput();
        }
        try {
            Storage.updateFile(tasks);
        } catch (IOException e){
            System.out.println("Error updating data in file");
        }
        System.out.println("Bye! Hope to see you again soon!");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks = Storage.getData();
        } catch (IOException e) {
            System.out.println("Error obtaining data from file");
        }
        try {
            editList(tasks);
        } catch (UnknownCommandException e) {
            System.out.println("OOPS!! I'm sorry but I don't know what that means :-(");
        } catch (EmptyDescriptionException e) {
            e.printErrorMessage();
        } catch (TaskToMarkDoesNotExistException e) {
            e.printErrorMessage();
            System.out.println("You only have " + tasks.size() + " tasks in your list.");
        }
    }
}