import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n");
        Scanner input = new Scanner(System.in);
        String userName;
        System.out.println("What is your name? (Please enter name)\n");
        userName = input.nextLine();
        System.out.println("What can I do for you," + userName + " ?\n");
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
