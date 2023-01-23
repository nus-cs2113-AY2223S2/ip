import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static String line = "____________________________________________________________";
    private static ArrayList<String> list = new ArrayList<String>();
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
    public static void processInput(String input) {
        if (input.toLowerCase().equals("list")) {
            if(list.size() == 0) {
                System.out.println("Nothing on your list yet!");
            } else {
                for (int i = 0; i < list.size(); i += 1) {
                    System.out.println((i+1) + ". " + list.get(i));
                }
            }
        } else {
            System.out.println("added : " + input);
            list.add(input);
        }
    }
    public static void main(String[] args) {
        greet();
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if(input.toLowerCase().equals("bye")) {
                bye();
                break;
            }
            processInput(input);
        }
    }
}
