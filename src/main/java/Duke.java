import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class Duke {

    /** Language state of the program. */
    public static boolean isSinglish = false;
    /** A resizeable array to store all the tasks entered from the user. */
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Greeting.sayHello(isSinglish);

        Path storageFilePath = Storage.loadStorageFolderAndFile();
        Storage.initializeListWithSavedTasks(storageFilePath);

        boolean isQuit = false;

        while (!isQuit) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            String[] commands = line.split(" ");
            boolean isBye = commands[0].equals(Constants.BYE_COMMAND);
            boolean isValidChangeLangCommand = commands[0].equals(Constants.CHANGE_COMMAND) && commands.length == 2 && commands[1].equals(Constants.LANG_COMMAND);
            boolean isValidPrintListCommand = commands[0].equals(Constants.LIST_COMMAND) && commands.length == 1;
            boolean isValidMarkOrUnmarkCommand = (commands[0].equals(Constants.MARK_COMMAND) || commands[0].equals(Constants.UNMARK_COMMAND)) && commands.length == 2;
            boolean isValidDeleteCommand = commands[0].equals(Constants.DELETE_COMMAND) && commands.length == 2;
            boolean isTodo = commands[0].equals(Constants.TODO_COMMAND);
            boolean isDeadline = commands[0].equals(Constants.DEADLINE_COMMAND);
            boolean isEvent = commands[0].equals(Constants.EVENT_COMMAND);

            try {
                if (isBye) {
                    Greeting.sayGoodbye(isSinglish);
                    isQuit = true;
                    break;
                } else if (isValidChangeLangCommand) {
                    Greeting.changeLanguage();
                } else if (isValidPrintListCommand) {
                    Printer.printList();
                } else if (isValidMarkOrUnmarkCommand) {
                    Marker.checkAndMarkTask(commands);
                    Storage.updateTasksSaved(storageFilePath);
                } else if (isValidDeleteCommand) {
                    Deleter.checkAndDeleteTask(commands);
                    Storage.updateTasksSaved(storageFilePath);
                } else if (isTodo) {
                    Adder.addTodo(line);
                    Storage.updateTasksSaved(storageFilePath);
                } else if (isDeadline) {
                    Adder.addDeadline(line);
                    Storage.updateTasksSaved(storageFilePath);
                } else if (isEvent) {
                    Adder.addEvent(line);
                    Storage.updateTasksSaved(storageFilePath);
                } else {
                    throw new IllegalAccessException();
                }
            } catch (IndexOutOfBoundsException e) {
                Greeting.warnOutOfRange(isSinglish);
            } catch (NumberFormatException e) {
                Greeting.warnWrongSyntax(isSinglish);
            } catch (IllegalAccessException e) {
                Greeting.warnWrongSyntax(isSinglish);
            } catch (MissingFormatArgumentException e) {
                Greeting.warnEmptyDesc(isSinglish);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
