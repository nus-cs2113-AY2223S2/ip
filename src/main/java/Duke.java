import java.util.Scanner;
import java.util.ArrayList;

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
        Scanner in = new Scanner(System.in);
        String input;
        ArrayList<String> items = new ArrayList<String>();
        while((input = in.nextLine()) != "") {
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                in.close();
                return;
            }
            else if (input.equals("list")) {
                int count = 1;
                for(String list_item : items) {
                    System.out.println(Integer.toString(count) +". " + list_item);
                    count++;
                }
            }
            items.add(input);
            System.out.println("added: " + input);
        }
        
    }
}
