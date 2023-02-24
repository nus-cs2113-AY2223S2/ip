import java.util.ArrayList;



public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public void MarkStatusAsDone(int taskNumber) {
        tasks.get(tasks.size() - 1).MarkStatusDone();
    }
    /*
     * =================================================================================
     *                      Declaration of tasks ArrayList, print Tasks
     *                                      vvvvvvvvvv
     * =================================================================================
     */

     public void printTasks() {
         Greeting.printSeperator();
         System.out.println("\tHere are the tasks in your list:");
         for (int i = 0; i < tasks.size(); i++) {
             System.out.println("\t" + (i+1) + "." + tasks.get(i).printTask());
         }
         Greeting.printSeperator();
     }
 
     public void printNewTask(int taskNumber) {
         Greeting.printSeperator();
         System.out.println("\tGot it. I've added this task: \n"+ "\t\t" + tasks.get(tasks.size()-1).printTask());
         //tasksLength ++;
         System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
         Greeting.printSeperator();
     }
 
 
 
     /*
      * =================================================================================
      *                               Mark/ Unmark/ Delete Tasks
      *                                      vvvvvvvvvv
      * =================================================================================
      */
 
     public void mark(String userInput) {
         int taskNumber = Integer.parseInt(userInput);
         tasks.get(taskNumber - 1).MarkStatusDone();
         Greeting.printSeperator();
         System.out.println("\tNice! I've marked this task as done:\n" +
                 "\t\t" + tasks.get(taskNumber - 1).printTask());
         Greeting.printSeperator();
     }
 
     public void unmark(String userInput) {
         int taskNumber = Integer.parseInt(userInput);
         tasks.get(taskNumber - 1).MarkStatusUndone();
         Greeting.printSeperator();
         System.out.println("\tOK, I've marked this task as not done yet:\n" +
 
                 "\t\t" + tasks.get(taskNumber - 1).printTask());
         Greeting.printSeperator();
     }
 
     public void deleteTask(String userInput) {
         int taskNumber = Integer.parseInt(userInput);
         Task taskToBeRemoved = tasks.get(taskNumber - 1);
         tasks.remove(taskNumber - 1);
         Greeting.printSeperator();
         System.out.println("\tNoted. I've removed this task:\n" +
                 "\t\t" + taskToBeRemoved.printTask());
         Greeting.printSeperator();
     }
 
}
