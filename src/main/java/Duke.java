import java.util.Scanner;

public class Duke {
    private static int userInputCount = 0;
    public static void main(String[] args) {
        String[] userInputList = new String[100];
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
            userInputList[userInputCount] = userInput;
            userInputCount++;
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                for (int i = 1; i < userInputCount; i++) {
                    System.out.println(i + ". " + userInputList[i]);
                }
            } else {
                System.out.println(userInput);
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}
