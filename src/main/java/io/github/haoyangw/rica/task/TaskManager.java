package io.github.haoyangw.rica.task;

import io.github.haoyangw.rica.exception.RicaStorageException;
import io.github.haoyangw.rica.exception.RicaTaskException;
import io.github.haoyangw.rica.ui.TextUi;
import io.github.haoyangw.rica.storage.StorageManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages a list of Tasks that Rica is keeping track of. Provides utility methods
 *   for other classes to modify Rica's recorded Tasks.
 */
public class TaskManager {
    private static final String ADD_PHRASE = " New %s I'll remember: ";
    private static final String BAD_TASK_INDEX_ERROR = " Invalid task number? Are you trying too hard to avoid work xP";
    private static final String DEADLINE_CMD = "deadline";
    private static final String DELETE_CMD = "delete";
    private static final String EVENT_CMD = "event";
    private static final String INVALID_TASK_INDEX_ERROR = " You alright? I can't mark a task that doesn't exist as done xD";
    private static final String NO_TASKS_TO_DELETE_ERROR = " Add a task first... Then ask me to delete one xD";
    private static final String NOT_A_TASK_INDEX_ERROR = " Fat fingers? That's not a task number LOL";
    private static final String SINGLE_TASK_ADDED_PHRASE = " You have %d task for now, all the best!";
    private static final String TASK_ADDED_PHRASE = " You have %d tasks for now, all the best!";
    private static final String TASK_REMAINING_PHRASE = " Wow, just 1 task left! Congratulations, come party with me when you're done with work!";
    private static final String TASK_REMOVED_PHRASE = " I have removed this task for you:";
    private static final String TASKS_REMAINING_PHRASE = " Let's see... You now have %d tasks left. Keep going!";
    private static final String TODO_CMD = "todo";
    private static final String WRONG_CMD_ERROR = " Hello wrong command for %s! Check again?";
    private static final String WRONG_TASK_TYPE = " Erm I don't think this task can be marked done xD";
    private final StorageManager storageManager;
    private final TextUi textUi;
    private ArrayList<Task> tasks;

    public TaskManager() {
        storageManager = new StorageManager();
        textUi = new TextUi();
        tasks = new ArrayList<>();
    }

    /**
     * Adds a new Task to Rica's list of remembered Tasks
     *
     * @param newTask New Task Object to be added to Rica's list of Tasks
     */
    private void addTask(Task newTask) {
        if (newTask != null) {
            this.getTasks().add(newTask);
        }
    }

    /**
     * Creates the corresponding type of Task based on the given user command and
     *   adds it to Rica's list of Tasks she remembers.
     *
     * @param typeOfTask String describing what type of Task to create
     * @param command Full Task creation command that was issued by the user
     */
    private void createTask(String typeOfTask, String command) {
        switch (typeOfTask) {
        case TaskManager.TODO_CMD:
            Todo newTodo = Todo.create(command);
            this.addTask(newTodo);
            this.getTextUi().printlnWithIndent(String.format(TaskManager.ADD_PHRASE,
                    TaskManager.TODO_CMD));
            this.getTextUi().printlnWithIndent("   " + newTodo.toString());
            break;
        case TaskManager.DEADLINE_CMD:
            Deadline newDeadline = Deadline.create(command);
            this.addTask(newDeadline);
            this.getTextUi().printlnWithIndent(String.format(TaskManager.ADD_PHRASE,
                    TaskManager.DEADLINE_CMD));
            this.getTextUi().printlnWithIndent("   " + newDeadline.toString());
            break;
        case TaskManager.EVENT_CMD:
            Event newEvent = Event.create(command);
            this.addTask(newEvent);
            this.getTextUi().printlnWithIndent(String.format(TaskManager.ADD_PHRASE,
                    TaskManager.EVENT_CMD));
            this.getTextUi().printlnWithIndent("   " + newEvent.toString());
            break;
        }
    }

    private StorageManager getStorageManager() {
        return this.storageManager;
    }

    private Task getTask(int indexOfTask) {
        boolean isNegativeIndex = indexOfTask < 0;
        boolean isIndexTooLarge = indexOfTask >= this.getTasks().size();
        if (isNegativeIndex || isIndexTooLarge) {
            return null;
        }
        return this.getTasks().get(indexOfTask);
    }

