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
        System.out.println( "\tThank you! See you again soon :)");
        printLine();
    }

    public static void addEvent(String userInput){
        int fromIndexStart = userInput.indexOf(" /from");
        int fromIndexEnd = fromIndexStart + 7;
        int toIndexStart = userInput.indexOf(" /to");
        int toIndexEnd = toIndexStart + 5;
        int startIndex = 5;
        String eventDescription = userInput.substring(startIndex, fromIndexStart);
        String startTime = userInput.substring(fromIndexEnd, toIndexStart);
        String endTime = userInput.substring(toIndexEnd);
        tasks.add(new Event(eventDescription, startTime, endTime));
        printLine();
        System.out.println("\tadded: " + eventDescription);
        printLine(); 
    } 

    public static void addDeadline(String userInput){
        int byIndexStart = userInput.indexOf(" /by");
        int byIndexEnd = byIndexStart + 4;
        int startIndex = 9;
        String deadlineDescription = userInput.substring(startIndex, byIndexStart);
        String deadlineDate = userInput.substring(byIndexEnd);
        tasks.add(new Deadline(deadlineDescription, deadlineDate));
        printLine();
        System.out.println("\tadded: " + deadlineDescription);
        printLine(); 
    }


    public static void addTodo(String userInput){
        int startIndex = 4;
        String todoDescription = userInput.substring(startIndex);
        tasks.add(new Todo(todoDescription));
        printLine();
        System.out.println("\tadded: " + todoDescription);
        printLine(); 
    }

    public static void displayTaskList(){
        printLine();
        for (int i = 0; i < tasks.size(); i++){
            String taskNumber = Integer.toString(i+1);
            Task currentTask = tasks.get(i);      
            System.out.println("\t" + taskNumber + "." +currentTask.getStatusIcon() + currentTask.printTask());
        
        }
        printLine();
    }

    public static void markTask(int index){
        Task currentTask = tasks.get(index-1); 
        currentTask.markAsDone();
        printLine();
        System.out.println("\t"+'"'+currentTask.description+'"'+" is marked as done! Good Job :)" );
        printLine();
    }

    public static void unmarkTask(int index){
        Task currentTask = tasks.get(index-1); 
        currentTask.markAsUndone();
        printLine();
        System.out.println("\t"+'"'+currentTask.description+'"'+" is marked as undone. Jiayou!" );
        printLine();
    }

    public static void main(String[] args) {
        printWelcome();
        Scanner in = new Scanner(System.in);
        while (true){
            String userInput;
            userInput = in.nextLine();
            String [] userInputArray = userInput.split(" ");
            String command = userInputArray[0];
            switch (command){
            case "bye": 
                printGoodbye();
                return;
            case "list":
                displayTaskList();
                break;
            case "mark":
                markTask(Integer.parseInt(userInputArray[1]));
                break;
            case "unmark":
                unmarkTask(Integer.parseInt(userInputArray[1]));
                break;
            case "todo":
                addTodo(userInput);
                break;
            case "deadline":
                addDeadline(userInput);
                break;
            case "event":
                addEvent(userInput);
                break;
            }
        }
    }
}
