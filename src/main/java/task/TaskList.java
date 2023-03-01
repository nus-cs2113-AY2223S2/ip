package task;

import exception.CorruptedStoreException;
import exception.EmptyListException;
import exception.InvalidArgumentException;
import exception.KeywordNotFoundException;
import utility.Storage;
import genesis.Parser;
import utility.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object and loads tasks from the storage file if available.
     * If storage file is not available or is corrupted, initializes an empty task list.
     */
    public TaskList() {
        try {
            this.tasks = Storage.loadFromFile();
            System.out.println("Task list loaded successfully.");
        } catch (IOException e) {
            this.tasks = new ArrayList<>();
            System.out.println("There is no existing task list found. Initializing new task list.");
        } catch (IndexOutOfBoundsException | CorruptedStoreException e) {
            this.tasks = new ArrayList<>();
            System.out.println("Task list is corrupted. Initializing new task list.");
        } finally {
            Ui.breakLine();
        }
    }

    /**
     * Prints the list of tasks.
     *
     * @throws EmptyListException If there are no tasks in the task list.
     */
    public void list() throws EmptyListException {
        if (tasks.size() < 1) {
            throw new EmptyListException();
        }

        Ui.listTasks(tasks);
    }

    /**
     * Adds a new Todo task to the task list with the specified description.
     *
     * @param description The description of the todo task.
     */
    public void addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);

        Ui.onTaskAdded(todo.getListDescription(), tasks.size());
    }

    /**
     * Adds a new Deadline task to the task list with the specified content.
     * Parses the content into a description and a deadline date/time.
     *
     * @param content The description and deadline of the task in a specific format.
     * @throws InvalidArgumentException If the input format is invalid.
     */
    public void addDeadline(String content) throws InvalidArgumentException {
        String[] parts = Parser.formatDeadline(content);

        String description = parts[0];
        String by = parts[1];

        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);

        Ui.onTaskAdded(deadline.getListDescription(), tasks.size());
    }

    /**
     * Adds a new Event task to the task list with the specified content.
     * Parses the content into a description and a start/end date/time.
     *
     * @param content The description and time frame of the event in a specific format.
     * @throws InvalidArgumentException If the input format is invalid.
     */
    public void addEvent(String content) throws InvalidArgumentException {
        String[] parts = Parser.formatEvent(content);

        String description = parts[0];
        String from = parts[1];
        String to = parts[2];

        Event event = new Event(description, from, to);
        tasks.add(event);

        Ui.onTaskAdded(event.getListDescription(), tasks.size());
    }

    /**
     * Marks a task as done.
     *
     * @param content The index of the task to be marked as done.
     */
    public void markTask(String content) {
        int index = Parser.extractIndex(content);
        Task task = tasks.get(index);
        task.setIsDone(true);

        Ui.onTaskMarked(task.getListDescription());
    }

    /**
     * Marks a task as not done.
     *
     * @param content The index of the task to be marked as not done.
     */
    public void unmarkTask(String content) {
        int index = Parser.extractIndex(content);

        Task task = tasks.get(index);
        task.setIsDone(false);

        Ui.onTaskUnmarked(task.getListDescription());
    }

    /**
     * Deletes a task from the task list.
     *
     * @param content The index of the task to be deleted.
     */
    public void deleteTask(String content) {
        int index = Parser.extractIndex(content);
        Task task = tasks.remove(index);

        Ui.onTaskDelete(task.getListDescription(), tasks.size());
    }

    /**
     * This method finds a task in the list of tasks that matches the provided keyword and displays the matching tasks' descriptions.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @throws EmptyListException       if the task list is empty.
     * @throws KeywordNotFoundException if no tasks matching the keyword are found in the task list.
     * @throws InvalidArgumentException if the keyword is null or empty.
     */
    public void findTask(String keyword) throws EmptyListException, KeywordNotFoundException, InvalidArgumentException {

        boolean found = false;

        if (tasks.size() < 1) {
            throw new EmptyListException();
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().contains(keyword)) {
                if (!found) {
                    System.out.println("Here are the matching tasks in your list:");
                    found = true;
                }

                System.out.println((i + 1) + ". " + task.getListDescription());
            }
        }

        if (!found) {
            throw new KeywordNotFoundException();
        }

    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


}
