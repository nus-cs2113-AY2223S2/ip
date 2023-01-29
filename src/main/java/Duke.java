import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    //Definition lists //
    public static final String margin = "*----------------------------*";
    public static String updateStr = "added: ";          // for adding new tasks in taskList

    public static Task[] taskList = new Task[100];  // Create array to store the tasks
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
            userCommand = input.nextLine();

            enter(userCommand);
        }
        while(!userCommand.equals("bye"));

        }

    // How the program runs based on user's input commands //
    public static void enter(String userCommand) {

        String[] strNumSeparator = userCommand.split(" ");  // split string & number components

        switch (strNumSeparator[0]) {
            case "bye":        //Exit the program
                System.out.println(margin);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(margin);
                break;

            case "list":       // View taskList
                System.out.println(margin);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(Integer.toString(i + 1) + ". " + "["+ taskList[i].getStatusIcon() + "]" + taskList[i] );
                }
                System.out.println(margin);
                break;

            case "mark":       // Mark task as done
                int taskToMark = Integer.parseInt(strNumSeparator[1]) - 1;      // identify which task to mark
                System.out.println(margin);
                System.out.println("Nice! I've marked this task as done:");
                taskList[taskToMark].setDone(true);
                System.out.println("  [" +  taskList[taskToMark].getStatusIcon() + "] " + taskList[taskToMark]);
                System.out.println(margin);
                break;


            case "unmark":      // unmark task
                int taskToUnmark = Integer.parseInt(strNumSeparator[1]) - 1;      // identify which task to un
                System.out.println(margin);
                System.out.println("OK, I've marked this task as not done yet:");
                taskList[taskToUnmark].setDone(false);
                System.out.println("  [" +  taskList[taskToUnmark].getStatusIcon() + "] " + taskList[taskToUnmark]);
                System.out.println(margin);
                break;

            default:            // adding new tasks
                Task t = new Task(userCommand) ;
                taskList[taskCount] = t;
                taskCount++;
                System.out.println(margin);
                System.out.println(updateStr + t.description);
                System.out.println(margin);
                break;

        }

    }

}
