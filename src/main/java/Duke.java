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
        System.out.println(" added: " + input);
        System.out.println(line);
    }
    public static void add(String[] texts, String input, int counter) { // work on this
        texts[counter] = input;
        echo(input);
    }
    public static void list(String[] texts, int counter) {
        String line = "____________________________________________________________";
        System.out.println(line);
        if (counter == 0) {
            System.out.println("The list is empty!");
        } else {
            for (int i = 0; i < counter; i += 1) {
                System.out.println(" " + (i + 1) + ". " + texts[i]);
            }
        }
        System.out.println(line);
    }
    public static void main(String[] args) {
        greet();
        String texts[] = new String[100];
        int counter = 0;
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (input.equals("bye") == false) {
            if (input.equals("list")) {
                list(texts, counter);
            } else {
                add(texts, input, counter);
                counter += 1;
            }
            in = new Scanner(System.in);
            input = in.nextLine();
        }
        bye();
    }
}
