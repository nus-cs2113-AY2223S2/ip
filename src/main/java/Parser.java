import duke.Ui;
import duke.TaskList;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser that parses the user's input.
 * It is a child of the Task class.
 */
public class Parser {

    /**
     * Parses the user's input and executes the appropriate command.
     * @param command The user's input.
     * @throws IOException
     */
    public static void parse(String command) throws IOException {
        String[] commandArray = command.split(" ");
        String commandType = commandArray[0];
        switch (commandType) {
        case "bye":
            Ui.printByeMessage();
            Storage.saveFile(TaskList.getList());
            System.exit(0);
            break;
        case "list":
            Ui.printTaskList(TaskList.getList());
            break;
        case "mark":
            try {
                int index = Integer.parseInt(commandArray[1]) - 1;
                TaskList.markTask(index);
                Ui.printTaskDoneMessage(TaskList.getTask(index));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                Ui.printInvalidIndex();
            }
            break;
        case "unmark":
            try {
                int index = Integer.parseInt(commandArray[1]) - 1;
                TaskList.unmarkTask(index);
                Ui.printTaskUnDoneMessage(TaskList.getTask(index));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                Ui.printInvalidIndex();
            }
            break;
        case "delete":
            try {
                int index = Integer.parseInt(commandArray[1]) - 1;
                Ui.printTaskDeletedMessage(index, TaskList.getSize()-1);
                TaskList.deleteTask(index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                Ui.printInvalidIndex();
            }
            break;
        case "todo":
            try {
                String description = command.substring(5);
                TaskList.addTask(new Todo(description));
                Ui.printTaskAddedMessage(TaskList.getTask(TaskList.getSize() - 1), TaskList.getSize());
            } catch (StringIndexOutOfBoundsException e) {
                Ui.printEmptyDescription();
            }
            break;
        case "deadline":
            try {
                String description = command.substring(9, command.indexOf("/by"));
                String by = command.substring(command.indexOf("/by") + 4);
                TaskList.addTask(new Deadline(description, by));
                Ui.printTaskAddedMessage(TaskList.getTask(TaskList.getSize() - 1), TaskList.getSize());
            } catch (StringIndexOutOfBoundsException e) {
                Ui.printEmptyDescription();
            } catch (DateTimeParseException e) {
                Ui.printInvalidDateTime();
            }
            break;
        case "event":
            try {
                String description = command.substring(6, command.indexOf("/from"));
                String from = command.substring(command.indexOf("/from") + 6, command.indexOf("/to")-1);
                String to = command.substring(command.indexOf("/to") + 4);
                TaskList.addTask(new Event(description, from, to));
                Ui.printTaskAddedMessage(TaskList.getTask(TaskList.getSize() - 1), TaskList.getSize());
            } catch (StringIndexOutOfBoundsException e) {
                Ui.printEmptyDescription();
            } catch (DateTimeParseException e) {
                Ui.printInvalidDateTime();
            }
            break;
        case "find":
            try {
                String keyword = command.substring(5);
                Ui.printFindMessage(TaskList.findTask(keyword));
            } catch (StringIndexOutOfBoundsException e) {
                Ui.printEmptyDescription();
            }
            break;
        default:
            Ui.printInvalidCommand();
            break;
        }
    }

    /**
     * Returns whether the user has entered the exit command.
     * @return Whether the user has entered the exit command.
     */
    public static boolean isExit() {
        return false;
    }
}
