import java.util.Scanner;

public class Duke {

    public static String[] Tasks= new String[100]; 
    
    public static int numberOfTasks = 0;

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

    /*public static void echoUserInput(String userInput){
        printLine();
        System.out.println(userInput);  
        printLine();
    }  */

    public static void addToList(String userInput){
        Tasks[numberOfTasks] = userInput;
        numberOfTasks += 1;
        printLine();
        System.out.println("\tadded: " + userInput);
        printLine(); 
    }

    public static void displayTaskList(){
        printLine();
        for (int i = 0; i < numberOfTasks; i++){
            String taskNumber = Integer.toString(i+1);
            System.out.println("\t" + taskNumber + ". " + Tasks[i]);
        }
        printLine();
    }

    public static void main(String[] args) {
        printWelcome();
        Scanner in = new Scanner(System.in);
        while (true){
            String userInput;
            userInput = in.nextLine();
            switch (userInput){
            case "bye": 
                printGoodbye();
                return;
            case "list":
                displayTaskList();
                break;
            default:
                addToList(userInput);
                break;
            }
        }
    }
}
