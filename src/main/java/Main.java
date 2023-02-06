import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsolePrinter.greet();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();

            if (userInput.isEmpty()) {
                System.out.println("Please enter one of the available command");
                ConsolePrinter.helpAll();
                ConsolePrinter.breakLine();
                continue;
            }

            if (userInput.equals("bye")) {
                sc.close();
                break;
            }

            Genesis.askGenesis(userInput);
        }

        ConsolePrinter.goodbye();
    }
}