    private ArrayList<Task> getTasks() {
        return this.tasks;
    }

    private TextUi getTextUi() {
        return this.textUi;
    }

    private boolean hasAnyTasks() {
        return !this.getTasks().isEmpty();
    }

    private void insertTask(int indexOfTask, Task newTask) {
        if (newTask != null) {
            this.getTasks().add(indexOfTask, newTask);
        }
    }

    private Task rmTaskByIndex(int indexOfTask) throws RicaTaskException {
        int FIRST_VALID_INDEX = 0;
        boolean isNegativeIndex = indexOfTask < FIRST_VALID_INDEX;
        boolean isIndexTooLarge = indexOfTask >= this.getTasks().size();
        if (isNegativeIndex || isIndexTooLarge) {
            throw new RicaTaskException(TaskManager.BAD_TASK_INDEX_ERROR);
        }
        return this.getTasks().remove(indexOfTask);
    }

    /**
     * Given a Task creation command issued by the user, create the correct type
     *   of Task, add it to Rica's memory, and help her record the new list of Tasks
     *   to persistent storage.
     *
     * @param command Task creation command issued by the user
     */
    public void createTaskFrom(String command) {
        String[] parameters = command.split(" ");
        int FIRST_PARAM = 0;
        String typeOfTask = parameters[FIRST_PARAM];
        this.createTask(typeOfTask, command);
        this.getStorageManager().saveTasks(this.getTasks());
        int howManyTasks = this.getTasks().size();
        int ONLY_ONE_TASK = 1;
        if (howManyTasks > ONLY_ONE_TASK) {
            this.getTextUi().printlnWithIndent(String.format(TaskManager.TASK_ADDED_PHRASE,
                    this.getTasks().size()));
        } else if (howManyTasks == ONLY_ONE_TASK) {
            this.getTextUi().printlnWithIndent(String.format(TaskManager.SINGLE_TASK_ADDED_PHRASE,
                    this.getTasks().size()));
        }
    }

