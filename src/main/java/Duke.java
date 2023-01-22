import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Vivy");
        System.out.println("\t What can I do for you?");
        System.out.println("\t____________________________________________________________");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        String[] tasks = new String[100];
        int taskCount = 0;
        while(line.equals("bye") == false) {
            // List out all the tasks added
            if (line.equals("list")) {
                System.out.println("\t____________________________________________________________");
                for(int i = 0; i < taskCount; i++) {
                    System.out.println("\t " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("\t____________________________________________________________");
            } else {
                tasks[taskCount] = line;
                taskCount++;
                System.out.println("\t____________________________________________________________");
                System.out.println("\t added: " + line);
                System.out.println("\t____________________________________________________________");
            }
            line = in.nextLine();
        }

        // Exiting the program
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
