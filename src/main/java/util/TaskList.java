package util;

import java.util.ArrayList;

import tasks.Task;

/**
 * Contains the list of tasks added by the user or loaded in on start up
 * Able to create delete, mark, unmark, add, print and load tasks
 */
public class TaskList {
    private static ArrayList<Task> listOfTasks = new ArrayList<>();
    private static final TaskDeleter taskDeleter = new TaskDeleter();
    private static final Marker marker = new Marker();
    private static final TaskAdder taskAdder = new TaskAdder();

    private static final Storage storage = new Storage();

    /**
     * @param input
     * @param doIPrintOutput If false, does not print task marked/unmarked. Occurs when loading data when Duke starts
     *                       If true, print task marked/unmarked. Occurs during the Duke session
     */
    public void addNewTask(String input, boolean doIPrintOutput) {
        taskAdder.addTaskToList(listOfTasks, input, doIPrintOutput);
    }

    public ArrayList<Task> accessTaskList() {
        return listOfTasks;
    }


    /**
     * prints all the list of tasks with their corresponding indexes
     */
    public void printList() {
        OutputUI outputUI = new OutputUI();
        outputUI.printList(listOfTasks, listOfTasks.size());
    }

    /**
     * @param input
     * @param doIPrintOutput If false, does not print task marked/unmarked. Occurs when loading data when Duke starts
     *                       If true, print task marked/unmarked. Occurs during the Duke session
     */
    public void handleMarkUnmarkAction(String input, boolean doIPrintOutput) {
        marker.markOrUnamrkTask(listOfTasks, input, doIPrintOutput);
    }

    /**
     * Starts the delete action
     * @param input Contains the 'delete' keyword and the task index to delete
     */
    public void handleDeleteAction(String input) {
        taskDeleter.attemptToDeleteTask(listOfTasks, input);
    }

    /**
     * Pushes all the current tasks to a file named listData.txt
     */
    public void saveData() {
        storage.saveList(listOfTasks);
    }


    /**
     * Takes in a keyword and tries to find all tasks from current task list
     * @param input contains the find keyword and the keyword used to find tasks
     */
    public void handleFindTaskAction(String input) {
        Finder finder = new Finder();
        finder.findTasksFromList(listOfTasks, input);
    }


}
