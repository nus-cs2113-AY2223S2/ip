import genesis.Genesis;

import java.util.Scanner;

import utility.Ui;

public class Main {
    public static void main(String[] args) {
        Ui.greet();

        Genesis genesis = new Genesis();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.isEmpty()) {
                System.out.println("Please enter one of the available command");
                Ui.helpAll();
                Ui.breakLine();
                continue;
            }

            if (userInput.equals("bye")) {
                sc.close();
                break;
            }

            genesis.askGenesis(userInput);
        }

        Ui.goodbye();
    }
}
