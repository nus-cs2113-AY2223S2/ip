import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static String line = "____________________________________________________________";
    private static ArrayList<Tasks> list = new ArrayList<Tasks>();
    public static void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }
    public static void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
    public static void printTask(int i) {
        System.out.println((i+1) + ".[" + list.get(i).getStatus() + "] " + list.get(i).getTask());
    }
    public static void processInput(String input) {
        if (input.equalsIgnoreCase("list")) {
            if(list.size() == 0) {
                System.out.println(line);
                System.out.println("Nothing on your list yet!");
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i += 1) {
                    printTask(i);
                }
                System.out.println(line);
            }
        } else if (input.split("\\s+")[0].equalsIgnoreCase("mark")) {
            int itemNum = Integer.parseInt(input.split("\\s+")[1]) - 1;
            if(itemNum > list.size()-1) {
                System.out.println(line);
                System.out.println("Item does not exist!");
                System.out.println(line);
                return;
            }
            list.get(itemNum).markDone();
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + list.get(itemNum).getStatus() + "] " + list.get(itemNum).getTask());
            System.out.println(line);
        } else if (input.split("\\s+")[0].equalsIgnoreCase("unmark")) {
            int itemNum = Integer.parseInt(input.split("\\s+")[1]) - 1;
            if(itemNum > list.size()-1) {
                System.out.println(line);
                System.out.println("Item does not exist!");
                System.out.println(line);
                return;
            }
            list.get(itemNum).unmarkDone();
            System.out.println(line);
            System.out.println("OK! I've marked this task as not done yet:");
            System.out.println("[" + list.get(itemNum).getStatus() + "] " + list.get(itemNum).getTask());
            System.out.println(line);
        } else {
            Tasks t = new Tasks(input);
            System.out.println(line);
            System.out.println("added : " + input);
            System.out.println(line);
            list.add(t);
        }
    }
    public static void main(String[] args) {
        greet();
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if(input.equalsIgnoreCase("bye")) {
                bye();
                break;
            }
            processInput(input);
        }
    }
}
