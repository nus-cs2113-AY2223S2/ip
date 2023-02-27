package duke;

import duke.command.Ui;
import duke.command.Parser;
import duke.task.TaskList;
import duke.task.TaskType;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.loadData();
    }
    public static void main(String[] args) {
        new Duke("data/data.txt").runDuke();
    }

    private void closeDuke() {
        ui.line();
        Storage.writeFile();
        ui.bye();
        ui.line();
    }

    private void startDuke() {
        ui.line();
        ui.hello();
        ui.line();
    }

    private void runDuke() {
        startDuke();

        String userCommand, userInputParameter;

        do {
            Parser.getUserInput();
            userCommand = Parser.getUserCommand();
            switch(userCommand) {
            case "list":
                userInputParameter = Parser.getUserInputDetails();
                if (userInputParameter.equals("")) {
                    TaskList.printList();
                } else {
                    ui.unknownCommandHandler();
                }
                break;
            case "mark":
                userInputParameter = Parser.getUserInputDetails();
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
                userInputParameter = Parser.getUserInputDetails();
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
                userInputParameter = Parser.getUserInputDetails();
                TaskList.addTask(userInputParameter, TaskType.TODO);
                break;
            case "deadline":
                userInputParameter = Parser.getUserInputDetails();
                TaskList.addTask(userInputParameter, TaskType.DEADLINE);
                break;
            case "event":
                userInputParameter = Parser.getUserInputDetails();
                TaskList.addTask(userInputParameter, TaskType.EVENT);
                break;
            case "delete":
                userInputParameter = Parser.getUserInputDetails();
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
                userInputParameter = Parser.getUserInputDetails();
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
}
