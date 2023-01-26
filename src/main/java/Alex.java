import java.util.Scanner;

public class Alex {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        greeting();
        boolean isBye = false;
        while(!isBye){
            String userInput = myObj.nextLine();
            printLine();
            if(userInput.toLowerCase().equals("bye")){
                exit();
                isBye = true;
                break;
            }
            echoResponse(userInput);

        }


    }
    public static void echoResponse(String userInput){
        System.out.println(userInput);
        printLine();
    }
    public static void printLine(){
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }
    public static void greeting(){
        printLine();
        String greet = "Hello! I'm Alex";
        String question = "What can I do for you?";
        System.out.println(greet);
        System.out.println(question);
        printLine();
    }
    public static void exit(){
        String exit = "Bye. Hope to see you again soon!";
        System.out.println(exit);
        printLine();
    }
}
