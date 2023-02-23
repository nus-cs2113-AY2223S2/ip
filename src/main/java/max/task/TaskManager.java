package max.task;

import max.Ui.Ui;
import max.command.Command;
import max.data.PersistentDataHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private ArrayList<Task> tasks;
    private Ui ui;

    public void createTask(HashMap<String, String> commandMap, Command command) throws TaskException {
        // Assertion: commandMap has the correct subcommands & length
        Task newTask = null;
        if (command.equals(Command.TASK_TODO)) {
            // To-do task
            String description = commandMap.get("todo");
            newTask = new Todo(description);
        } else if (command.equals(Command.TASK_DEADLINE)) {
            // Deadline task
            String description = commandMap.get("deadline");
            String deadline = commandMap.get("by");
            newTask = new Deadline(description, deadline);
        } else if (command.equals(Command.TASK_EVENT)) {
            // Event task
            String description = commandMap.get("event");
            String from = commandMap.get("from");
            String to = commandMap.get("to");
            newTask = new Event(description, from, to);
        }
        if (newTask == null) {
            // Safety check in case the assertion fails
            throw new TaskException("Throw me a bone here, I couldn't create a task!");
        }
        tasks.add(newTask);
        ui.printMessage("Got it. Task added:");
        ui.printMessage(newTask.getDescription());
        ui.printMessage("You now have " + tasks.size() + " tasks in your list.");
    }

    public void printTasklist() {
        if (tasks.size() == 0) {
            ui.printMessage("There's nothing in your list. I'm gonna bite you.");
            return;
        }
        ui.printMessage("Here's what's in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            // Print number, box, description in that order
            Task curr = tasks.get(i);
            ui.printMessage(i + 1 + ". " + curr.getDescription() + '\n');
        }
    }

    // Takes in a string representing the 1-indexed task number
    // Checks for string validity and range validity
    // Returns the 0-indexed task number value
    private int convertTaskNumberString(String taskNumString) throws TaskException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(taskNumString) - 1; // Convert to 0-idx
        } catch (NumberFormatException exception) {
            throw new TaskException("I'm a dog, but even I know that you didn't enter a number.");
        }
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new TaskException("Invalid task number!");
        }
        return taskNum;
    }

    public void markTask(String taskNumString, boolean isDone) {
        int taskNum;
        try {
            taskNum = convertTaskNumberString(taskNumString);
        } catch (TaskException exception) {
            ui.printError(exception.getMessage());
            return;
        }

        // Update the task's done status
        if (isDone) {
            tasks.get(taskNum).markAsDone();
            ui.printMessage("Okay, marking this task as done: ");
        } else {
            tasks.get(taskNum).markAsUndone();
            ui.printMessage("Okay, setting this task as undone: ");
        }
        ui.printMessage(tasks.get(taskNum).getDescription());
    }

    public void deleteTask(String taskNumString) {
        int taskNum;
        try {
            taskNum = convertTaskNumberString(taskNumString);
        } catch (TaskException exception) {
            ui.printMessage(exception.getMessage());
            return;
        }
        // Assertion: taskNum is in tasklist range, guaranteed by convertTaskNumStr()
        ui.printMessage("Woof woof this task will be rem-woofed:");
        ui.printMessage(tasks.get(taskNum).getDescription());
        tasks.remove(taskNum);
        ui.printMessage("You have have " + tasks.size() + " tasks left.");
    }

    public void loadData() {
        PersistentDataHandler dataHandler = new PersistentDataHandler();
        this.tasks = dataHandler.loadTasksFromDisk();
    }

    public void saveData() {
        PersistentDataHandler dataHandler = new PersistentDataHandler();
        dataHandler.saveTasksToDisk(tasks);
    }

    public void resetTaskList() {
        tasks.clear();
    }

    public TaskManager() {
        tasks = new ArrayList<>();
        ui = new Ui();
    }
}
