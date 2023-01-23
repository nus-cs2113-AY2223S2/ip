import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        String[] store = new String[100];
        int counter = 0;
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < counter; i++) {
                    System.out.println(i+1 + ": " + store[i]);
                }
                System.out.println("____________________________________________________________\n");
                continue;
            }
            store[counter] = line;
            counter++;
            System.out.println("____________________________________________________________\n" +
                    "added: " + line + "\n" +
                    "____________________________________________________________\n");
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
