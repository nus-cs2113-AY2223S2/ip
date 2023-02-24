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
     
     /*
     * =================================================================================
     *                      Adding of New Tasks (Todo/ Deadline/ Event)
     *                                      vvvvvvvvvv
     * =================================================================================
     */

    public void addTodo(String userInput) throws IllegalInputException {

        String todoTask = userInput.substring(4).trim();
        if (todoTask == "") {
            throw new IllegalInputException();
        }
        tasks.add(new Todo (todoTask));

        return;
    }

    public void addDeadline(String userInput) throws IllegalInputException, MissingCommandException, IllegalDayException {

        if ((userInput.indexOf("/by") < 0 )){
            throw new MissingCommandException();
        }
        String deadlineTask = userInput.substring(8, userInput.indexOf("/by")).trim();
        if (deadlineTask == "") {
            throw new IllegalInputException();
        }
        String deadlineDay = userInput.substring(userInput.indexOf("/by") + 3).trim();
        if (deadlineDay == "") {
            throw new IllegalDayException();
        }
        tasks.add(new Deadline(deadlineTask, deadlineDay));

        /*
        try {
            writeToFile("T:0:"+deadlineTask+":"+deadlineDay);
        } catch (IOException e){
            System.out.println(e);
        }
         */

        return;
    }

    public void addEvent(String userInput) throws IllegalInputException, MissingCommandException, IllegalDayException {

        if ((userInput.indexOf("/from") < 0 ) || (userInput.indexOf("/to") < 0 )){
            throw new MissingCommandException();
        }
        String eventTask = userInput.substring(5, userInput.indexOf("/")).trim();
        if (eventTask == "") {
            throw new IllegalInputException();
        }
        String eventFrom = userInput.substring(userInput.indexOf("/from") + 5, userInput.indexOf("/to")).trim();
        String eventTo = userInput.substring(userInput.indexOf("/to") + 3).trim();
        if (eventFrom == "" || eventTo == "") {
            throw new IllegalDayException();
        }
        tasks.add(new Event(eventTask, eventFrom, eventTo));

        /*
        try {
            writeToFile("T:0:"+eventTask+":"+eventFrom+":"+eventTo);
        } catch (IOException e){
            System.out.println(e);
        }
        */

        return;
    }

}
