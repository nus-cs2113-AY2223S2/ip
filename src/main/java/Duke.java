import java.util.Scanner;

public class Duke {
    public static final String EXIT_CMD = "exit";
    public static final String BYE_CMD = "bye";
    public static void main(String[] args) {
        Ui.welcome();
        Scanner in = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit) {
            String userInput = in.nextLine().trim();
            String inputCommand = Parser.ParseInputCommand(userInput);
            switch (inputCommand) {
            case EXIT_CMD:
            case BYE_CMD:
                isExit = true;
                Ui.exitMessage();
                break;
            default:
                Parser.ParseCommand(inputCommand,userInput);
                break;
            }
        }
    }
}




