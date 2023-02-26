package duke.functionalities;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * This Class contains the Task List and deals with Task List Operations
 * */
public class TaskList {

    protected ArrayList<Task> tasks;
    private final Ui UI;

    /**
     * Retrieves the stored Task List from Duke Data File
     *
     * @param tasks The Task ArrayList
     * @param ui The User Interface Class
     * */
    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.UI = ui;
    }

    /**
     * Adds a Todo Task to the Task List
     *
     * @param todo The todo Task
     * */
    public void addTodo(String todo) {
        tasks.add(new Todo(todo));
        int taskCount = getTaskIndex(todo);
        UI.showTaskAdded(tasks, taskCount);
    }

    /**
     * Adds an Event Task to the Task List
     *
     * @param event The Event Description
     * @param eventStart The Event Start Date/Time
     * @param eventEnd The Event End Date/Time
     * */
    public void addEvent(String event, String eventStart, String eventEnd) {
        tasks.add(new Event(event, eventStart, eventEnd));
        int taskCount = getTaskIndex(event);
        UI.showTaskAdded(tasks, taskCount);
    }

    /**
     * Adds a Deadline Task to the Task List
     *
     * @param deadline The Deadline Description
     * @param dueDate The Deadline Due Date
     * */
    public void addDeadline(String deadline, String dueDate) {
        tasks.add(new Deadline(deadline, dueDate));
        int taskCount = getTaskIndex(deadline);
        UI.showTaskAdded(tasks, taskCount);
    }

    /**
     * Deletes the Task from the Task List
     *
     * @param task The Task to be deleted
     * @throws DukeException if there is an error encountered while deleting the Task
     * */
    public void deleteTask(String task) throws DukeException {
        try {
            int index = Integer.parseInt(task);
            String description = tasks.get(index - 1).toString();
            UI.showTaskDeleted(tasks, description);
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" Task to delete is not recognized!");
        } catch (NumberFormatException e) {
            throw new DukeException(" Task index should be an integer!");
        }
    }

    /**
     * Gets the Task Index from the Task List
     *
     * @param description The Task Description
     * @return The Task Index
     * */
    public int getTaskIndex(String description) {
        int index = 0;
        for (Task myObj : tasks) {
            if (description.equalsIgnoreCase(myObj.getDescription())) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Prints all the Tasks in the Task List
     * */
    public void printAllTasks() {
        UI.showUserMessage(" Here are the tasks in your list:");
        int index = 1;
        for (Task userTask : tasks) {
            UI.showUserMessage("  " + index + "." + userTask.toString());
            index++;
        }
    }

    /**
     * Marks the Task as Not Complete
     *
     * @param task the Task to mark as Not Complete
     * @throws DukeException if there is an error encountered when marking the Task as Not Complete
     * */
    public void markTaskAsNotComplete(String task) throws DukeException {
        try {
            int index = Integer.parseInt(task);
            tasks.get(index - 1).setTaskStatus(false);
            UI.showUserMessage(" Okay, I've marked this task as not done yet:");
            UI.showUserMessage(tasks.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" Task to unmark is not recognized!");
        } catch (NumberFormatException e) {
            throw new DukeException(" Task index should be an integer!");
        }
    }

    /**
     * Marks the Task as Complete
     *
     * @param task the Task to mark as Complete
     * @throws DukeException if there is an error encountered when marking the Task as Complete
     * */
    public void markTaskAsComplete(String task) throws DukeException {
        try {
            int index = Integer.parseInt(task);
            tasks.get(index - 1).setTaskStatus(true);
            UI.showUserMessage(" Nice! I've marked this task as done:");
            UI.showUserMessage(tasks.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" Task to mark is not recognized!");
        } catch (NumberFormatException e) {
            throw new DukeException(" Task index should be an integer!");
        }
    }

    /**
     * Finds the Matching Tasks in the Task List from the given Keyword
     *
     * @param keyword The Keyword
     * */
    public void findTask(String keyword) {
        UI.showUserMessage(" Here are the matching tasks in your list:");
        int index = 1;
        String searchKeyword = keyword.toLowerCase();
        for (Task userTask : tasks) {
            if (userTask.toString().contains(searchKeyword)) {
                UI.showUserMessage("  " + index + "." + userTask);
                index++;
            }
        }
    }
}