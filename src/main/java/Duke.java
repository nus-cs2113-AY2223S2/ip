import java.util.Scanner;

public class Duke {

    static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void main(String[] args) {

        // Variables needed
        Scanner scanner = new Scanner(System.in);
        String userInput;
        String[] listOfInputs = new String[100];
        int inputCounter = 0;
        // Start of the program
        greet();

        // Get first input
        userInput = scanner.nextLine();

        // Loop for next inputs
        while (!userInput.equals("bye")) {

            // Print list upon user request
            if (userInput.equals("list")) {
                printList(listOfInputs, inputCounter);
            }

            // Add other user inputs into list
            else {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("added: " + userInput);
                listOfInputs[inputCounter] = userInput;
                inputCounter++;
            }

            // Print trailing horizontal line and take in next input
            System.out.println(HORIZONTAL_LINE + "\n");
            userInput = scanner.nextLine();

        }

        // Exit program
        exit();

    }

    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printList(String[] listOfInputs, int inputCounter) {
        for (int i = 0; i < inputCounter; i++) {
            System.out.println(i + 1 + ". " + listOfInputs[i]);
        }
    }
}