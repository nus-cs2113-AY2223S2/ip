/**
 * Level 1:
 * A few more tests are remaining:
 *      1.empty, just enter, [ _/ ]
 *      2.space, what could happen [ _/ ]
 *      3.some kinda special characters which can break the whole seqeunce, like /s or something? [ _/ ]
 *      All passed so far
 */

/**
 * Level 2:
 * A few more tests are remaining:
 *     1. Possible to list 2 same things
 *     2. Line spacing has been ensured
 *     3. added line has one space after it
 *     4. new change of indenting all the lines is in progress/ completed
 */


import java.util.Scanner;
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
        String[] taskList = new String[100];
        int currentIndex = 0;
        while (true) { // ensure that the loop can stay on forever if needed.
            userInput = in.nextLine();
            if(userInput.equals("bye")) { // exit command
                break;
            } else if(userInput.equals("list")) { //displays the list if needed
                System.out.println("\t---------------------------------------------------------------------------------");
                for(int i = 0; i<currentIndex;i+=1) {
                    System.out.println('\t' + Integer.toString(i+1) + ". " + taskList[i]);
                }
                System.out.println("\t---------------------------------------------------------------------------------");
            } else { // tells the user that we have added the task in
                taskList[currentIndex] = userInput;
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
