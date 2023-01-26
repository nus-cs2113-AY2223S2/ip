import java.util.Scanner;

public class Duke {
    public static void printLine(){
        String horizontalLine = "――――――――――――――――――――――――――――――――――――――――――";
        System.out.println(horizontalLine);
    }
    
    public static void printWelcome(){
        printLine();
        System.out.println("Well hello there!\n" +
                           "What can I do for you today?");
        printLine();
    }

    public static void printGoodbye(){
        printLine();
        System.out.println( "Thank you! See you again soon :)");
        printLine();
    }

    public static void echoUserInput(String userInput){
        printLine();
        System.out.println(userInput);
        printLine();
    }

    public static void main(String[] args) {
        printWelcome();
        Scanner in = new Scanner(System.in);
        while (true){
            String userInput;
            userInput = in.nextLine(); 
            if (userInput.equals("bye")){
                printGoodbye();
                return;
            }
            else{
                echoUserInput(userInput);
            }
        }
        
        
    }
}
