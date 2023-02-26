package tasklist;
import exceptions.InvalidAddTaskException;
import exceptions.InvalidCommandException;
import exceptions.TaskListOutofBoundsException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import userInterface.Print;
import java.util.ArrayList;

/*
 * Handles all the tasks methods
 * Tasklist taskslist is the arraylist containing all the tasks
 */
public class Tasklist {

    public static ArrayList<Task> tasksList = new ArrayList<Task>();

    /*
     * Used in the default case of the runCommand() method,
     * where the command input does not correspond to any possible commands
     * 
     * @throws InvalidCommandException
     */ 
    public static void throwInvalidCommand() throws InvalidCommandException{
        throw new InvalidCommandException();
    }
    
    /*
     * Returns true if all parameters are present
     * Returns false if the dates or description or "/to" or "/from" is missing
     * 
     * @param String[] userInputArray array of user's event input
     * @return bool true/false if the parameters are valid/invalid
     */
    public static boolean isEventValid(String[] userInputArray){
        if (userInputArray.length == 1){
            return false;           
            }
        boolean containsFrom = false;
        boolean containsTo = false;
        boolean containsDescriptionAndDates = false;
        String[] allDetails = userInputArray[1].split(" ");   
        for (String i : allDetails){
            if (i.equals("/from")){
                containsFrom = true;
            }
            if (i.equals("/to")){
                containsTo = true;
            }
        }
        String[] eventDetails = userInputArray[1].split("/from | /to");
        if (eventDetails.length == 3){
            containsDescriptionAndDates = true;
        }
        return containsFrom && containsTo && containsDescriptionAndDates;
    }
    
    /*
     * Returns true if all parameters are present
     * Returns false if the dates or description or "/by" is missing
     * 
     * @param String[] userInputArray array user's deadline input
     * @return bool true/false if the parameters are valid/invalid
     */
    public static boolean isDatelineValid(String[] userInputArray){
        if (userInputArray.length ==1){        
            return false;
        }
        boolean byExists = false;
        boolean descriptionAndDatesExists = false;
        String[] allDetails = userInputArray[1].split(" ");
        for (String i : allDetails){           
            if (i.equals("/by")){
                byExists = true;
            }
        }
        String[] datelineDetails = userInputArray[1].split("/by");
        if (datelineDetails.length == 2){
            descriptionAndDatesExists = true;           
        }
        return byExists && descriptionAndDatesExists;
    }
    
    /*
     * Splits the user's input into the separate Event parameters
     * Adds event into taskslist 
     * 
     * @param String[] userInputArray from user's event input
     * @throws InvalidAddTaskException is parameters are invalid
     */
    public static void addEvent(String[] userInputArray) throws InvalidAddTaskException{
        if (!isEventValid(userInputArray)){
            throw new InvalidAddTaskException();
        }
        String[] eventDetails = userInputArray[1].split("/from | /to");
        String eventDescription = eventDetails[0];
        String startTime = eventDetails[1];
        String endTime = eventDetails[2];
        tasksList.add(new Event(eventDescription, startTime, endTime));
        Print.printTaskDescription(eventDescription);
    } 

    /*
     * Splits the user's input into the separate Deadline parameters
     * Adds deadline into taskslist 
     * 
     * @param String[] userInputArray from user's deadline input
     * @throws InvalidAddTaskException is parameters are invalid
     */
    public static void addDeadline(String[] userInputArray) throws InvalidAddTaskException{
        if (!isDatelineValid(userInputArray)){
            throw new InvalidAddTaskException();
        }
        String[] deadlineDetails = userInputArray[1].split(" /by",2);
        String deadlineDescription = deadlineDetails[0];
        String deadlineDate = deadlineDetails[1];
        tasksList.add(new Deadline(deadlineDescription, deadlineDate));
        Print.printTaskDescription(deadlineDescription);
    }

    /*
     * Adds todo into taskslist 
     * 
     * @param String[] userInputArray from user's todo input
     * @throws InvalidAddTaskException is parameters are invalid
     */
    public static void addTodo(String[] userInputArray) throws InvalidAddTaskException{
        if (userInputArray.length == 1){
            throw new InvalidAddTaskException();
        }
        String todoDescription = userInputArray[1];
        tasksList.add(new Todo(todoDescription));
        Print.printTaskDescription(todoDescription); 
    }

    /*
     * Retrieves each Task from tasksList and prints their information 
     * In the form of taskNumber.[mark Status][task type] description dates/times
     */
    public static void displayTaskList(){
        Print.printLine();
        for (int i = 0; i < tasksList.size(); i++){
            String taskNumber = Integer.toString(i+1);
            Task currentTask = tasksList.get(i);      
            System.out.println("\t" + taskNumber + "." + currentTask.getStatusIcon() + currentTask.printTask());
        }
        Print.printLine();
    }
    
    /*
     * Deletes Task in taskslist
     * 
     * @param int Index of Task specified by user 
     */
    public static void deleteTask(int index){
        Task currentTask = tasksList.get(index-1); 
        System.out.println("\t" + "Noted. I have removed this task: " + currentTask.getStatusIcon() + currentTask.printTask());
        tasksList.remove(currentTask);
    }
    
    /*
     * Marks Task in taskslist
     * 
     * @param int Index of Task specified by user 
     */
    public static void markTask(int index){
        Task currentTask = tasksList.get(index-1); 
        currentTask.markAsDone();
        Print.printLine();
        System.out.println("\t" + '"' + currentTask.description + '"' + " is marked as done! Good Job :)");
        Print.printLine();
    }
    
    /*
     * Unmarks Task in taskslist
     * 
     * @param int Index of Task specified by user 
     */
    public static void unmarkTask(int index){
        Task currentTask = tasksList.get(index-1); 
        currentTask.markAsUndone();
        Print.printLine();
        System.out.println("\t" + '"' + currentTask.description + '"' + " is marked as undone. Jiayou!");
        Print.printLine();
    }
    
    /*
     * Returns the command of the user's input
     * 
     * @param String[] userInputArray from user's input
     * @return String command 
     */
    public static String retrieveCommand(String[] userInputArray){
        String command = userInputArray[0];
        return command;
    }
    
    /*
     * Returns the index of the task that the user wants to mark/unmark/delete
     * 
     * @param String[] userInputArray from user's input
     * @return int markIndex
     */
    public static int retrieveMarkIndex(String[] userInputArray) throws TaskListOutofBoundsException{
        int markIndex = Integer.parseInt(userInputArray[1]);
        if (markIndex == 0 || markIndex > tasksList.size()){
            throw new TaskListOutofBoundsException();
        }
        return markIndex;
    }
}
