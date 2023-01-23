import java.util.Scanner;
public class Duke {
    public static void horizontalLine() {
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        horizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String userInput;
        Scanner in = new Scanner(System.in);

        while (true) {
            horizontalLine();
            System.out.println();
            userInput = in.nextLine();
            horizontalLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                horizontalLine();
                break;
            } else {
                System.out.println(userInput);
            }
        }

    }
}