import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String openingMsg = " Hello! I'm Dukebot\n What can I do for you?";
        String closingMsg = " Goodbye! Hope to see you again soon ^^!";
        String horizontal = "---------------------------------";

        System.out.println(horizontal + "\n" + openingMsg + "\n" + horizontal);

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("bye")) {
            System.out.println(horizontal + "\n" + line + "\n" + horizontal);
            line = in.nextLine();
        }

        System.out.println(horizontal + "\n" + closingMsg + "\n" + horizontal);


    }
}
