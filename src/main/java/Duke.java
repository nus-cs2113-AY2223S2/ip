import java.util.Scanner;
public class Duke {
    public static Scanner input = new Scanner(System.in);
    public static String[] list = new String[100];
    public static Boolean exit = false;
    public static void printList(int pos){
        int counter = 1;
        if (pos == 0){
            System.out.println("No Task!");
        }
        while (pos != 0){
            System.out.println(counter + ". " +list[counter-1]);
            counter ++;
            pos--;
        }
    }
    
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting ="____________________________________________________________\n" +
                " Hi! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String farewellMessage =
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);
        int pos = 0;
        while (!exit){
            String userInput = input.nextLine();  // Read user input
            switch(userInput) {
            case "list":
                System.out.println("Here is your list!");
                printList(pos);
                break;
            case "bye":
                System.out.println(farewellMessage);
                exit = true;
                break;
            default:
                list[pos] = userInput;
                System.out.println("Added! " + userInput);
            }
            pos++;
        }
    }
}
