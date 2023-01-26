import java.util.Scanner;

public class Duke {
    public static void read_Command() {
        Scanner command = new Scanner(System.in);
        String input_Command = command.nextLine();
        if ("bye".equals(input_Command)) {
            System.out.println("I look forward to seeing you again! Goodbye!");
        } else {
            System.out.println(input_Command);
            read_Command();
        }
    }
    public static void main(String arguments[]) {
        System.out.println("Hi there! My name is Coffee");
        System.out.println("How can I help you today?");
        read_Command();
    }
}
