public class List {
    private Task[] list;
    private int listLength;
    
    public List() {
        // implement 1-based indexing, ignore list[0] and areDone[0]
        list = new Task[101];
        listLength = 1;
    }
    
    public void addList(String text) {
        Task newTask = new Task(text);
        list[listLength] = newTask;
        listLength++;
    }
    
    public void markTaskDone(int task) {
        list[task].markTask(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + list[task].getDescription());
    }
    
    public void markTaskUndone(int task) {
        list[task].markTask(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + list[task].getDescription());
    }
    
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        
        for (int i = 1; i < listLength; i++) {
            System.out.println(i + ".[" + list[i].getStatusIcon() + "] " + list[i].getDescription());
        }
    }
}
