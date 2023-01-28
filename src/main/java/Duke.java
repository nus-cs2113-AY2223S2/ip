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
        ArrayList<Boolean> marked = new ArrayList<Boolean>();
        
        while((input = in.nextLine()) != "") {
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                in.close();
                return;
            }
            else if (input.equals("list")) {
                
                for(int i = 0; i < items.size(); i++) {
                    System.out.println(i+1 +". " +  (marked.get(i) ? "[X] " : "[ ] ")   + items.get(i));
                    
                }
            }
            else if (input.startsWith("mark")) {
                String[] strArray = input.split(" ");
                int num = Integer.parseInt(strArray[1]);
                marked.set(num-1, true);
                System.out.println("Nice! I've marked this task as done:\n" + "[X] " + items.get(num-1));
            }
            else if (input.startsWith("unmark")) {
                String[] strArray = input.split(" ");
                int num = Integer.parseInt(strArray[1]);
                marked.set(num-1, false);
                System.out.println("Ok, I've marked this task as not done yet:\n" + "[ ] " + items.get(num-1));
            }
            else {
                items.add(input);
                marked.add(false);
                System.out.println("added: " + input);
            }
        }
        
    }
}
