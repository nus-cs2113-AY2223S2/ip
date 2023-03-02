package todolist;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

public class TaskList {
    //All the messages Strings
    public static final String COMPLETED_TASK_MESSAGE = "Nice! I've marked this task as done!";
    public static final String INCOMPLETE_TASK_MESSAGE = "Stop being lazy, get to work!";
    public static final String ADDED_TASK_MESSAGE = "Done! Added: ";
    public static final String TASK_REMOVED_MESSAGE_ONE = "Task removed:";
    public static final String TASK_REMOVED_MESSAGE_TWO = "Total tasks left: ";
    //Error Strings
    public static final String TASKS_WITH_CORRESPONDING_KEYWORDS = "These are the tasks found with Keyword: ";
    public static final String ERROR_NO_TASK_WITH_KEYWORD_FOUND = "No task with such keyword found";
    public static final String ERROR_NO_TASKS_IN_LIST = "Behold the fields of which I keep your tasks. " +
            "Lay thine eyes upon it, and see that it is barren";

    public ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public Task getTask(int taskIndex) {
        return list.get(taskIndex);
    }

    public void addTask(Task newTask) {
        list.add(newTask);
    }

    public int getSize() {
        return list.size();
    }

    public void listAllTasks() {
        int sizeOfTaskList = list.size();
        if (sizeOfTaskList == 0) {
            System.out.println(ERROR_NO_TASKS_IN_LIST);
        }
        for (int i = 0; i < sizeOfTaskList; i += 1) {
            Task currentTask = list.get(i);
            String printedMessage = String.format("%d.%s", i + 1, currentTask);
            System.out.println(printedMessage);
        }
    }

    /**
     * Removes a task, given the index of the task.
     * List follows a 1-based indexing.
     *
     * @param taskIndex The index of the Task to be deleted
     */
    public void deleteATask(int taskIndex) {
        Task currentTask = list.get(taskIndex);
        list.remove(taskIndex);
        System.out.println(TASK_REMOVED_MESSAGE_ONE);
        System.out.println(currentTask);
        System.out.println(TASK_REMOVED_MESSAGE_TWO + list.size());
    }

    /**
     * Loops through all the tasks in TaskList and prints all tasks containing the queried String
     *
     * @param query The String queried
     */
    public void searchByKeyword(String query) {
        int indexOfTaskFound = 1;
        for (Task currentTask : list) {
            String currentTaskName = currentTask.getTask();
            if (currentTaskName.contains(query)) {
                if (indexOfTaskFound == 1) {
                    System.out.println(TASKS_WITH_CORRESPONDING_KEYWORDS + query);
                }
                String printedMessage = String.format("[%d] %s", indexOfTaskFound, currentTask);
                System.out.println(printedMessage);
                indexOfTaskFound += 1;
            }
        }
        if (indexOfTaskFound == 1) {
            System.out.println(ERROR_NO_TASK_WITH_KEYWORD_FOUND);
        }
    }

    /**
     * Marks a task as complete, given the index of the task.
     * List follows a 1-based indexing.
     *
     * @param taskIndex The index of the Task to be marked
     */
    public void markTaskComplete(int taskIndex) {
        Task currentTask = list.get(taskIndex);
        currentTask.setComplete();
        System.out.println(COMPLETED_TASK_MESSAGE);
        System.out.println(currentTask);
    }

    /**
     * Marks a task as incomplete, given the index of the task.
     * List follows a 1-based indexing.
     *
     * @param taskIndex The index of the Task to be marked
     */
    public void markTaskIncomplete(int taskIndex) {
        Task currentTask = list.get(taskIndex);
        currentTask.setIncomplete();
        System.out.println(INCOMPLETE_TASK_MESSAGE);
        System.out.println(currentTask);
    }

    public void addNewEventTask(String[] inputMessage) {
        Event newEvent = new Event(inputMessage[0], inputMessage[1], inputMessage[2]);
        list.add(newEvent);
        System.out.println(ADDED_TASK_MESSAGE + newEvent);
    }

    public void addNewDeadlineTask(String[] inputMessage) {
        Deadline newDeadline = new Deadline(inputMessage[0], inputMessage[1]);
        list.add(newDeadline);
        System.out.println(ADDED_TASK_MESSAGE + newDeadline);
    }

    public void addNewTodoTask(String task) {
        Todo newTodo = new Todo(task);
        this.list.add(newTodo);
        System.out.println(ADDED_TASK_MESSAGE + newTodo);
    }

}
