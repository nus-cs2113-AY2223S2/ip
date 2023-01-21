import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Message.greet();

        // Input variables initialised.
        Scanner myScanner = new Scanner(System.in);
        String userInput = new String();

        // The main loop, which ends when user says Bye
        while (!userInput.toLowerCase().equals("bye")) {
            System.out.print("Input: ");
            userInput = myScanner.nextLine();
            Message.printHLine();
            System.out.println(userInput);
            Message.printHLine();
        }

        // Exit message
        Message.exit();
    }
}
