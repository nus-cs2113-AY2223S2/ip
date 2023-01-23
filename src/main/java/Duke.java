import java.util.Scanner;
public class Duke {
    public static void createHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printItems(String[] container) {
        for (int i = 0; i < container.length; ++i) {
            if (container[i] == null) {
                break;
            } else {
                System.out.println((i + 1) + ". " + container[i]);
            }
        }
    }
    public static void main(String[] args) {
        createHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String userInput;
        Scanner in = new Scanner(System.in);
        String[] tasks = new String[100];

        int currentIndex = 0;

        while (true) {
            createHorizontalLine();
            System.out.println();
            userInput = in.nextLine();
            createHorizontalLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                createHorizontalLine();
                break;
            } else if (userInput.equals("list")) {
                printItems(tasks);
            } else {
                tasks[currentIndex] = userInput;
                System.out.println("added: " + userInput);
                ++currentIndex;
            }
        }

    }
}