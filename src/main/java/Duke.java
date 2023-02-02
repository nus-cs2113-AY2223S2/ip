import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String userCommand, userInputDetails;

        Message.hello();
        
        do {
            userCommand = inputScanner.next();
            switch(userCommand) {
            case "list":
                List.printList();
                break;
            case "mark":
                userInputDetails = inputScanner.next();
                List.markDone(Integer.parseInt(userInputDetails));
                break;
            case "unmark":
                userInputDetails = inputScanner.next();
                List.markUndone(Integer.parseInt(userInputDetails));
                break;
            case "todo":
                userInputDetails = inputScanner.nextLine();
                List.addTask(userInputDetails, TaskType.TODO);
                break;
            case "deadline":
                userInputDetails = inputScanner.nextLine();
                List.addTask(userInputDetails, TaskType.DEADLINE);
                break;
            case "event":
                userInputDetails = inputScanner.nextLine();
                List.addTask(userInputDetails, TaskType.EVENT);
                break;
            default:
                Message.unknownCommandHandler();
            }
        } while (!(userCommand.equals("bye")));

        Message.bye();
        Message.line();
    }
}
