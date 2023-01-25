/**
 * Level 1:
 * A few more tests are remaining:
 *      1.empty, just enter, [ _/ ]
 *      2.space, what could happen [ _/ ]
 *      3.some kinda special characters which can break the whole seqeunce, like /s or something? [ _/ ]
 *      All passed so far
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
        while (true) { // ensure that the loop can stay on forever if needed.
            userInput = in.nextLine();
            if(userInput.equals("bye")) {
                break; // break once "bye" is met
            }
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("\t" + userInput);
            System.out.println("---------------------------------------------------------------------------------");
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("---------------------------------------------------------------------------------");


    }
}
