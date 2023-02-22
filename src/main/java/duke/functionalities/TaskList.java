package duke.functionalities;

import java.util.ArrayList;
import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class TaskList {

    protected ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void addTodo(String todo) {
        tasks.add(new Todo(todo));
        int taskCount = getTaskIndex(todo);
        ui.showTaskAdded(tasks, taskCount);
    }

    public void addEvent(String event, String eventStart, String eventEnd) {
        tasks.add(new Event(event, eventStart, eventEnd));
        int taskCount = getTaskIndex(event);
        ui.showTaskAdded(tasks, taskCount);
    }

    public void addDeadline(String deadline, String dueDate) {
        tasks.add(new Deadline(deadline, dueDate));
        int taskCount = getTaskIndex(deadline);
        ui.showTaskAdded(tasks, taskCount);
    }

    public void deleteTask(String task) throws DukeException {
        try {
            int index = Integer.parseInt(task);
            String description = tasks.get(index - 1).toString();
            ui.showTaskDeleted(tasks, description);
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" The task to delete does not exist in the list!");
        } catch (NumberFormatException e) {
            throw new DukeException(" Task index should be an integer!");
        }
    }

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

    public void printAllTasks() {
        ui.showUserMessage(" Here are the tasks in your list:");
        int index = 1;
        for (Task userTask : tasks) {
            ui.showUserMessage("  " + index + "." + userTask.toString());
            index++;
        }
    }

    public void markTaskAsNotComplete(String task) throws DukeException {
        try {
            int index = Integer.parseInt(task);
            tasks.get(index - 1).setTaskStatus(false);
            ui.showUserMessage(" Okay, I've marked this task as not done yet:");
            ui.showUserMessage(tasks.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" The task to unmark does not exist in the list!");
        } catch (NumberFormatException e) {
            throw new DukeException(" Task index should be an integer!");
        }
    }

    public void markTaskAsComplete(String task) throws DukeException {
        try {
            int index = Integer.parseInt(task);
            tasks.get(index - 1).setTaskStatus(true);
            ui.showUserMessage(" Nice! I've marked this task as done:");
            ui.showUserMessage(tasks.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" The task to mark does not exist in the list!");
        } catch (NumberFormatException e) {
            throw new DukeException(" Task index should be an integer!");
        }
    }
}