import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner input = new Scanner(System.in);
        String text = input.nextLine();
        String[] list = new String[100];
        int j = 0;
        while (!text.equals("bye")) {
            if (text.equals("list")) {
                for (int i = 0; i < list.length; i += 1) {
                    if (list[i] == null) {
                        break;
                    } else {
                        System.out.println(i + 1 + ". " + list[i]);
                    }
                }
            } else {
                list[j] = text;
                System.out.println("added: " + text);
                j += 1;
            }
            text = input.nextLine();
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}