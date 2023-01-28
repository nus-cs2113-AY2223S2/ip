import java.util.Scanner;

public class Buddy {
    public static void main(String[] args) {
        String greeting = "Hello there! I'm Buddy\n"
                + "How may I assist you?";
        String exitMessage = "Hope I was of help to you! Have a great day and see you again, Buddy :)";
        String divider = "________________________________________________________________________________";

        System.out.println(divider);
        System.out.println(greeting);
        System.out.println(divider);

        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();

        while (! command.equals("bye")){
            System.out.println(divider);
            System.out.println(command);
            System.out.println(divider);
            command = in.nextLine();
        }

        System.out.println(divider);
        System.out.println(exitMessage);
        System.out.println(divider);

    }
}
