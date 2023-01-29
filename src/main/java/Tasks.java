public class Tasks {
    protected Task[] tasks;
    private int tasksCount;
    
    public Tasks() {
        // implement 1-based indexing, ignore list[0] and areDone[0]
        tasks = new Task[101];
        tasksCount = 0;
    }
    
    public int getTasksCount() {
        return tasksCount;
    }
    
    public void addTask(Task newTask) {
        tasksCount++;
        tasks[tasksCount] = newTask;
        
        System.out.println("Got it. I've added this task:" + System.lineSeparator() +
                newTask.toString() + System.lineSeparator() +
                "Now you have " + tasksCount + " tasks in the list.");
    }
    
    public void markTaskDone(int taskNumber) {
        tasks[taskNumber].markTask(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tasks[taskNumber].getDescription());
    }
    
    public void markTaskUndone(int task) {
        tasks[task].markTask(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + tasks[task].getDescription());
    }
    
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        
        for (int i = 1; i <= tasksCount; i++) {
            System.out.println(i + "." + tasks[i].toString());
        }
        
        System.out.println("Now you have " + tasksCount + " tasks in the list.");
    }
}
