package duke;

import duke.command.Ui;
import duke.command.Parser;
import duke.task.TaskList;
import duke.task.TaskType;

/**
 * A <code>Duke</code> object handles the running of the Duke chatbot application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * The class constructor that instantiates the <code>Ui</code>,
     * <code>Storage</code>, and populate the <code>TaskList</code></code> objects.
     * It uses the file path of the data file to load previous data into the <code>TaskList</code>.
     *
     * @param filePath The file path of the data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.loadData();
    }

    /**
     * Starts the Duke application by printing a welcome message.
     */
    private void startDuke() {
        ui.line();
        ui.hello();
        ui.line();
    }

    /**
     * Closes the Duke application by saving the data and printing a closing message.
     */
    private void closeDuke() {
        ui.line();
        Storage.writeFile();
        ui.bye();
        ui.line();
    }

    /**
     * Runs the Duke application.
     */
    private void runDuke() {
        startDuke();

        String userCommand, userInputParameter;

        do {
            Parser.getUserInput();
            userCommand = Parser.getUserCommand();
            switch(userCommand) {
            case "list":
                userInputParameter = Parser.getUserInputParameter();
                if (userInputParameter.equals("")) {
                    TaskList.printList();
                } else {
                    ui.unknownCommandHandler();
                }
                break;
            case "mark":
                userInputParameter = Parser.getUserInputParameter();
                try {
                    TaskList.markDone(Integer.parseInt(userInputParameter.trim()));
                } catch (NumberFormatException e) {
                    ui.line();
                    System.out.println("Please input a list index to mark!");
                    ui.line();
                } catch (IndexOutOfBoundsException e) {
                    ui.line();
                    System.out.println("The list index you have input is not in the list!");
                    ui.line();
                }
                break;
            case "unmark":
                userInputParameter = Parser.getUserInputParameter();
                try {
                    TaskList.markUndone(Integer.parseInt(userInputParameter.trim()));
                } catch (NumberFormatException e) {
                    ui.line();
                    System.out.println("Please input a list index to mark!");
                    ui.line();
                } catch (IndexOutOfBoundsException e) {
                    ui.line();
                    System.out.println("The list index you have input is not in the list!");
                    ui.line();
                }
                break;
            case "todo":
                userInputParameter = Parser.getUserInputParameter();
                TaskList.addTask(userInputParameter, TaskType.TODO);
                break;
            case "deadline":
                userInputParameter = Parser.getUserInputParameter();
                TaskList.addTask(userInputParameter, TaskType.DEADLINE);
                break;
            case "event":
                userInputParameter = Parser.getUserInputParameter();
                TaskList.addTask(userInputParameter, TaskType.EVENT);
                break;
            case "delete":
                userInputParameter = Parser.getUserInputParameter();
                try {
                    TaskList.removeTask(Integer.parseInt(userInputParameter.trim()));
                } catch (NumberFormatException e) {
                    ui.line();
                    System.out.println("Please input a list index to delete!");
                    ui.line();
                } catch (IndexOutOfBoundsException e) {
                    ui.line();
                    System.out.println("The list index you have input is not in the list!");
                    ui.line();
                }
                break;
            case "find":
                userInputParameter = Parser.getUserInputParameter();
                TaskList.findInList(userInputParameter.trim());
                break;
            case "bye":
                break;
            default:
                ui.unknownCommandHandler();
            }
            Storage.writeFile();
        } while (!(userCommand.equals("bye")));

        closeDuke();
    }

    public static void main(String[] args) {
        new Duke("data/data.txt").runDuke();
    }
}
