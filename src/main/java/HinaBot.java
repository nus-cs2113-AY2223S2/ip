import java.util.Scanner;

public class HinaBot {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        showGreeting();
        while (true) {
            line = in.nextLine();
            handleCommand(line);
        }
    }

    private static void showGreeting() {
        System.out.println("Hello master!");
        System.out.println("What are your orders?");
    }

    public static void handleCommand(String command) {
        if (!command.equalsIgnoreCase("bye")) {
            System.out.println(command);
        }
        else {
            System.out.println("Goodbye master, let's meet again soon...");
            System.exit(0);
        }
    }
}
