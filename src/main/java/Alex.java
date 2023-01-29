import java.util.Scanner;

public class Alex {
    public static void main(String[] args) {
        String[] instructions = new String[100];
        int numberOfInstructions = 0;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        greeting();
        boolean isBye = false;
        while(!isBye){
            String userInput = myObj.nextLine();
            if(userInput.toLowerCase().equals("list")){
                for(int i = 1; i <= numberOfInstructions; i++){
                    System.out.print(i);
                    System.out.println(". " + instructions[i-1]);
                }
                printLine();

            }
            else if(userInput.toLowerCase().equals("bye")){
                exit();
                isBye = true;
                break;
            }
            else{
                instructions[numberOfInstructions] = userInput;
                numberOfInstructions += 1;
                printLine();
                echoResponse(userInput);
            }


        }


    }
    public static void echoResponse(String userInput){
        System.out.println("added: " + userInput);
        printLine();
    }
    public static void printLine(){
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }
    public static void greeting(){
        printLine();
        String greet = "Greetings! I'm Alex\nWhat can I do for you today?";
        //String question = "What can I do for you today?";
        System.out.println(greet);
        //System.out.println(question);
        printLine();
    }
    public static void exit(){
        String exit = "Bye. Hope to chat with you again soon!";
        System.out.println(exit);
        printLine();
    }
}
