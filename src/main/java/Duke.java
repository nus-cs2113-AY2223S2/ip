import java.util.Scanner;

public class Duke {

    static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void main(String[] args) {

        // Variables needed
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Task[] listOfInputs = new Task[100];
        int inputCounter = 0;

        // Start of the program
        greet();
        // Get first task
        userInput = scanner.nextLine();
        // Loop for next tasks to add
        while (!userInput.equals("bye")) {

            System.out.println(HORIZONTAL_LINE);

            // Print list upon user request
            if (userInput.equals("list")) {
                printList(listOfInputs, inputCounter);
            }
            // Mark as done
            else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.substring(userInput.length() - 1));
                listOfInputs[taskIndex - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + listOfInputs[taskIndex - 1].getDescription());
            }

            // Mark as undone
            else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.substring(userInput.length() - 1));
                listOfInputs[taskIndex - 1].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + listOfInputs[taskIndex - 1].getDescription());
            }

            // Add other tasks into list
            else {
                System.out.println("added: " + userInput);
                Task newTask = new Task(userInput);
                listOfInputs[inputCounter] = newTask;
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

    public static void printList(Task[] listOfInputs, int inputCounter) {
        for (int i = 0; i < inputCounter; i++) {
            System.out.println(i + 1 + ".[" + listOfInputs[i].getStatusIcon() + "] "
                    + listOfInputs[i].getDescription());
        }
    }

}