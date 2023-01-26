import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String userInput;
        String[] userInputs;

        // Greeting the user
        Messages.hello();
        
        do {
            userInput = inputScanner.nextLine();
            userInputs = userInput.split(" ", 0);
            switch(userInputs[0]) {
            case "list":
                Lists.printList();
                break;
            case "mark":
                Lists.markDone(Integer.parseInt(userInputs[1]));
                break;
            case "unmark":
                Lists.markUndone(Integer.parseInt(userInputs[1]));
                break;
            default:
                Lists.addItem(userInput);
            }
        } while (!(userInput.equals("bye")));

        Messages.bye();
        Messages.line();
    }
}
