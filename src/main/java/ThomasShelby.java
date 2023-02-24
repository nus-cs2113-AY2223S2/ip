import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class ThomasShelby {
    public static void main(String[] args) throws IOException {
        System.out.print("Good day, I'm Thomas Shelby.\nTo what do I owe the pleasure?\n");
        File data = new File("data/data.txt");
        Data.loadData(TaskManager.taskManager);
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String command = in.nextLine();
                String[] parsedCommand = Parser.parseCommand(command); // user input split into individual words
                switch (parsedCommand[0]) {
                case "bye":
                    Data.saveData(TaskManager.taskManager);
                    System.out.println("Cheers.");
                    return;
                case "list":
                    TaskManager.listTasks(TaskManager.taskManager);
                    break;
                case "todo":
                    TaskManager.addToDo(parsedCommand);
                    break;
                case "deadline":
                    TaskManager.addDeadline(parsedCommand);
                    break;
                case "event":
                    TaskManager.addEvent(parsedCommand);
                    break;
                case "mark":
                    TaskManager.markTask(parsedCommand);
                    break;
                case "unmark":
                    TaskManager.unmarkTask(parsedCommand);
                    break;
                case "delete":
                    TaskManager.deleteTask(parsedCommand);
                    break;
                case "find":
                    TaskManager.listTasks(TaskManager.findTask(parsedCommand));
                    break;
                default:
                    throw new IncompleteTaskException();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Something's wrong: " + e);
                System.out.println("You probably didn't include the task or the timeframe.");
            } catch (IncompleteTaskException e) {
                System.out.println("Don't know what that means comrade.");
            }
        }
    }
}