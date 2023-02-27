package duke;

import duke.command.Message;
import duke.command.Parser;
import duke.task.List;
import duke.task.TaskType;

public class Duke {
    public static void main(String[] args) {
        startDuke();
        runDuke();
        closeDuke();
    }

    private static void closeDuke() {
        Message.line();
        Data.writeFile();
        Message.bye();
        Message.line();
    }

    private static void startDuke() {
        Message.line();
        Message.hello();
        Data.loadData();
        Message.line();
    }

    private static void runDuke() {
        String userCommand, userInputDetails;

        do {
            Parser.getUserInput();
            userCommand = Parser.getUserCommand();
            switch(userCommand) {
            case "list":
                userInputDetails = Parser.getUserInputDetails();
                if (userInputDetails.equals("")) {
                    List.printList();
                } else {
                    Message.unknownCommandHandler();
                }
                break;
            case "mark":
                userInputDetails = Parser.getUserInputDetails();
                try {
                    List.markDone(Integer.parseInt(userInputDetails.trim()));
                } catch (NumberFormatException e) {
                    Message.line();
                    System.out.println("Please input a list index to mark!");
                    Message.line();
                } catch (IndexOutOfBoundsException e) {
                    Message.line();
                    System.out.println("The list index you have input is not in the list!");
                    Message.line();
                }
                break;
            case "unmark":
                userInputDetails = Parser.getUserInputDetails();
                try {
                    List.markUndone(Integer.parseInt(userInputDetails.trim()));
                } catch (NumberFormatException e) {
                    Message.line();
                    System.out.println("Please input a list index to mark!");
                    Message.line();
                } catch (IndexOutOfBoundsException e) {
                    Message.line();
                    System.out.println("The list index you have input is not in the list!");
                    Message.line();
                }
                break;
            case "todo":
                userInputDetails = Parser.getUserInputDetails();
                List.addTask(userInputDetails, TaskType.TODO);
                break;
            case "deadline":
                userInputDetails = Parser.getUserInputDetails();
                List.addTask(userInputDetails, TaskType.DEADLINE);
                break;
            case "event":
                userInputDetails = Parser.getUserInputDetails();
                List.addTask(userInputDetails, TaskType.EVENT);
                break;
            case "delete":
                userInputDetails = Parser.getUserInputDetails();
                try {
                    List.removeTask(Integer.parseInt(userInputDetails.trim()));
                } catch (NumberFormatException e) {
                    Message.line();
                    System.out.println("Please input a list index to delete!");
                    Message.line();
                } catch (IndexOutOfBoundsException e) {
                    Message.line();
                    System.out.println("The list index you have input is not in the list!");
                    Message.line();
                }
                break;
            case "find":
                userInputDetails = Parser.getUserInputDetails();
                List.findInList(userInputDetails.trim());
                break;
            case "bye":
                break;
            default:
                Message.unknownCommandHandler();
            }
            Data.writeFile();
        } while (!(userCommand.equals("bye")));
    }
}
