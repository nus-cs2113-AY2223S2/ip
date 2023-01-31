import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        boolean isFinished = false;

        String userInput;
        Scanner scan = new Scanner(System.in);

        while (!isFinished) {
            userInput = scan.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isFinished = true;
            } else {
                System.out.println(userInput);
            }
        }
    }
}
