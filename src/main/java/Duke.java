import java.util.Scanner;

/**
 * Incorrect Tags have been resolved, all commits have been tagged appropriately
 */
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------------------------------------------------");
        Scanner in = new Scanner(System.in);
        String userInput= " ";
        Task[] taskList = new Task[100];
        int currentIndex = 0;
        while (true) { // ensure that the loop can stay on forever if needed.
            userInput = in.nextLine();
            if(userInput.equals("bye")) { // exit command
                break;
            } else if(userInput.equals("list")) { //displays the list if needed
                System.out.println("\t---------------------------------------------------------------------------------");
                System.out.println("\tHere are the tasks in your list:");
                for(int i = 0; i<currentIndex;i+=1) {
                    System.out.println('\t' + Integer.toString(i+1) + "." + taskList[i].getStatusIcon() + " " +
                            taskList[i].getDescription());
                }
                System.out.println("\t---------------------------------------------------------------------------------");
            } else if (userInput.split(" ")[0].equals("mark")) { //mark the task in
                while(!(Integer.parseInt(userInput.split(" ")[1])>0 && Integer.parseInt(userInput.split(" ")[1])<=currentIndex+1)) {
                    System.out.println("\t---------------------------------------------------------------------------------");
                    System.out.println("\tNice try, enter a valid index to mark:");
                    System.out.println("\t---------------------------------------------------------------------------------");
                    userInput = in.nextLine();
                }
                System.out.println("\t---------------------------------------------------------------------------------");
                System.out.println("\tNice! I've marked this task as done:");
                taskList[Integer.parseInt(userInput.split(" ")[1])-1].markTask();
                System.out.println("\t\t" + taskList[Integer.parseInt(userInput.split(" ")[1])-1].getStatusIcon() + " " +
                        taskList[Integer.parseInt(userInput.split(" ")[1])-1].getDescription());
                System.out.println("\t---------------------------------------------------------------------------------");
            } else if (userInput.split(" ")[0].equals("unmark")) {//unmark the task
                while(!(Integer.parseInt(userInput.split(" ")[1])>0 && Integer.parseInt(userInput.split(" ")[1])<=currentIndex+1)) {
                    System.out.println("\t---------------------------------------------------------------------------------");
                    System.out.println("\tNice try, enter a valid index to unmark:");
                    System.out.println("\t---------------------------------------------------------------------------------");
                    userInput = in.nextLine();
                }
                System.out.println("\t---------------------------------------------------------------------------------");
                System.out.println("\tOK, I've marked this task as not done yet:");
                taskList[Integer.parseInt(userInput.split(" ")[1])-1].unMarkTask();
                System.out.println("\t\t" + taskList[Integer.parseInt(userInput.split(" ")[1])-1].getStatusIcon() + " " +
                        taskList[Integer.parseInt(userInput.split(" ")[1])-1].getDescription());
                System.out.println("\t---------------------------------------------------------------------------------");
            } else { // tells the user that we have added the task in
                taskList[currentIndex] = new Task(userInput); // set the description
                currentIndex+=1;
                System.out.println("\t---------------------------------------------------------------------------------");
                System.out.println("\tadded: " + userInput);
                System.out.println("\t---------------------------------------------------------------------------------");
            }

        }
        System.out.println("\t---------------------------------------------------------------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t---------------------------------------------------------------------------------");
    }
}
