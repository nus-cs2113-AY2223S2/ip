import java.util.Scanner;
public class Duke {
    private static String line = "____________________________________________________________";
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
    public static void processInput() {
        while(true) {
            Scanner in  = new Scanner(System.in);
            String input = in.nextLine();
            if(input.toLowerCase().equals("bye")) {
                bye();
                break;
            }
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
    }
    public static void main(String[] args) {
        greet();
        processInput();
    }
}
