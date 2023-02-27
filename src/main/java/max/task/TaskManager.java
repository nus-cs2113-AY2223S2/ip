package max.task;

import max.Ui.Ui;
import max.command.Command;
import max.data.Storage;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private ArrayList<Task> tasks;
    private Ui ui;

    // String literals definitions
    private static final String ERROR_MAKE_TASK_FAILED = "Throw me a bone here, I couldn't create a task!";
    private static final String ERROR_NO_TASKS = "There's nothing in your list. I'm gonna bite you.";
    private static final String ERROR_MISSING_NO = "I'm a dog, but even I know that you didn't enter a number.";
    private static final String ERROR_BAD_TASK_NUM = "Invalid task number!";
    private static final String TASK_TODO = "todo";
    private static final String TASK_DEADLINE = "deadline";
    private static final String TASK_EVENT = "event";
    private static final String TASK_EVENT_FROM = "from";
    private static final String TASK_EVENT_TO = "to";
    private static final String TASK_DEADLINE_BY = "by";
    private static final String MESSAGE_ADD_TASK = "Got it. Task added:";
    private static final String MESSAGE_CURR_TASK = "You now have ";
    private static final String MESSAGE_CURR_TASK_LEFT = " tasks in your list.";
    private static final String MESSAGE_LIST_HEADER = "Here's what's in your list:";
    private static final String MESSAGE_MARK_DONE = "Okay, marking this task as done: ";
    private static final String MESSAGE_MARK_UNDONE = "Okay, setting this task as undone: ";
    private static final String MESSAGE_REMOVE_TASK = "Woof woof this task will be rem-woofed:";


    public void createTask(HashMap<String, String> commandMap, Command command) throws TaskException {
        // Assertion: commandMap has the correct subcommands & length
        Task newTask = null;
        if (command.equals(Command.TASK_TODO)) {
            // To-do task
            String description = commandMap.get(TASK_TODO);
            newTask = new Todo(description);
        } else if (command.equals(Command.TASK_DEADLINE)) {
            // Deadline task
            String description = commandMap.get(TASK_DEADLINE);
            String deadline = commandMap.get(TASK_DEADLINE_BY);
            newTask = new Deadline(description, deadline);
        } else if (command.equals(Command.TASK_EVENT)) {
            // Event task
            String description = commandMap.get(TASK_EVENT);
            String from = commandMap.get(TASK_EVENT_FROM);
            String to = commandMap.get(TASK_EVENT_TO);
            newTask = new Event(description, from, to);
        }
        if (newTask == null) {
            // Safety check in case the assertion fails
            throw new TaskException(ERROR_MAKE_TASK_FAILED);
        }
        tasks.add(newTask);
        ui.printMessage(MESSAGE_ADD_TASK);
        ui.printMessage(newTask.getDescription());
        ui.printMessage(MESSAGE_CURR_TASK + tasks.size() + MESSAGE_CURR_TASK_LEFT);
    }

    public void printTasklist() {
        if (tasks.size() == 0) {
            ui.printMessage(ERROR_NO_TASKS);
            return;
        }
        ui.printMessage(MESSAGE_LIST_HEADER);
        for (int i = 0; i < tasks.size(); ++i) {
            // Print number, box, description in that order
            Task curr = tasks.get(i);
            ui.printMessage(i + 1 + ". " + curr.getDescription() + ui.getNewline());
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
            throw new TaskException(ERROR_MISSING_NO);
        }
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new TaskException(ERROR_BAD_TASK_NUM);
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
            ui.printMessage(MESSAGE_MARK_UNDONE);
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
        ui.printMessage(MESSAGE_CURR_TASK + tasks.size() + MESSAGE_CURR_TASK_LEFT);
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

    public TaskManager() {
        tasks = new ArrayList<>();
        ui = new Ui();
    }
}
