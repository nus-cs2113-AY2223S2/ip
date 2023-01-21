import java.util.Scanner;

public class Duke {
    public static void startDuke() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public static void endDuke() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        startDuke();
        Scanner input = new Scanner(System.in);
        String nextInput = input.nextLine();
        while (!nextInput.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     " + nextInput);
            System.out.println("    ____________________________________________________________");
            nextInput = input.nextLine();
        }
        endDuke();
    }

}
