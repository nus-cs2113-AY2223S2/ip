import java.util.ArrayList;
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
        System.out.println("What can I do for you?\n");
        ArrayList<String> inputList = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int a = 1;
                for (String i : inputList) {
                    System.out.println(a + ". " + i);
                    a++;
                }
            } else {
                System.out.println("added: " + input);
                inputList.add(input);
            }
            input = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
