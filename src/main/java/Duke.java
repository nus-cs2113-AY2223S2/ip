import java.util.Scanner;

public class Duke {
    static String line = "\t____________________________________________________________";
    static String commandBye = "bye";
    static String commandList = "list";
    static String[] texts = new String[100];
    static int textCount = 0;

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

        do {
            user_command = in.nextLine();
            if (user_command.equals(commandList)) {
                int count = 1;
                System.out.println(line);
                for (int index = 0; index < textCount; index++) {
                    System.out.print("\t" + count + ". ");
                    System.out.println("\t" + texts[index]);
                    count++;
                }
                System.out.println(line);
            } else if (user_command.equals((commandBye))) {
                Bye();
            } else {
                texts[textCount] = user_command;
                textCount++;
                System.out.println(line);
                System.out.println("\t" + "added: " + user_command);
                System.out.println(line);
            }
        } while (!user_command.equals(commandBye));
    }
}
