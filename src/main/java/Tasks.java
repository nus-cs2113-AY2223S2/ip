import java.util.ArrayList;

public class Tasks {
    protected ArrayList<Task> tasks;
    private int tasksCount;
    
    public Tasks() {
        // implement 1-based indexing, ignore list[0]
        tasks = new ArrayList<Task>();
        tasks.add(null);
        tasksCount = 0;
    }
    
    public int getTasksCount() {
        return tasksCount;
    }
    
    public void addTask(Task newTask) {
        tasksCount++;
        tasks.add(newTask);
        
        System.out.println("Got it. I've added this task:" + System.lineSeparator() +
                newTask.toString() + System.lineSeparator() +
                "Now you have " + tasksCount + " tasks in the list.");
    }
    
    public void markTaskDone(int taskNumber) {
        tasks.get(taskNumber).markTask(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskNumber + "." + tasks.get(taskNumber).toString());
    }
    
    public void markTaskUndone(int taskNumber) {
        tasks.get(taskNumber).markTask(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskNumber + "." + tasks.get(taskNumber).toString());
    }
    
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        
        for (int i = 1; i <= tasksCount; i++) {
            System.out.println(i + "." + tasks.get(i).toString());
        }
        
        System.out.println("Now you have " + tasksCount + " tasks in the list.");
    }
}
