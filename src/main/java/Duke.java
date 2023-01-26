import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        //initial greeting
        System.out.println("____________________________________________________________");
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");


        //echo
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String line = scanner.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(line);
            System.out.println("____________________________________________________________");
            if (line.equals("bye")) {
                break;
            }

        }

        //end greeting
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