    public List<Task> getMatchingTasks(String keyword) {
        return this.getTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Sets up TaskManager fully.
     *
     * Internally, this restores TaskManager's previously saved list of Tasks,
     *   which is IO-heavy and prone to Exceptions. As such, only run this when
     *   the application is ready.
     */
    public void initialise() {
        try {
            tasks = storageManager.getSavedTasks();
        } catch (RicaStorageException exception) {
            textUi.printImportantErrorMessage(exception);
            textUi.printFooter();
        }
    }

    /**
     * Mark a given task in the task list as done
     *
     * @param indexOfTask Index of given task in the task list
     * @return rica.Task object representing the desired task being marked as done,
     * null if not an instance of rica.Todo
     * @throws RicaTaskException If an invalid Task index is given or a Task cannot
     *   be marked done
     */
    public Todo markDone(int indexOfTask) throws RicaTaskException {
        int FIRST_VALID_INDEX = 0;
        boolean isNegativeIndex = indexOfTask < FIRST_VALID_INDEX;
        boolean isIndexTooLarge = indexOfTask >= this.getTasks().size();
        if (isNegativeIndex || isIndexTooLarge) {
            throw new RicaTaskException(TaskManager.INVALID_TASK_INDEX_ERROR);
        }
        Task selectedTask = this.getTask(indexOfTask);
        boolean isTaskATodo = selectedTask instanceof Todo;
        if (!isTaskATodo) {
            throw new RicaTaskException(TaskManager.WRONG_TASK_TYPE);
        }
        // At this point, rica.Task is definitely an instance of rica.Todo. Can cast it to rica.Todo safely
        Todo selectedTodo = (Todo) selectedTask;
        if (selectedTodo.getIsDone()) {
            this.getTextUi().printlnWithIndent(" Take a break maybe? Alright marked as done my friend:");
            this.getTextUi().printlnWithIndent("    " + selectedTodo);
            return selectedTodo;
        }
        this.rmTaskByIndex(indexOfTask);
        selectedTodo = selectedTodo.setDone(true);
        this.insertTask(indexOfTask, selectedTodo);
        this.getStorageManager().saveTasks(this.getTasks());
        this.getTextUi().printlnWithIndent(" Shall remember that this task is done:");
        this.getTextUi().printlnWithIndent("    " + selectedTodo);
        return selectedTodo;
    }

    /**
     * Prints out the list of tasks added so far, or inform the user if no tasks have been added
     * yet
     */
    public void printTasks() {
        this.getTextUi().printTasks(this.getTasks());
    }

    /**
     * Parses a delete command issued by the user and removes the corresponding
     *   Task from Rica's memory of all the user's Tasks.
     *
     * @param command Full delete command issued by the user
     * @throws RicaTaskException If the wrong command keyword was used, no Tasks
     *   exist, or a non-integer index was given within the command
     */
    public void deleteTaskSpecifiedByl(String command) throws RicaTaskException {
        String[] parameters = command.split(" ");
        int FIRST_PARAM = 0;
        if (!parameters[FIRST_PARAM].equals(TaskManager.DELETE_CMD)) {
            String cmdType = "deleting a task";
            throw new RicaTaskException(String.format(TaskManager.WRONG_CMD_ERROR, cmdType));
        }
        if (!this.hasAnyTasks()) {
            throw new RicaTaskException(TaskManager.NO_TASKS_TO_DELETE_ERROR);
        }
        int givenIndex;
        int SECOND_PARAM = 1;
        try {
            givenIndex = Integer.parseInt(parameters[SECOND_PARAM]);
        } catch (NumberFormatException exception) {
            throw new RicaTaskException(TaskManager.NOT_A_TASK_INDEX_ERROR);
        }
        // givenIndex is 1-based, but rmTask() expects 0-based indexing, so subtract one
        //   before passing to rmTask()
        Task removedTask = this.rmTaskByIndex(givenIndex - 1);
        this.getStorageManager().saveTasks(this.getTasks());
        this.getTextUi().printlnWithIndent(TaskManager.TASK_REMOVED_PHRASE);
        this.getTextUi().printlnWithIndent("   " + removedTask.toString());
        int numTasksLeft = this.getTasks().size();
        if (numTasksLeft == 1) {
            this.getTextUi().printlnWithIndent(TaskManager.TASK_REMAINING_PHRASE);
        } else {
            this.getTextUi().printlnWithIndent(String.format(TaskManager.TASKS_REMAINING_PHRASE, numTasksLeft));
        }
    }

    /**
     * Mark a given task in the task list as not done
     *
     * @param indexOfTask Index of desired task in the task list
     * @return rica.Task object representing the desired task being marked as not done,
     * null if not an instance of rica.Todo
     */
    public Todo unmarkDone(int indexOfTask) throws RicaTaskException {
        int FIRST_VALID_INDEX = 0;
        boolean isIndexNegative = indexOfTask < FIRST_VALID_INDEX;
        boolean isIndexTooLarge = indexOfTask >= this.getTasks().size();
        if (isIndexNegative || isIndexTooLarge) {
            throw new RicaTaskException(TaskManager.INVALID_TASK_INDEX_ERROR);
        }
        Task selectedTask = this.getTask(indexOfTask);
        boolean isTaskATodo = selectedTask instanceof Todo;
        if (!isTaskATodo) {
            throw new RicaTaskException(TaskManager.WRONG_TASK_TYPE);
        }
        Todo selectedTodo = (Todo) selectedTask;
        if (!selectedTodo.getIsDone()) {
            this.getTextUi().printlnWithIndent(" Getting a little ahead of yourself"
                    + "are you xD It's not even done:");
            this.getTextUi().printlnWithIndent("    " + selectedTodo);
            return selectedTodo;
        }
        this.rmTaskByIndex(indexOfTask);
        selectedTodo = selectedTodo.setDone(false);
        this.insertTask(indexOfTask, selectedTodo);
        this.getStorageManager().saveTasks(this.getTasks());
        this.getTextUi().printlnWithIndent(" (Why??) Anyway, I've marked this task as not done yet:");
        this.getTextUi().printlnWithIndent("    " + selectedTodo);
        return selectedTodo;
    }

}
