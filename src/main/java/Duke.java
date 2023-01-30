import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo =  "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(logo);
        Scanner in = new Scanner(System.in);
        String line;
        //String[] todo = new String[100];
        while(true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            System.out.println("added:"+ line);
        }
    }
}
