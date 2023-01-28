import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    //Definition lists //
    public static final String margin = "*----------------------------*";
    public static String updateStr = "added: ";          // for adding new tasks in taskList

    public static String[] taskList = new String[100];  // Create array to store the tasks
    public static int taskCount = 0;                    // Counter to track how many tasks in taskList


    // Run program here: //
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);


        // Welcome Message //
        System.out.println(margin);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(margin);

        Scanner input = new Scanner(System.in);
        String userCommand;

        do{
            userCommand = input.nextLine();    // Take in user input

            enter(userCommand);
        }
        while(!userCommand.equals("bye"));

        }


    // Determines what the program does based on user's input commands //
    public static void enter(String userCommand) {

        String[] strNumSeparator = userCommand.split(" ");

        switch (strNumSeparator[0]) {
            case "bye":        //Exit the program
                System.out.println(margin);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(margin);
                break;

            case "list":       // View taskList
                System.out.println(margin);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(Integer.toString(i + 1) + ". " + taskList[i]);
                }
                System.out.println(margin);
                break;

//            case "mark":       // Mark task as done
//                System.out.println(margin);
//                System.out.println("Nice! I've marked this task as done:");
//                System.out.println("  [" +  taskList[taskCount].getStatusIcon() + "]");
//                System.out.println(margin);
//                break;


            case "unmark":      // unmark task
                System.out.println(margin);
                System.out.println("OK, I've marked this task as not done yet:");

                System.out.println(margin);
                break;

            default:
                taskList[taskCount] = userCommand;           // adding new tasks
                taskCount++;
                System.out.println(margin);
                System.out.println(updateStr + userCommand);
                System.out.println(margin);
                break;

        }

    }

}
