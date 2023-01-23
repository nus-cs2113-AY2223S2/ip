import java.util.Scanner;
public class Duke {
    public static void greet() {
        String line = "____________________________________________________________";
        String greeting = " Hello! I'm Duke\n"
                + " What can I do for you?";
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);
    }
    public static void bye() {
        String line = "____________________________________________________________";
        String goodbye = " Bye. Hope to see you again soon!";
        System.out.println(line);
        System.out.println(goodbye);
        System.out.print(line);
    }
    public static void echo(String input) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(" " + input);
        System.out.println(line);
    }
    public static void main(String[] args) {
        greet();
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (input.equals("bye") == false) {
            echo(input);
            in = new Scanner(System.in);
            input = in.nextLine();
        }
        bye();
    }
}
