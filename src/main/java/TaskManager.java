public class TaskManager {
    protected Task[] taskList;
    protected int taskCount;
    TaskManager(int taskCount){
        this.taskCount = 0;
        taskList = new Task[100];
    }
    public void addTask(Task upcomingTask){
        taskList[taskCount] = upcomingTask;
        taskCount += 1;
    }

    public void markTaskAsDone(int taskIndex){
        taskList[taskIndex-1].markAsDone();
    }
    public void markTaskAsUndone(int taskIndex){
        taskList[taskIndex-1].markAsUndone();
    }
    public void markTask(String input) {
        int whitespaceIndex = input.indexOf(" ");
        int startingIndex = Integer.parseInt(input.substring(whitespaceIndex + 1));
        if (input.indexOf("m") == 0) {
            //taskList[startingIndex - 1].markAsDone();
            this.markTaskAsDone(startingIndex);
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            //taskList[startingIndex - 1].markAsUndone();
            this.markTaskAsUndone(startingIndex);
            System.out.println("OK, I've marked this task as not done yet: ");
        }
        taskList[startingIndex - 1].printTaskDetails();
        //printSeparator();
    }
    public void listTasks(){
        for (int i = 0; i < taskCount; i++){
            System.out.print((i+1)+ ".");
            taskList[i].printTaskDetails();
        }
    }
}
