package tasks;

import java.util.ArrayList;

/**
 * Represents the complete list of tasks.
 */
public class Tasks {
    public static final String DELETE_TASK_ALERT = "Noted. I have removed the following task:";
    public static final String UNMARK_TASK_ALERT = "OK, I've marked this task as not done yet:";
    public static final String MARK_TASK_ALERT = "Nice! I've marked this task as done:";
    public static final String PRINT_LIST_ALERT = "Here are the tasks in your list:";
    public static final String ADD_TASK_ALERT = "Got it. I've added this task:\n";
    protected static ArrayList<Task> tasks;
    private static int tasksCount;
    
    /**
     * Initialises an ArrayList of tasks to store the list
     * @implNote 1-based indexing implemented to allow for easier access to the numbering, 0 should be ignored
     */
    public Tasks() {
        tasks = new ArrayList<Task>();
        tasks.add(null); // to implement 1-based indexing
        tasksCount = 0;
    }
    
    public int getTasksCount() {
        return tasksCount;
    }
    
    public void setTasksCount(int tasksCount) {
        Tasks.tasksCount = tasksCount;
    }
    
    public void printTask(int taskNumber) {
        String taskMessage = taskNumber + "." + tasks.get(taskNumber).toString();
        System.out.println(taskMessage);
    }
    
    public void printListCount() {
        String listCount = "Now you have " + tasksCount + " tasks in the list.";
        System.out.println(listCount);
    }
    
    public Task getTask(int taskCount) {
        return tasks.get(taskCount);
    }
    
    public void addSavedTask(Task newTask) {
        tasksCount++;
        tasks.add(newTask);
    }
    
    public void addTask(Task newTask) {
        tasksCount++;
        tasks.add(newTask);
        
        System.out.println(ADD_TASK_ALERT + newTask.toString());
        printListCount();
    }
    
    public void markTaskDone(int taskNumber) {
        tasks.get(taskNumber).markTask(true);
        System.out.println(MARK_TASK_ALERT);
        printTask(taskNumber);
    }
    
    public void markTaskUndone(int taskNumber) {
        tasks.get(taskNumber).markTask(false);
        System.out.println(UNMARK_TASK_ALERT);
        printTask(taskNumber);
    }

    public void deleteTask(int taskNumber) {
        System.out.println(DELETE_TASK_ALERT);
        printTask(taskNumber);
        tasks.remove(taskNumber);
        tasksCount--;
    }
    
    public void clear() {
        tasks.clear();
        
        // reinitialise tasks to make it 1-based indexing
        tasks.add(null);
        tasksCount = 0;
    }
    
    public void printList() {
        System.out.println(PRINT_LIST_ALERT);
        
        for (int i = 1; i <= tasksCount; i++) {
            printTask(i);
        }
        
        printListCount();
    }
}
