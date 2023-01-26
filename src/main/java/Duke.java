import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static final String line = "____________________________________________________________";

    public static void main(String[] args) {
        // Greet the user
        System.out.println(line);
        System.out.println("Hello! I'm your personal shopping checklist.");
        System.out.println("What items do you need to buy today?");
        System.out.println(line);

        // Take in user inputs and loop until user says bye
        Scanner in = new Scanner(System.in);
        ArrayList<Item> items = new ArrayList<Item>();
        while (true) {
            try {
                String input = in.nextLine();
                System.out.println(line);

                if (input.equals("list")) {
                    // Prints the list of items user has added
                    System.out.println("Here are the items in your shopping list:");
                    for (int i = 0; i < items.size(); i++) {
                        Item item = items.get(i);
                        System.out.println((i+1) + ". [" + item.getStatus() + "] " + item.getDescription());
                    }
                } else if (input.split(" ")[0].equals("mark")) {
                    // Mark the item that user has done
                    int num = Integer.parseInt(input.split(" ")[1]);
                    Item item = items.get(num-1);
                    item.setStatus(true);

                    System.out.println("Good job! I've marked this item as done:");
                    System.out.println("[" + item.getStatus() + "] " + item.getDescription());
                } else if (input.split(" ")[0].equals("unmark")) {
                    // Unmark the item that user has not done
                    int num = Integer.parseInt(input.split(" ")[1]);
                    Item item = items.get(num-1);
                    item.setStatus(false);

                    System.out.println("OK, I've marked this item as not done yet:");
                    System.out.println("[" + item.getStatus() + "] " + item.getDescription());
                } else if (input.equals("bye")) {
                    // Exits user
                    System.out.println("Bye. Thanks for using me!");
                    System.out.println(line);
                    break;
                } else {
                    // For other inputs, add into shopping list
                    Item newItem = new Item(input);
                    items.add(newItem);
                    System.out.println(input + " has been added to your shopping list.");
                }
            } catch(Exception e) {
                // When user enters a list no. that is out of arraylist for mark/unmark
                System.out.println("Please input a valid command.");
            }
            
            System.out.println(line);
        }
        in.close();
    }
}
