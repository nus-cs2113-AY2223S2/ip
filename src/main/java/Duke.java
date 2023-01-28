import java.util.Locale;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("what can i do for you");

        String input = myObj.nextLine();
        String[] todo = new String[100];
        int c = 0;
        while(!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                System.out.println("----------------------");
                for (int i = 0; i < todo.length && todo[i] != null; i++) {
                    System.out.println(i + ". " + todo[i]);
                }
                System.out.println("----------------------");
            } else {
                todo[c] = input;
                System.out.println("Added: " + input);

            }
            input = myObj.nextLine();
            c++;

        }
        System.out.println("Bye! see you soon!");
    }
}
