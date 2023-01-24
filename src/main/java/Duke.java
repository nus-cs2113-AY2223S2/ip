import java.util.Scanner;
public class Duke {
    public static void printGreeting(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printFarewell(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printGreeting();
        while(true)
        {
            String userInput = in.nextLine();
            if(userInput.equals("bye")){
                break;
            }
            System.out.println(userInput);
        }
        printFarewell();
    }
}
