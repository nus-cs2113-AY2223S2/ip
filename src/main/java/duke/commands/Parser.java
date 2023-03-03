package duke.commands;

import duke.*;
import duke.exceptions.IncorrectDeadlineException;
import duke.exceptions.IncorrectEventException;

public class Parser {

    /**
     * Parses the command from the user and decides on the appropriate command to execute
     *
     * @param input the command sent by the user
     */
    public static void parseCommand(String input) {
        //String[] inputs splits the string into the command and the command description
        String[] inputs = input.split(" ", 2);
        if (inputs[0].equals("bye")) {
            Ui.showBye();
            return;
        }
        if (inputs[0].equals("list")) {
            TaskList.printTaskList();
            return;
        }
        switch (inputs[0]) {
        case "mark":
            if (isPresent(inputs) == false) {
                break;
            }
            int index = Integer.parseInt(inputs[1]);
            if (index > TaskList.getTaskSize() || index <= 0) {
                System.out.println("Task number is invalid, please select the correct task number");
                break;
            }
            TaskList.markDone(index);
            break;
        case "unmark":
            if (isPresent(inputs) == false) {
                break;
            }
            else if (Integer.parseInt(inputs[1]) > TaskList.getTaskSize() || Integer.parseInt(inputs[1]) <= 0) {
                System.out.println("Task number is invalid, please select the correct task number");
                break;
            }
            TaskList.markNotDone(Integer.parseInt(inputs[1]));
            break;
        case "todo":
            if (isPresent(inputs) == false) {
                break;
            }
            TaskList.addTodo(inputs[1]);
            break;
        case "deadline":
            if (isPresent(inputs) == false) {
                break;
            }
            try {
                TaskList.addDeadline(inputs[1]);
            } catch (IncorrectDeadlineException e) {
                System.out.println(e.printError());
            }
            break;
        case "event":
            if (isPresent(inputs) == false) {
                break;
            }
            try {
                TaskList.addEvent(inputs[1]);
            } catch (IncorrectEventException e){
                System.out.println(e.printError());
            }
            break;
        case "delete":
            if (isPresent(inputs) == false) {
                break;
            }
            if (Integer.parseInt(inputs[1]) > TaskList.getTaskSize() || Integer.parseInt(inputs[1]) <= 0) {
                System.out.println("Task number is invalid, please select the correct task number");
                break;
            }
            TaskList.deleteTask(Integer.parseInt(inputs[1]));
            break;
        case "find":
            if (isPresent(inputs) == false) {
                break;
            }
            TaskList.findTask(inputs[1]);
            break;
        default:
            System.out.println("Wrong command, please try again");
            break;
        }
    }

    /**
     * Checks if there is a command description written in the user inputs
     *
     * @param inputs the command return by the user
     * @return true if the description is present and false if it is not present
     */
    public static boolean isPresent (String[] inputs) {
        if (inputs.length == 1) {
            System.out.println("Command description is empty, please include the command description");
            return false;
        }
        return true;
    }

}
