import java.util.Scanner;
import java.util.ArrayList;
import Exceptions.TaskListOutofBoundsException;
import Exceptions.InvalidAddTaskException;
import Exceptions.InvalidCommandException;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<Task>();
    
    public static void printLine(){
        String horizontalLine = "\t――――――――――――――――――――――――――――――――――――――――――";
        System.out.println(horizontalLine);
    }
    
    public static void printWelcome(){
        printLine();
        System.out.println("\tWell hello there!\n" +
                           "\tWhat can I do for you today?");
        printLine();
    }

    public static void printGoodbye(){
        printLine();
        System.out.println("\tThank you! See you again soon :)");
        printLine();
    }

    public static void printTaskDescription(String description){
        printLine();
        System.out.println("\tadded: " + description);
        printLine(); 
    }

    public static void throwInvalidCommand() throws InvalidCommandException{
        throw new InvalidCommandException();
    }

    public static boolean isEventValid(String[] userInputArray){
        if (userInputArray.length == 1){
            return false;
        }
        boolean fromExists = false;
        boolean toExists = false;
        boolean descriptionAndDatesExists = false;
        String[] allDetails = userInputArray[1].split(" ");
        for (String i : allDetails){
            if (i.equals("/from")){
                fromExists = true;
            }
            if (i.equals("/to")){
                toExists = true;
            }
        }
        String[] eventDetails = userInputArray[1].split("/from | /to");
        if (eventDetails.length == 3){
            descriptionAndDatesExists = true;
        }
        return fromExists && toExists && descriptionAndDatesExists;
    }

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
        if (datelineDetails.length == 2 ){
            descriptionAndDatesExists = true;
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
        tasks.add(new Event(eventDescription, startTime, endTime));
        printTaskDescription(eventDescription);
    } 

    public static void addDeadline(String[] userInputArray) throws InvalidAddTaskException{
        if (!isDatelineValid(userInputArray)){
            throw new InvalidAddTaskException();
        }
        String[] deadlineDetails = userInputArray[1].split(" /by",2);
        String deadlineDescription = deadlineDetails[0];
        String deadlineDate = deadlineDetails[1];
        tasks.add(new Deadline(deadlineDescription, deadlineDate));
        printTaskDescription(deadlineDescription);
    }


    public static void addTodo(String[] userInputArray) throws InvalidAddTaskException{
        if (userInputArray.length == 1){
            throw new InvalidAddTaskException();
        }
        String todoDescription = userInputArray[1];
        tasks.add(new Todo(todoDescription));
        printTaskDescription(todoDescription); 
    }

    public static void displayTaskList(){
        printLine();
        for (int i = 0; i < tasks.size(); i++){
            String taskNumber = Integer.toString(i+1);
            Task currentTask = tasks.get(i);      
            System.out.println("\t" + taskNumber + "." + currentTask.getStatusIcon() + currentTask.printTask());
        }
        printLine();
    }

    public static void markTask(int index){
        Task currentTask = tasks.get(index-1); 
        currentTask.markAsDone();
        printLine();
        System.out.println("\t" + '"' + currentTask.description + '"' + " is marked as done! Good Job :)");
        printLine();
    }

    public static void unmarkTask(int index){
        Task currentTask = tasks.get(index-1); 
        currentTask.markAsUndone();
        printLine();
        System.out.println("\t" + '"' + currentTask.description + '"' + " is marked as undone. Jiayou!");
        printLine();
    }

    public static String retrieveCommand(String[] userInputArray){
        String command = userInputArray[0];
        return command;
    }

    public static int retrieveMarkIndex(String[] userInputArray) throws TaskListOutofBoundsException{
        int markIndex = Integer.parseInt(userInputArray[1]);
        if (markIndex == 0 || markIndex > tasks.size()){
            throw new TaskListOutofBoundsException();
        }
        return markIndex;
    }

    public static void main(String[] args){
        printWelcome();
        Scanner in = new Scanner(System.in);
        while (true){
            String userInput;
            userInput = in.nextLine();
            String [] userInputArray = userInput.split(" ",2);
            String command = retrieveCommand(userInputArray);
            try{
            switch (command){
            case "bye": 
                printGoodbye();
                return;
            case "list":
                displayTaskList();
                break;
            case "mark":
                markTask(retrieveMarkIndex(userInputArray));
                break;
            case "unmark":
                unmarkTask(retrieveMarkIndex(userInputArray));
                break;
            case "todo":
                addTodo(userInputArray);
                break;
            case "deadline":
                addDeadline(userInputArray);
                break;
            case "event":
                addEvent(userInputArray);
                break;
            default:
                throwInvalidCommand();
                break;
            }
        } catch (NumberFormatException e){
            System.out.println("Please type an integer behind mark/unmark!");
        } catch (TaskListOutofBoundsException e){
            System.out.println("The task you want to mark/unmark is not found!");
        } catch (InvalidCommandException e){
            System.out.println("Please input a valid command!");
        } catch (InvalidAddTaskException e){
            System.out.println("Please the input correct task parameters!");
        }
        }
    }
}
