import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    final static String HORIZONTAL_LINE = "____________________________________________________________";

    public static void greeting() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE + '\n');

        return;
    }

    public static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE + '\n');

        return;
    }

    public static void main(String[] args) {
        ArrayList<String> userTexts = new ArrayList<String>();

        greeting();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            }

            String firstWord = userInput.split(" ", 2)[0];

            System.out.println(HORIZONTAL_LINE);
            if (firstWord.equals("list")) {
                for (int i = 0; i < userTexts.size(); i++) {
                    System.out.println((i + 1) + ". " + userTexts.get(i));
                }
            } else {
                userTexts.add(userInput);
                System.out.println("added: " + userInput);
            }
            System.out.println(HORIZONTAL_LINE + '\n');
        }

        exit();

    }
}
