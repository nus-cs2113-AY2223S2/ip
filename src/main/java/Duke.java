import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String openingMsg = "Hello! I'm Dukebot\n\tWhat can I do for you?";
        String closingMsg = "Goodbye! Hope to see you again soon ^^!";
        String horizontal = "---------------------------------";
        String[] list = new String[100];

        System.out.println(horizontal + "\n\t" + openingMsg + "\n" + horizontal);

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        int item = 0;
        while (!line.equals("bye")) {
            if (!line.equals("list")) {
                list[item] = line;
                System.out.println(horizontal + "\n\tadded: " + line + "\n" + horizontal);
                item++;
            }

            line = in.nextLine();

            // display list
            if (line.equals("list")) {
                System.out.println(horizontal);
                for (int i = 0; i < item; i++) {
                    System.out.println("\n\t" + (i + 1) + ". " + list[i] + "\n");
                }
                System.out.println(horizontal);
            }
        }

        System.out.println(horizontal + "\n\t" + closingMsg + "\n" + horizontal);
        
    }
}
