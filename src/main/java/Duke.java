import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.TaskToMarkDoesNotExistException;
import duke.exceptions.UnknownCommandException;

import java.util.Scanner;

public class Duke {
    private static void displayList(Task[] tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.numberOfTasks; i += 1) {
            System.out.print(i + 1 + ". ");
            tasks[i].printTask();
        }
    }

    private static void markTask(Task[] tasks, String task) {
        tasks[Integer.parseInt(task) - 1].setDone();
        System.out.println("Nice! I've marked this task as done:");
        displayList(tasks);
    }

    private static void unmarkTask(Task[] tasks, String task) {
        tasks[Integer.parseInt(task) - 1].setUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        displayList(tasks);
    }

    private static void createTodo(Task[] tasks, String task) {
        tasks[Task.numberOfTasks] = new Todo(task);
        System.out.println("Got it. I've added this task:");
        tasks[Task.numberOfTasks - 1].printTask();
        System.out.println("Now you have " + Task.numberOfTasks + " tasks in your list.");
    }

    private static void createEvent(Task[] tasks, String task) {
        String[] words = task.split("/from");
        String description = words[0];
        String[] words2 = words[1].split("/to");
        String start = words2[0];
        String end = words2[1];
        tasks[Task.numberOfTasks] = new Event(description, start, end);
        System.out.println("Got it. I've added this task:");
        tasks[Task.numberOfTasks - 1].printTask();
        System.out.println("Now you have " + Task.numberOfTasks + " tasks in your list.");
    }

    private static void createDeadline(Task[] tasks, String task) {
        String[] words = task.split("/by");
        String description = words[0];
        System.out.println(words[0]);
        System.out.println(words[1]);
        String end = words[1];
        tasks[Task.numberOfTasks] = new Deadline(description, end);
        System.out.println("Got it. I've added this task:");
        tasks[Task.numberOfTasks - 1].printTask();
        System.out.println("Now you have " + Task.numberOfTasks + " tasks in your list.");
    }

    private static String[] getInput() {
        Scanner input = new Scanner(System.in);
        String text = input.nextLine(); // input the whole sentence into text
        return text.split(" ", 2);
    }

    private static void editList() throws UnknownCommandException, EmptyDescriptionException, TaskToMarkDoesNotExistException {
        String[] splitText = getInput();
        Task[] tasks = new Task[100];
        Task.numberOfTasks = 0;
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
                    throw new EmptyDescriptionException("deadline");
                }
                break;
            default:
                throw new UnknownCommandException();
            }
            splitText = getInput();
        }
        System.out.println("Bye! Hope to see you again soon!");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        try {
            editList();
        } catch (UnknownCommandException e) {
            System.out.println("OOPS!! I'm sorry but I don't know what that means :-(");
        } catch (EmptyDescriptionException e) {
            e.printErrorMessage();
        } catch (TaskToMarkDoesNotExistException e) {
            e.printErrorMessage();
        }
    }
}