package io.github.haoyangw.rica.task;

import io.github.haoyangw.rica.exception.RicaTaskException;

import java.util.ArrayList;

public class TaskManager {
    private static final String ADD_PHRASE = " New %s I'll remember: ";
    private static final String DEADLINE_CMD = "deadline";
    private static final String EVENT_CMD = "event";
    private static final String INVALID_TASK_INDEX_ERROR = " You alright? I can't mark a task that doesn't exist as done xD";
    private static final String SINGLE_TASK_ADDED_PHRASE = " You have %d task for now, all the best!";
    private static final String TASK_ADDED_PHRASE = " You have %d tasks for now, all the best!";
    private static final String TODO_CMD = "todo";
    private static final String WRONG_TASK_TYPE = " Erm I don't think this task can be marked done xD";
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    private void addTask(Task newTask) {
        if (newTask != null) {
            this.getTasks().add(newTask);
        }
    }

    private void createTask(String typeOfTask, String command) {
        switch (typeOfTask) {
        case TaskManager.TODO_CMD:
            Todo newTodo = Todo.create(command);
            this.addTask(newTodo);
            printlnWithIndent(String.format(TaskManager.ADD_PHRASE,
                    TaskManager.TODO_CMD));
            printlnWithIndent("   " + newTodo.toString());
            break;
        case TaskManager.DEADLINE_CMD:
            Deadline newDeadline = Deadline.create(command);
            this.addTask(newDeadline);
            printlnWithIndent(String.format(TaskManager.ADD_PHRASE,
                    TaskManager.DEADLINE_CMD));
            printlnWithIndent("   " + newDeadline.toString());
            break;
        case TaskManager.EVENT_CMD:
            Event newEvent = Event.create(command);
            this.addTask(newEvent);
            printlnWithIndent(String.format(TaskManager.ADD_PHRASE,
                    TaskManager.EVENT_CMD));
            printlnWithIndent("   " + newEvent.toString());
            break;
        }
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

    private boolean hasAnyTasks() {
        return !this.getTasks().isEmpty();
    }

    private void insertTask(int indexOfTask, Task newTask) {
        if (newTask != null) {
            this.getTasks().add(indexOfTask, newTask);
        }
    }

    private void printlnWithIndent(String line) {
        System.out.print("    ");
        System.out.println(line);
    }

    private void rmTask(int indexOfTask) {
        boolean isNegativeIndex = indexOfTask < 0;
        boolean isIndexTooLarge = indexOfTask >= this.getTasks().size();
        if (isNegativeIndex || isIndexTooLarge) {
            return;
        }
        this.getTasks().remove(indexOfTask);
    }

    public void createTaskFrom(String command) {
        String[] parameters = command.split(" ");
        String typeOfTask = parameters[0];
        this.createTask(typeOfTask, command);
        int howManyTasks = this.getTasks().size();
        if (howManyTasks > 1) {
            printlnWithIndent(String.format(TaskManager.TASK_ADDED_PHRASE,
                    this.getTasks().size()));
        } else if (howManyTasks == 1) {
            printlnWithIndent(String.format(TaskManager.SINGLE_TASK_ADDED_PHRASE,
                    this.getTasks().size()));
        }
    }

    /**
     * Mark a given task in the task list as done
     *
     * @param indexOfTask Index of given task in the task list
     * @return rica.Task object representing the desired task being marked as done,
     * null if not an instance of rica.Todo
     */
    public Todo markDone(int indexOfTask) throws RicaTaskException {
        boolean isNegativeIndex = indexOfTask < 0;
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
            printlnWithIndent(" Take a break maybe? Alright marked as done my friend:");
            printlnWithIndent("    " + selectedTodo);
            return selectedTodo;
        }
        this.rmTask(indexOfTask);
        selectedTodo = selectedTodo.setDone(true);
        this.insertTask(indexOfTask, selectedTodo);
        printlnWithIndent(" Shall remember that this task is done:");
        printlnWithIndent("    " + selectedTodo);
        return selectedTodo;
    }

    /**
     * Prints out the list of tasks added so far, or inform the user if no tasks have been added
     * yet
     */
    public void printTasks() {
        if (!this.hasAnyTasks()) {
            printlnWithIndent(" Hope I'm not amnesiac, but I don't remember any tasks?");
        } else {
            ArrayList<Task> tasks = this.getTasks();
            printlnWithIndent(" I think you have these tasks:");
            for (int i = 1; i <= tasks.size(); i += 1) {
                printlnWithIndent(" " + i + "." + tasks.get(i - 1));
            }
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
        boolean isIndexNegative = indexOfTask < 0;
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
            printlnWithIndent(" Getting a little ahead of yourself are you xD It's not even done:");
            printlnWithIndent("    " + selectedTodo);
            return selectedTodo;
        }
        this.rmTask(indexOfTask);
        selectedTodo = selectedTodo.setDone(false);
        this.insertTask(indexOfTask, selectedTodo);
        printlnWithIndent(" (Why??) Anyway, I've marked this task as not done yet:");
        printlnWithIndent("    " + selectedTodo);
        return selectedTodo;
    }

}
