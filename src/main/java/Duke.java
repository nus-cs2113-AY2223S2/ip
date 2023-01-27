import java.util.Scanner;

public class Duke {
    public static void main(String arguments[]) {
        System.out.println("Hi there! My name is Coffee");
        System.out.println("How can I help you today?");
        Scanner command = new Scanner(System.in);
        String input_Command = command.nextLine();
        while (!"bye".equals(input_Command)) {
                System.out.println(input_Command);
                command = new Scanner(System.in);
                input_Command = command.nextLine();
            }
        System.out.println("I look forward to seeing you again! Goodbye!");
        }
    }
