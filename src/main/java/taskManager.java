public class taskManager {
    private Task[] taskList;
    private int listSize;

    /**
     * Constructor for list assuming items in the list is <=100
     */
    public taskManager(){
        this.listSize = 0;
        this.taskList = new Task[100];
    }

    /**
     * Adds task to the list to keep track
     * @param userTaskInput: user task to remember
     */
    public void addToList(String userTaskInput){
        Task task = new Task(userTaskInput);
        taskList[this.listSize] = task;
        listSize+=1;
        System.out.println("Got it! Added " + userTaskInput + " to the list.");
    }

    public void markAsDone(int taskIndex){
        if(taskIndex >= listSize || taskIndex < 0){
            System.out.println("Task not found within the list!");
        } else{
            String task = taskList[taskIndex].setAsDone();
            System.out.println("Noted sir, I have marked \n"
                    + "[✔] " + task + "\n"
                    + "as done.");
        }
    }

    public void markAsUndone(int taskIndex){
        if(taskIndex >= listSize|| taskIndex < 0){
            System.out.println("Task not found within the list!");
        } else{
            String task = taskList[taskIndex].setAsUndone();
            System.out.println("Noted sir, I have marked \n"
                    + "[ ] " + task + "\n"
                    + "as not done.");
        }
    }

    /**
     * prints all tasks stored in the list
     */
    public void printList(){
        System.out.println("Here are the tasks in your list:");
        for(int i=1;i<this.listSize+1;i++){
            String task = taskList[i-1].getTask();
            String statusIcon = taskList[i-1].getStatusIcon();
            System.out.println(i + ". [" + statusIcon +"] " + task);
        }
    }
}
