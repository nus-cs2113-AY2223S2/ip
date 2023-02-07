import java.util.Scanner;
import java.util.ArrayList;

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

    public static void printInvalidCommand(){
        printLine();
        System.out.println("Please enter a valid command!");
        printLine();
    }

    public static void addEvent(String[] userInputArray){
        String[] eventDetails = userInputArray[1].split("/from | /to");
        String eventDescription = eventDetails[0];
        String startTime = eventDetails[1];
        String endTime = eventDetails[2];
        tasks.add(new Event(eventDescription, startTime, endTime));
        printTaskDescription(eventDescription);
    } 

    public static void addDeadline(String[] userInputArray){
        String[] deadlineDetails = userInputArray[1].split(" /by",2);
        String deadlineDescription = deadlineDetails[0];
        String deadlineDate = deadlineDetails[1];
        tasks.add(new Deadline(deadlineDescription, deadlineDate));
        printTaskDescription(deadlineDescription);
    }

    public static void addTodo(String[] userInputArray){
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

    public static int retrieveMarkIndex(String[] userInputArray){
        int markIndex = Integer.parseInt(userInputArray[1]);
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
                printInvalidCommand();
                break;
                
            }
        }
    }
}
