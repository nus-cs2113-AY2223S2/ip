import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        String line2;
        Scanner in = new Scanner(System.in);
        System.out.println("______________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________________________");

        while(true) {
            line = in.nextLine();
            line2 = line.toLowerCase();
            System.out.println("______________________________________________________________");
            if (line2.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("______________________________________________________________");
                return;
            } else {
                System.out.println(line);
                System.out.println("______________________________________________________________");
            }
        }
    }
}
