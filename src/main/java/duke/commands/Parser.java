package duke.commands;

import duke.*;
import duke.exceptions.IncorrectDeadlineException;
import duke.exceptions.IncorrectEventException;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    /**
     * Parses the command from the user and decides on the appropriate command to execute
     *
     * @param input the command sent by the user
     */
    public static void parseCommand(String input) {
        String[] inputs = input.split(" ", 2);
        String command = inputs[0];
        if (command.equals("bye")) {
            Ui.showBye();
            return;
        }
        if (command.equals("list")) {
            TaskList.printTaskList();
            return;
        }
        String commandDescription = inputs[1];
        switch (command) {
        case "mark":
            TaskList.markDone(Integer.parseInt(commandDescription));
            break;
        case "unmark":
            TaskList.markNotDone(Integer.parseInt(commandDescription));
            break;
        case "todo":
            TaskList.addTodo(commandDescription);
            break;
        case "deadline":
            try {
                TaskList.addDeadline(commandDescription);
            } catch (IncorrectDeadlineException e) {
                System.out.println(e.printError());
            }
            break;
        case "event":
            try {
                TaskList.addEvent(commandDescription);
            } catch (IncorrectEventException e){
                System.out.println(e.printError());
            }
            break;
        case "delete":
            TaskList.deleteTask(Integer.parseInt(commandDescription));
            break;
        default:
            System.out.println("Wrong command, please try again");
            break;
        }
    }

}
