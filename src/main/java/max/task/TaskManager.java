package max.task;

import max.Ui.Ui;
import max.command.Command;
import max.data.Storage;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private ArrayList<Task> tasks;
    private Ui ui;

    private static String MESSAGE_MAX_CANNOT_FIND = "Max dug around everywhere but can't find anything!";
    private static String MESSAGE_MAX_HAS_FOUND = "Max sniffed out these matching tasks:";
    private static String MESSAGE_EMPTY_QUERY = "I'm gonna bite you. Your query is empty!";
    private static String MESSAGE_REMOVE_TASK = "Woof woof this task will be rem-woofed:";
    private static String MESSAGE_MARK_DONE = "Okay, marking this task as done: ";
    private static String MESSAGE_UNMARK_DONE = "Okay, setting this task as undone: ";
    private static String MESSAGE_SHOW_LIST = "Here's what's in your list:";
    private static String MESSAGE_LIST_EMPTY = "There's nothing in your list. I'm gonna bite you.";
    private static String MESSAGE_ADD_TASK = "Got it. Task added:";
    private static String EXCEPTION_INVALID_NUMBER = "Invalid task number!";
    private static String EXCEPTION_NOT_A_NUMBER = "I'm a dog, but even I know that you didn't enter a number.";
    private static String EXCEPTION_ADD_TASK_FAILED = "Throw me a bone here, I couldn't create a task!";

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
            throw new TaskException(EXCEPTION_ADD_TASK_FAILED);
        }
        tasks.add(newTask);
        ui.printMessage(MESSAGE_ADD_TASK);
        ui.printMessage(newTask.getDescription());
        ui.printMessage("You now have " + tasks.size() + " tasks in your list.");
    }

    public void printTasklist() {
        if (tasks.size() == 0) {
            ui.printMessage(MESSAGE_LIST_EMPTY);
            return;
        }
        ui.printMessage(MESSAGE_SHOW_LIST);
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
            throw new TaskException(EXCEPTION_NOT_A_NUMBER);
        }
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new TaskException(EXCEPTION_INVALID_NUMBER);
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
            ui.printMessage(MESSAGE_MARK_DONE);
        } else {
            tasks.get(taskNum).markAsUndone();
            ui.printMessage(MESSAGE_UNMARK_DONE);
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
        ui.printMessage(MESSAGE_REMOVE_TASK);
        ui.printMessage(tasks.get(taskNum).getDescription());
        tasks.remove(taskNum);
        ui.printMessage("You have have " + tasks.size() + " tasks left.");
    }

    public void loadData() {
        Storage dataHandler = new Storage();
        this.tasks = dataHandler.loadTasksFromDisk();
    }

    public void saveData() {
        Storage dataHandler = new Storage();
        dataHandler.saveTasksToDisk(tasks);
    }

    public void resetTaskList() {
        tasks.clear();
    }

    public void findTasks(HashMap<String, String> commandPayload) {
        // Naiively search for tasks that have exact matches with the query string
        String query;
        if (commandPayload.containsKey("find")) {
            query = commandPayload.get("find");
        } else {
            query = commandPayload.get("fetch");
        }
        if (query.trim().length() == 0) {
            ui.printMessage(MESSAGE_EMPTY_QUERY);
            return;
        }

        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            String taskDescription = task.getDescription();
            if (taskDescription.contains(query)) {
                matchedTasks.add(task);
            }
        }
        // Print all related items
        if (matchedTasks.size() == 0) {
            ui.printMessage(MESSAGE_MAX_CANNOT_FIND);
        } else {
            ui.printMessage(MESSAGE_MAX_HAS_FOUND);
            for (int i = 0; i < matchedTasks.size(); ++i) {
                Task curr = matchedTasks.get(i);
                ui.printMessage(i + 1 + ". " + curr.getDescription());
            }
        }

    }

    public TaskManager() {
        tasks = new ArrayList<>();
        ui = new Ui();
    }
}
