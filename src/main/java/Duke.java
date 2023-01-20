import java.util.Scanner;

public class Duke {
    static String line = "\t____________________________________________________________";
    static String command_Bye = "bye";

    public static void Greet() {
        System.out.println(line);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?\n");
        System.out.println(line);
    }

    public static void Bye() {
        System.out.println(line);
        System.out.println("\tBye. Hope to see you again soon!\n");
        System.out.println(line);
    }

    public static void main(String[] args) {
        Greet();
        Scanner in = new Scanner(System.in);
        String user_command = new String();
        user_command = in.nextLine();
        while (!user_command.equals(command_Bye)) {
            System.out.println(line);
            System.out.println("\t" + user_command);
            System.out.println(line);
            user_command = in.nextLine();
        }
        Bye();
    }
}
