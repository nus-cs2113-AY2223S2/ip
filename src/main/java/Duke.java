import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greet = "════════════════════════════════════════════════════════════\n"
                + " Hello! I'm Chatty\n"
                + " How can I help you?\n"
                + "════════════════════════════════════════════════════════════\n\n";
        System.out.print(greet);

        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            if (!line.equals("bye")) {
                String echo = "════════════════════════════════════════════════════════════\n"
                        + " "
                        + line
                        + "\n"
                        + "════════════════════════════════════════════════════════════\n\n";
                System.out.print(echo);
            } else {
                String exit = "════════════════════════════════════════════════════════════\n"
                        + " Goodbye. Hope to see you again soon!\n"
                        + "════════════════════════════════════════════════════════════";
                System.out.print(exit);
                break;
            }
        }
    }
}