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
                taskIndex = Integer.parseInt(userCommand.split(" ")[1])-1;
                MainFunctions.markTask(taskIndex, storedUserTasks);
                break;
            case "unmark":
                taskIndex = Integer.parseInt(userCommand.split(" ")[1])-1;
                MainFunctions.unmarkTask(taskIndex, storedUserTasks);
                break;
            case "todo":
                description = userCommand.substring(5);
                MainFunctions.createTodo(description, storedUserTasks);
                break;
            case "deadline":
                int indexOfBy = userCommand.indexOf("/by");
                description = userCommand.substring(9,indexOfBy-1);
                by = userCommand.substring(indexOfBy+4);
                MainFunctions.createDeadline(description, by, storedUserTasks);
                break;
            case "event":
                int indexOfFrom = userCommand.indexOf("/from");
                int indexOfTo = userCommand.indexOf("/to");
                description = userCommand.substring(6,indexOfFrom-1);
                from = userCommand.substring(indexOfFrom+6,indexOfTo-1);
                to = userCommand.substring(indexOfTo+4);
                MainFunctions.createEvent(description, from, to, storedUserTasks);
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
