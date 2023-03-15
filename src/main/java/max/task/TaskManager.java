package max.task;

import max.ui.Ui;
import max.command.Command;
import max.data.Storage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * TaskManager is the controller that contains and manages the user's task list
 * <p>
 * TaskManager helps with data management such as save/load
 * and modifying task state (e.g. deletion, marking as complete)
 */
public class TaskManager {
    private ArrayList<Task> tasks;
    private final Ui ui;
    private final DateParser dateParser;

    // String literals definitions
    private static final String ERROR_MISSING_NO = "I'm a dog, but even I know that you didn't enter a number.";
    private static final String ERROR_BAD_TASK_NUM = "Invalid task number!";
    private static final String ERROR_EMPTY_DESCRIPTION = "I'm a dog, but even I know you didn't enter anything.";
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
    private static final String MESSAGE_MAX_CANNOT_FIND = "Max dug around everywhere but can't find anything!";
    private static final String MESSAGE_MAX_HAS_FOUND = "Max sniffed out these matching tasks:";
    private static final String MESSAGE_EMPTY_QUERY = "I'm gonna bite you. Your query is empty!";
    private static final String MESSAGE_LIST_EMPTY = "There's nothing in your list. I'm gonna bite you.";
    private static final String EXCEPTION_ADD_TASK_FAILED = "Throw me a bone here, I couldn't create a task!";
    private static final String DOT_SPACE = ". ";
    private static final int ONE_OFFSET = 1;

    private String validateDescription(String description) throws TaskException {
        String formattedDescription = description.trim();
        if (formattedDescription.length() == 0) {
            throw new TaskException(ERROR_EMPTY_DESCRIPTION);
        }
        return formattedDescription;
    }

    /**
     * Creates a new to-do task
     *
     * @param commandMap argumentPayload map of the user input
     * @return a to-do task
     */
    private Task createTodo(HashMap<String, String> commandMap) throws TaskException {
        String description = commandMap.get(TASK_TODO);
        description = validateDescription(description);

        return new Todo(description);
    }

    /**
     * Creates a new Deadline task
     *
     * @param commandMap argumentPayload map of the user input
     * @return a deadline task
     */
    private Task createDeadline(HashMap<String, String> commandMap) throws TaskException {
        String description = commandMap.get(TASK_DEADLINE);
        description = validateDescription(description);

        String deadline = commandMap.get(TASK_DEADLINE_BY);
        deadline = dateParser.formatInputString(deadline);
        return new Deadline(description, deadline);
    }

    /**
     * Creates a new Event task
     *
     * @param commandMap argumentPayload map of the user input
     * @return an Event task
     */
    private Task createEvent(HashMap<String, String> commandMap) throws TaskException {
        String description = commandMap.get(TASK_EVENT);
        description = validateDescription(description);

        String from = commandMap.get(TASK_EVENT_FROM);
        String to = commandMap.get(TASK_EVENT_TO);
        // Format dates
        dateParser.validateToFromDates(to, from);
        from = dateParser.formatInputString(from);
        to = dateParser.formatInputString(to);
        return new Event(description, from, to);
    }

    private void printTaskAddedMessage(Task newTask) {
        ui.printMessage(MESSAGE_ADD_TASK);
        ui.printMessage(newTask.getDescription());
        ui.printMessage(MESSAGE_CURR_TASK + tasks.size() + MESSAGE_CURR_TASK_LEFT);
    }

    /**
     * Create a task, which may be To-do, deadline or events
     *
     * @param commandMap argumentPayload map of the user input
     * @param command    the type of task to create
     * @throws TaskException for bad user inputs
     */
    public void createTask(HashMap<String, String> commandMap, Command command) throws TaskException {
        // Assertion: commandMap has the correct subcommands & length
        Task newTask = null;
        if (command.equals(Command.TASK_TODO)) {
            // To-do task
            newTask = createTodo(commandMap);
        } else if (command.equals(Command.TASK_DEADLINE)) {
            // Deadline task
            newTask = createDeadline(commandMap);
        } else if (command.equals(Command.TASK_EVENT)) {
            // Event task
            newTask = createEvent(commandMap);
        }
        if (newTask == null) {
            // Safety check in case the assertion fails
            throw new TaskException(EXCEPTION_ADD_TASK_FAILED);
        }
        tasks.add(newTask);
        printTaskAddedMessage(newTask);
    }

    /**
     * Prints the task list of all tasks MAX is keeping track of
     */
    public void printTasklist() {
        if (tasks.size() == 0) {
            ui.printMessage(MESSAGE_LIST_EMPTY);
            return;
        }
        ui.printMessage(MESSAGE_LIST_HEADER);
        for (int i = 0; i < tasks.size(); ++i) {
            // Print number, box, description in that order
            Task curr = tasks.get(i);
            int currentTask = i + ONE_OFFSET;
            ui.printMessage(currentTask + DOT_SPACE + curr.getDescription());
        }
    }

    /**
     * Takes in a string representing the 1-indexed task number <br>
     * Checks for string validity and range validity <br>
     *
     * @param taskNumString string representation of the
     * @return zero-indexed integer representing the index of the task
     * @throws TaskException for out of range numbers and non-number inputs
     */
    private int convertTaskNumberString(String taskNumString) throws TaskException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(taskNumString) - ONE_OFFSET;
        } catch (NumberFormatException exception) {
            throw new TaskException(ERROR_MISSING_NO);
        }
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new TaskException(ERROR_BAD_TASK_NUM);
        }
        return taskNum;
    }

    /**
     * Mark the task to be done or not done
     *
     * @param taskNumString string representing the task number to be marked
     * @param isDone        if true, the task will be marked as done, else not done
     */
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

    /**
     * Remove a task from the task list
     *
     * @param taskNumString string representing the task number to be deleted
     */
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

    /**
     * Loads all tasks from the /data folder
     */
    public void loadData() {
        Storage dataHandler = new Storage();
        this.tasks = dataHandler.loadTasksFromDisk();
    }

    /**
     * Saves all current tasks to the /data folder
     */
    public void saveData() {
        Storage dataHandler = new Storage();
        dataHandler.saveTasksToDisk(tasks);
    }

    /**
     * Resets the task list to have no tasks
     */
    public void resetTaskList() {
        tasks.clear();
    }

    private void printFoundTasks(ArrayList<Task> matchedTasks) {
        ui.printMessage(MESSAGE_MAX_HAS_FOUND);
        for (int i = 0; i < matchedTasks.size(); ++i) {
            Task curr = matchedTasks.get(i);
            int currentTask = i + ONE_OFFSET;
            ui.printMessage(currentTask + DOT_SPACE + curr.getDescription());
        }
    }

    /**
     * Find tasks that may be related to the
     *
     * @param commandPayload  argumentPayload map of the user input
     * @param findArgumentKey the key value "find" or "fetch" that contains the query argument
     */
    public void findTasks(HashMap<String, String> commandPayload, String findArgumentKey) {
        // Naiively search for tasks that have exact matches with the query string
        String query = commandPayload.get(findArgumentKey);

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
            printFoundTasks(matchedTasks);
        }
    }

    public TaskManager() {
        tasks = new ArrayList<>();
        ui = new Ui();
        dateParser = new DateParser();
    }
}
