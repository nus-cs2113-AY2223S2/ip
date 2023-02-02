import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String userCommand, userInputDetails;

        // Greeting the user
        Messages.hello();
        
        do {
            userCommand = inputScanner.next();
            switch(userCommand) {
            case "list":
                Lists.printList();
                break;
            case "mark":
                userInputDetails = inputScanner.next();
                Lists.markDone(Integer.parseInt(userInputDetails));
                break;
            case "unmark":
                userInputDetails = inputScanner.next();
                Lists.markUndone(Integer.parseInt(userInputDetails));
                break;
            case "todo":
                userInputDetails = inputScanner.nextLine();
                Lists.addItem(userInputDetails, TaskType.TODO);
                break;
            case "deadline":
                userInputDetails = inputScanner.nextLine();
                Lists.addItem(userInputDetails, TaskType.DEADLINE);
                break;
            case "event":
                userInputDetails = inputScanner.nextLine();
                Lists.addItem(userInputDetails, TaskType.EVENT);
                break;
            default:
                Messages.unknownCommandHandler();
            }
        } while (!(userCommand.equals("bye")));
        Messages.bye();
        Messages.line();
    }
}
