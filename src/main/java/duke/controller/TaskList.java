package duke.controller;

import duke.exception.InvalidCommandException;
import duke.model.*;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public Task handleUnmarkTask(Command command) {
        String outputMessage;
        int taskIndex;
        taskIndex = Integer.parseInt(command.getPayload().getData()[0]) - 1;
        Task unmarkedTask = tasks.get(taskIndex);
        unmarkedTask.unmarkAsDone();
        return unmarkedTask;
    }

    public int getTasksNumber() {
        return tasks.size();
    }


    public Task handleDeleteTask(String[] payloadData) throws InvalidCommandException {
        String outputMessage;
        int removedIndex = Integer.parseInt(payloadData[0]);
        Task removedTask = tasks.get(removedIndex - 1);
        tasks.remove(removedIndex - 1);
        return removedTask;
    }

    public Event handleAddEvent(String[] payloadData) throws InvalidCommandException {
        String outputMessage;
        Event newEvent = new Event(payloadData);
        tasks.add(newEvent);
        return newEvent;
    }

    public Deadline handleAddDeadline(String[] payloadData) throws InvalidCommandException {
        String outputMessage;
        Deadline newDeadline = new Deadline(payloadData);
        tasks.add(newDeadline);
        return newDeadline;
    }

    public ToDo handleAddTodo(String[] payloadData) throws InvalidCommandException {
        String outputMessage;
        ToDo newTodo = new ToDo(payloadData);
        tasks.add(newTodo);
        return newTodo;
    }

    public Task handleAddTask(String[] payloadData) throws InvalidCommandException {
        String outputMessage;
        Task newTask = new Task(payloadData);
        tasks.add(newTask);
        return newTask;
    }

    public Task handleMarkTask(Command command) {
        int taskIndex;
        String outputMessage;
        taskIndex = Integer.parseInt(command.getPayload().getData()[0]) - 1;
        Task markedTask = tasks.get(taskIndex);
        markedTask.markAsDone();
        return markedTask;
    }

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
}
