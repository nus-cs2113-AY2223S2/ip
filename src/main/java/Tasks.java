public class Tasks {
    private Task[] tasks;
    private int tasksCount;
    
    public Tasks() {
        // implement 1-based indexing, ignore list[0] and areDone[0]
        tasks = new Task[101];
        tasksCount = 1;
    }
    
    public void addTask(String text) {
        Task newTask = new Task(text);
        tasks[tasksCount] = newTask;
        tasksCount++;
    }
    
    public void markTaskDone(int task) {
        tasks[task].markTask(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tasks[task].getDescription());
    }
    
    public void markTaskUndone(int task) {
        tasks[task].markTask(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + tasks[task].getDescription());
    }
    
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        
        for (int i = 1; i < tasksCount; i++) {
            System.out.println(i + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
    }
}
