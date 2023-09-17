import java.util.Scanner;
import java.io.IOException;

/**
 * This <code>ThomasShelby</code> class is used to run the entire ThomasShelby bot application.
 * It calls methods from various classes (TaskManager, Ui, Parser, and Data) to provide full feature functionality
 * @version v0.2
 * @since 2023-02-24
 *
 * @see TaskManager
 * @see Ui
 * @see Parser
 * @see Data
 */
public class ThomasShelby {
    public static void main(String[] args) throws IOException {
        Ui.printWelcomeMessage();
        Data.loadData(TaskManager.taskManager);
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String command = in.nextLine();
                String[] parsedCommand = Parser.parseCommand(command);
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
                Ui.printArrayIndexOutOfBoundsExceptionErrorMessage(e);
            } catch (IncompleteTaskException e) {
                Ui.printIncompleteTaskExceptionErrorMessage();
            }
        }
    }
}