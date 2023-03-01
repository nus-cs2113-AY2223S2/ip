package duke.controller;

import duke.exception.InvalidCommandException;
import duke.model.Deadline;
import duke.model.Event;
import duke.model.Task;
import duke.model.ToDo;

import java.util.ArrayList;

/**
 * A class that manages a collection of tasks
 */
public class TaskList {
    /**
     * The collection of tasks
     */
    protected ArrayList<Task> tasks;

    /**
     * Constructor that initializes the collection of tasks
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Method that unmarks a task as done
     *
     * @param payloadData The payload data of the command
     * @return The unmarked task
     */
    public Task handleUnmarkTask(String[] payloadData) {
        int taskIndex = taskIndex = Integer.parseInt(payloadData[0]) - 1;
        Task unmarkedTask = tasks.get(taskIndex);
        unmarkedTask.unmarkAsDone();
        return unmarkedTask;
    }

    /**
     * Method that marks a task as done
     *
     * @param payloadData The payload data of the command
     * @return The marked task
     */
    public Task handleMarkTask(String[] payloadData) {
        int taskIndex = Integer.parseInt(payloadData[0]) - 1;
        Task markedTask = tasks.get(taskIndex);
        markedTask.markAsDone();
        return markedTask;
    }

    /**
     * Method that returns the number of tasks of a taskList
     *
     * @return The number of tasks
     */
    public int getTasksNumber() {
        return tasks.size();
    }

    /**
     * A method that deletes a specific task
     *
     * @param payloadData THe payload data of the command
     * @return The deleted task
     * @throws InvalidCommandException If the information provided is insufficient
     */
    public Task handleDeleteTask(String[] payloadData) throws InvalidCommandException {
        String outputMessage;
        int removedIndex = Integer.parseInt(payloadData[0]);
        Task removedTask = tasks.get(removedIndex - 1);
        tasks.remove(removedIndex - 1);
        return removedTask;
    }

    /**
     * Method that add an event to tasks
     *
     * @param payloadData The payload data of the command
     * @return The added Event
     * @throws InvalidCommandException If the information provided is insufficient
     */
    public Event handleAddEvent(String[] payloadData) throws InvalidCommandException {
        Event newEvent = new Event(payloadData);
        tasks.add(newEvent);
        return newEvent;
    }

    /**
     * Method that add a deadline to tasks
     *
     * @param payloadData The payload data of the command
     * @return The added Deadline
     * @throws InvalidCommandException If the information provided is insufficient
     */
    public Deadline handleAddDeadline(String[] payloadData) throws InvalidCommandException {
        Deadline newDeadline = new Deadline(payloadData);
        tasks.add(newDeadline);
        return newDeadline;
    }

    /**
     * Method that add a todo to tasks
     *
     * @param payloadData The payload data of the command
     * @return The added ToDo
     * @throws InvalidCommandException If the information provided is insufficient
     */
    public ToDo handleAddTodo(String[] payloadData) throws InvalidCommandException {
        ToDo newTodo = new ToDo(payloadData);
        tasks.add(newTodo);
        return newTodo;
    }

    /**
     * Method that add a task to tasks
     *
     * @param payloadData The payload data of the command
     * @return The added Task
     * @throws InvalidCommandException If the information provided is insufficient
     */
    public Task handleAddTask(String[] payloadData) throws InvalidCommandException {
        String outputMessage;
        Task newTask = new Task(payloadData);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Method that converts a collection of tasks entity into its spring representation
     *
     * @return The spring representation of tasks
     */
    public String toString() {
        int size = this.getTasksNumber();
        if (size == 0) {
            return "Task is empty...";
        }
        String taskListString = "";
        int numberOfTasks = size;
        for (int i = 0; i < numberOfTasks; i++) {
            taskListString += String.format("%3d. ", (i + 1)) + tasks.get(i).toString();
            if (i < numberOfTasks - 1) {
                taskListString += System.lineSeparator() + "\t";
            }
        }
        return taskListString;
    }

    public ArrayList<Task> getSearchedTasks(String keyword) {
        ArrayList<Task> searchedTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.getTaskName().contains(keyword)) {
                searchedTasks.add(task);
            }
        }
        return searchedTasks;
    }
}
