package task;

import exception.CorruptedStoreException;
import exception.EmptyListException;
import exception.InvalidArgumentException;
import utility.Storage;
import genesis.Parser;
import utility.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

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

    public void list() throws EmptyListException {
        if (tasks.size() < 1) {
            throw new EmptyListException();
        }

        Ui.listTasks(tasks);
    }

    public void addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);

        Ui.onTaskAdded(todo.getListDescription(), tasks.size());
    }

    public void addDeadline(String content) throws InvalidArgumentException {
        String[] parts = Parser.formatDeadline(content);

        String description = parts[0];
        String by = parts[1];

        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);

        Ui.onTaskAdded(deadline.getListDescription(), tasks.size());
    }

    public void addEvent(String content) throws InvalidArgumentException {
        String[] parts = Parser.formatEvent(content);

        String description = parts[0];
        String from = parts[1];
        String to = parts[2];

        Event event = new Event(description, from, to);
        tasks.add(event);

        Ui.onTaskAdded(event.getListDescription(), tasks.size());
    }

    public void deleteTask(String content) {
        int index = Parser.extractIndex(content);
        Task task = tasks.remove(index);

        Ui.onTaskDelete(task.getListDescription(), tasks.size());
    }

    public void markTask(String content) {
        int index = Parser.extractIndex(content);
        Task task = tasks.get(index);
        task.setIsDone(true);

        Ui.onTaskMarked(task.getListDescription());
    }

    public void unmarkTask(String content) {
        int index = Parser.extractIndex(content);

        Task task = tasks.get(index);
        task.setIsDone(false);

        Ui.onTaskUnmarked(task.getListDescription());
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


}
