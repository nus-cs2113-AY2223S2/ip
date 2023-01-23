import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you?");
        String userInput;
        Scanner scan = new Scanner(System.in);
        while (true) {
            userInput = scan.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println(userInput);
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}
