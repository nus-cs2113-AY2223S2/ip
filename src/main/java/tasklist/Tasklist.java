package tasklist;
import exceptions.InvalidAddTaskException;
import exceptions.InvalidCommandException;
import exceptions.InvalidFindTaskException;
import exceptions.TaskListOutofBoundsException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import userInterface.Print;
import java.util.ArrayList;

public class Tasklist {

    public static ArrayList<Task> tasksList = new ArrayList<Task>();

    public static void throwInvalidCommand() throws InvalidCommandException{
        throw new InvalidCommandException();
    }
    
    public static boolean isEventValid(String[] userInputArray){
        if (userInputArray.length == 1){
            return false;           //return false if task is empty
            }
        boolean containsFrom = false;
        boolean containsTo = false;
        boolean containsDescriptionAndDates = false;
        String[] allDetails = userInputArray[1].split(" ");     // fromExists or toExists remains false if "/from" or /"to" doesnt exist
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
    
    public static boolean isDatelineValid(String[] userInputArray){
        if (userInputArray.length ==1){         //return false if task is empty
            return false;
        }
        boolean byExists = false;
        boolean descriptionAndDatesExists = false;
        String[] allDetails = userInputArray[1].split(" ");
        for (String i : allDetails){            //byExists equals false if "/by" does not exist
            if (i.equals("/by")){
                byExists = true;
            }
        }
        String[] datelineDetails = userInputArray[1].split("/by");
        if (datelineDetails.length == 2){
            descriptionAndDatesExists = true;           //descriptionAndDateExists = false if parameter is missing
        }
        return byExists && descriptionAndDatesExists;
    }
        
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

    public static void addTodo(String[] userInputArray) throws InvalidAddTaskException{
        if (userInputArray.length == 1){
            throw new InvalidAddTaskException();
        }
        String todoDescription = userInputArray[1];
        tasksList.add(new Todo(todoDescription));
        Print.printTaskDescription(todoDescription); 
    }

    public static void displayTaskList(){
        Print.printLine();
        for (int i = 0; i < tasksList.size(); i++){
            String taskNumber = Integer.toString(i+1);
            Task currentTask = tasksList.get(i);      
            System.out.println("\t" + taskNumber + "." + currentTask.getStatusIcon() + currentTask.printTask());
        }
        Print.printLine();
    }
    
    public static void deleteTask(int index){
        Task currentTask = tasksList.get(index-1); 
        System.out.println("\t" + "Noted. I have removed this task: " + currentTask.getStatusIcon() + currentTask.printTask());
        tasksList.remove(currentTask);
    }
    
    public static void markTask(int index){
        Task currentTask = tasksList.get(index-1); 
        currentTask.markAsDone();
        Print.printLine();
        System.out.println("\t" + '"' + currentTask.description + '"' + " is marked as done! Good Job :)");
        Print.printLine();
    }
    
    public static void unmarkTask(int index){
        Task currentTask = tasksList.get(index-1); 
        currentTask.markAsUndone();
        Print.printLine();
        System.out.println("\t" + '"' + currentTask.description + '"' + " is marked as undone. Jiayou!");
        Print.printLine();
    }
    
    public static String retrieveCommand(String[] userInputArray){
        String command = userInputArray[0];
        return command;
    }
    
    public static int retrieveMarkIndex(String[] userInputArray) throws TaskListOutofBoundsException{
        int markIndex = Integer.parseInt(userInputArray[1]);
        if (markIndex == 0 || markIndex > tasksList.size()){
            throw new TaskListOutofBoundsException();
        }
        return markIndex;
    }

    public static void findTask(String[] userInputArray)throws InvalidFindTaskException{
        if (userInputArray.length == 1){
            throw new InvalidFindTaskException();
        }
        String taskName = userInputArray[1];
        Print.printMatchingTasks();
        for (int i = 0; i < tasksList.size(); i++){
            Task currentTask = tasksList.get(i);
            if (currentTask.description.contains(taskName)){
                System.out.println("\t" + currentTask.getStatusIcon() + currentTask.printTask());
            }
        }
        Print.printLine();
    }

}
