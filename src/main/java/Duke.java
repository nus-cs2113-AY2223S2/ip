import java.util.Scanner;

public class Duke {

    public static int userTextCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MainFunctions.printWelcome();

        String userCommand = scanner.nextLine();

        Task[] storedUserTasks = new Task[100];
        boolean isExit = false;
        int taskIndex;
        String description, by, from, to;

        while(!isExit){
            switch (userCommand.split(" ")[0]){
            case "list":
                MainFunctions.listTasks(userTextCount, storedUserTasks);
                break;
            case "bye":
                isExit = true;
                break;
            case "mark":
                MainFunctions.markTask(userCommand, storedUserTasks);
                break;
            case "unmark":
                MainFunctions.unmarkTask(userCommand, storedUserTasks);
                break;
            case "todo":
                MainFunctions.createTodo(userCommand, storedUserTasks);
                break;
            case "deadline":
                MainFunctions.createDeadline(userCommand, storedUserTasks);
                break;
            case "event":

                MainFunctions.createEvent(userCommand, storedUserTasks);
                break;
            default:
                MainFunctions.invalidInput();
                break;
            }
            if(!isExit) {
                userCommand = scanner.nextLine();
            }
        }

        MainFunctions.printGoodbye();

    }
}
