import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        System.out.println("Good day. YodaBot, I am.");
        System.out.println("Assistance, you need?");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                System.out.println("See you soon, I hope. Goodbye.");
                break;
            }
            else System.out.println(command);
        }
    }
}
