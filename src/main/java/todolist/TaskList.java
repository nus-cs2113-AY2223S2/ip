package todolist;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import ui.UI;

import java.util.ArrayList;

public class TaskList {

    //Message Strings
    public static final String TASKS_WITH_CORRESPONDING_KEYWORDS = "These are the tasks found with Keyword: ";
    //Error Strings
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

    /**
     * Lists all the tasks in the taskList.
     * If taskList is empty, an error message is printed instead.
     */
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
        int tasksLeft = list.size();
        UI.printDeletedTaskMessage(currentTask, tasksLeft);
    }

    /**
     * Loops through all the tasks in TaskList and prints all tasks containing the queried String
     *
     * @param query The String queried
     */
    public void searchByKeyword(String query) {
        ArrayList<String> tasksWithMatchingKeyword = new ArrayList<String>();
        for (Task currentTask : list) {
            String currentTaskName = currentTask.getTask();
            if (currentTaskName.contains(query)) {
                tasksWithMatchingKeyword.add(currentTaskName);
            }
        }
        printTasksWithMatchingKeyword(tasksWithMatchingKeyword, query);
    }

    /**
     * Helper function used by searchByKeyword
     * Prints an error message if no matching tasks are found. Else, it prints all the matching tasks.
     *
     * @param listOfTasks the ArrayList containing all the matching Tasks
     * @param query The String queried
     */
    private static void printTasksWithMatchingKeyword(ArrayList<String> listOfTasks, String query) {
        if(listOfTasks.size() == 0) {
            System.out.println(ERROR_NO_TASK_WITH_KEYWORD_FOUND);
            return;
        }
        System.out.println(TASKS_WITH_CORRESPONDING_KEYWORDS + query);
        int indexOfTask = 1;
        for(String currentTask : listOfTasks) {
            String printedMessage = String.format("[%d] %s", indexOfTask, currentTask);
            System.out.println(printedMessage);
            indexOfTask += 1;
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
        UI.printMarkedTaskCompleteMessage(currentTask);
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
        UI.printMarkedTaskIncompleteMessage(currentTask);
    }

    public void addNewEventTask(String[] inputMessage) {
        Event newEvent = new Event(inputMessage[0], inputMessage[1], inputMessage[2]);
        list.add(newEvent);
        UI.printAddedNewTaskMessage(newEvent);
    }

    public void addNewDeadlineTask(String[] inputMessage) {
        Deadline newDeadline = new Deadline(inputMessage[0], inputMessage[1]);
        list.add(newDeadline);
        UI.printAddedNewTaskMessage(newDeadline);
    }

    public void addNewTodoTask(String task) {
        Todo newTodo = new Todo(task);
        this.list.add(newTodo);
        UI.printAddedNewTaskMessage(newTodo);
    }

}
