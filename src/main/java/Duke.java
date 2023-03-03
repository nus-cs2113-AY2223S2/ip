import java.util.Scanner;

public class Duke {
    static final int COMMAND_INDEX = 0;
    public static final int STATUSTYPE_DONE = 1;
    public static final int STATUSTYPE_NOTDONE = 0;

    public static void main(String[] args) {
        Ui.welcomeMessage();
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine().trim();
            String inputCommand = userInput.split(" ", 2)[COMMAND_INDEX];
            inputCommand = inputCommand.toLowerCase();
            switch (inputCommand) {
            case "exit":
            case "bye":
                Ui.exitMessage();
                return;
            case "list":
                Commands.showList();
                break;
            case "mark":
                Commands.markTask(userInput, STATUSTYPE_DONE);
                break;
            case "unmark": {
                Commands.markTask(userInput, STATUSTYPE_NOTDONE);
                break;
            }
            case "delete": {
                Commands.deleteTask(userInput);
                break;
            }
            case "save": {
                Storage.writeToTaskList();
                break;
            }
            case "todo": {
                Commands.addTodoTask(userInput);
                break;
            }
            case "deadline": {
               Commands.addDeadlineTask(userInput);
                break;
            }
            case "event":
                Commands.addEventTask(userInput);
                break;
            case "search":
                Commands.searchTask(userInput);
                break;
            default:
                Commands.invalidCommand();
                break;
            }
        }
    }
}




