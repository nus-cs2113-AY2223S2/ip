import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greet = "════════════════════════════════════════════════════════════\n"
                + " Hello! I'm Chatty\n"
                + " How can I help you?\n"
                + "════════════════════════════════════════════════════════════\n\n";
        System.out.print(greet);

        String[] list = new String[100];
        int listCount = 0;

        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            if (line.equals("list")) {
                System.out.print("════════════════════════════════════════════════════════════\n");

                for (int i = 0; i < listCount; i++) {
                    String printList = " " + (i + 1) + ". " + list[i] + "\n";
                    System.out.print(printList);
                }

                System.out.print("════════════════════════════════════════════════════════════\n\n");
            } else if (line.equals("bye")) {
                String exit = "════════════════════════════════════════════════════════════\n"
                        + " Goodbye. Hope to see you again soon!\n"
                        + "════════════════════════════════════════════════════════════\n";
                System.out.print(exit);
                break;
            } else {
                list[listCount] = line;
                listCount++;
                String echo = "════════════════════════════════════════════════════════════\n"
                        + " added: "
                        + line
                        + "\n"
                        + "════════════════════════════════════════════════════════════\n\n";
                System.out.print(echo);
            }
        }
    }
}