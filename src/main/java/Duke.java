import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static final String line = "____________________________________________________________";
    public static void main(String[] args) {
        // Greet the user
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner in = new Scanner(System.in);
        ArrayList<String> items = new ArrayList<String>();
        while (true) {
            String input = in.nextLine();

            System.out.println(line);
            if (input.equals("list")) {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i+1)+". "+items.get(i));
                }
            } else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else {
                items.add(input);
                System.out.println("added: "+input);
            }
            System.out.println(line);
        }
        in.close();
    }
}
