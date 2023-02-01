import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n"
        );
        ArrayList<Task> items = new ArrayList<Task>();
        String input = "";

        while (!input.equals("bye")) {
            input = sc.next();
            int order;
            Task newTask;
            String prompt;
            String description;

            if (input.equals("mark")) {
                order = sc.nextInt();
                items.get(order - 1).setMark(true);
                System.out.println("____________________________________________________________\n" + "  " + "Nice! I've marked this task as done:\n" + "[X] " + items.get(order - 1).getDescription() + "\n" + "____________________________________________________________\n");

            } else if (input.equals("unmark")) {
                order = sc.nextInt();
                items.get(order - 1).setMark(false);
                System.out.println("____________________________________________________________\n" + "  " + " OK, I've marked this task as not done yet:\n" + "[X] " + items.get(order - 1).getDescription() + "\n" + "____________________________________________________________\n");
            } else if (input.equals("list")) {
                System.out.println("\t____________________________________________________________\r\n"
                            + "\t Here are the tasks in your list:");
                    for (int i = 0; i < items.size(); i++) {
                        System.out.println("\t " + (i + 1) + ". " + items.get(i).toString());
                    }
                    System.out.println("\n\t____________________________________________________________\r\n");
            } else if (input.equals("todo")){
                prompt = sc.nextLine();
                newTask = new Todo(prompt.trim());
                items.add(newTask);
            }
            else if (input.equals("deadline")){
                prompt = sc.nextLine();
                int dividerPosition = prompt.indexOf("/by");
                description = prompt.substring(0, dividerPosition).trim();
                String by = prompt.substring(dividerPosition + 3).trim();
                newTask = new Deadline(description, by);
                items.add(newTask);
            }
            else if (input.equals("event")){
                prompt = sc.nextLine();
                    int divider1Position = prompt.indexOf("/from");
                    int divider2Position = prompt.indexOf("/to");
                    description = prompt.substring(0, divider1Position).trim();
                    String from = prompt.substring(divider1Position + 5, divider2Position).trim();
                    String to = prompt.substring(divider2Position + 3).trim();
                    newTask = new Event(description, from, to);
                    items.add(newTask);
            }



        }


        System.out.println("____________________________________________________________\n" + "  " + "Bye. Hope to see you again soon!" + "\n" + "____________________________________________________________\n");
    }
}
