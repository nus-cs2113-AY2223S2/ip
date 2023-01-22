import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        String[] list = new String[100];
        int nextEmptyIndex = 0;

        boolean done = false;

        while (!done) {
            line = in.nextLine();
            if (line.equals("bye")) {
                done = true;
            } else if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < nextEmptyIndex; i++) {
                    System.out.println((i+1) + ". " + list[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                list[nextEmptyIndex] = line;
                nextEmptyIndex++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + line);
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
