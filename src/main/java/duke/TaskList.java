package duke;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.TaskToMarkDoesNotExistException;
import duke.exceptions.UnknownCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    private static final String ADDED_COMMAND = "Got it. I've added this task:";
    private static final String REMOVED_COMMAND = "Noted. I've removed this task:";

    public static void displayList() {
        if (tasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i += 1) {
                System.out.print(i + 1 + ". ");
                tasks.get(i).printTask();
            }
        } else {
            System.out.println("You currently don't have any tasks in your list");
        }
    }

    public static void markTask(String task) {
        tasks.get(Integer.parseInt(task) - 1).setDone();
        System.out.println("Nice! I've marked this task as done:");
        TaskList.displayList();
    }

    public static void unmarkTask(String task) {
        tasks.get(Integer.parseInt(task) - 1).setUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        TaskList.displayList();
    }

    public static void createTodo(String task) {
        Task todoToAdd = new Todo(task);
        tasks.add(todoToAdd);
        System.out.println(ADDED_COMMAND);
        tasks.get(tasks.size() - 1).printTask();
        Ui.printNumberOfTasks(tasks);
    }

    public static void createEvent(String task) {
        String[] input = task.split("/from");
        String description = input[0];
        String[] period = input[1].split("/to");
        String start = period[0];
        String end = period[1];
        Task eventToAdd = new Event(description, start, end);
        tasks.add(eventToAdd);
        System.out.println(ADDED_COMMAND);
        tasks.get(tasks.size() - 1).printTask();
        Ui.printNumberOfTasks(tasks);
    }

    public static void createDeadline(String task) {
        String[] words = task.split("/by");
        String description = words[0];
        String end = words[1];
        Task deadlineToAdd = new Deadline(description, end);
        tasks.add(deadlineToAdd);
        System.out.println(ADDED_COMMAND);
        tasks.get(tasks.size() - 1).printTask();
        Ui.printNumberOfTasks(tasks);
    }

    public static void deleteTask(String task) {
        Task taskToDelete = tasks.get(Integer.parseInt(task) - 1);
        tasks.remove(Integer.parseInt(task) - 1);
        System.out.println(REMOVED_COMMAND);
        taskToDelete.printTask();
        Ui.printNumberOfTasks(tasks);
    }

    public static void editList() throws UnknownCommandException, EmptyDescriptionException, TaskToMarkDoesNotExistException {
        String[] splitText = Ui.getInput();
        while (!splitText[0].equals("bye")) {
            switch (splitText[0]) {
            case "mark":
                try {
                    TaskList.markTask(splitText[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDescriptionException("marked");
                } catch (NullPointerException e) {
                    throw new TaskToMarkDoesNotExistException("mark");
                }
                break;
            case "unmark":
                try {
                    TaskList.unmarkTask(splitText[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDescriptionException("unmarked");
                } catch (NullPointerException e) {
                    throw new TaskToMarkDoesNotExistException("unmark");
                }
                break;
            case "list":
                TaskList.displayList();
                break;
            case "todo":
                try {
                    TaskList.createTodo(splitText[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDescriptionException("todo");
                }
                break;
            case "deadline":
                try {
                    TaskList.createDeadline(splitText[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDescriptionException("deadline");
                }
                break;
            case "event":
                try {
                    TaskList.createEvent(splitText[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDescriptionException("event");
                }
                break;
            case "delete":
                try {
                    TaskList.deleteTask(splitText[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyDescriptionException("deleted");
                } catch (IndexOutOfBoundsException e) {
                    throw new TaskToMarkDoesNotExistException("delete");
                }
                break;
            default:
                throw new UnknownCommandException();
            }
            splitText = Ui.getInput();
        }
        try {
            Storage.updateFile(tasks);
        } catch (IOException e){
            System.out.println("Error updating data in file");
        }
        Ui.printBye();
    }
}
