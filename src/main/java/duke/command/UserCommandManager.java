package duke.command;

import duke.data.TaskData;

import java.util.Scanner;

public class UserCommandManager {

    TaskData taskData = new TaskData();

    /**
     * Handles the command (i.e. type of task) by user
     * and parse the arguments to add to task list
     *
     * @param userCommand type of action user wants to perform
     * @param userInput   arguments to the action (i.e. task description)
     */
    public void handleCommands(String userCommand, String userInput) {
        Scanner scanner = new Scanner(userInput);
        Command command = null;
        switch (userCommand) {
        case "bye":
            command = new ExitCommand();
        case "list":
            command = new ListTasks();
            break;
        case "mark":
            command = new MarkCommand(userInput.replace(" ", ""));
            break;
        case "unmark":
            command = new UnmarkCommand(userInput.replace(" ", ""));
            break;
        case "todo":
            command = new AddTodoToList(scanner.nextLine());
            break;
        case "deadline":
            command = new AddDeadlineToList(scanner.nextLine().trim().split("/by"));
            break;
        case "event":
            command = new AddEventToList(userInput);
            break;
        case "delete":
            command = new DeleteFromList(userInput.replace(" ", ""));
            break;
        default:
            System.out.println("error");
            break;
        }
        if (command != null) {
            command.executeCommand(taskData);
        }
    }
}
