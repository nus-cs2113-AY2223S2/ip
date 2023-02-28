package duke.parser;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class Parser {
    public static void getCommand() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        //System.out.println(command.length + command.toString());
        while (true) {
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                Ui.printMessage(Ui.CommandType.LIST);
            } else if (userInput.startsWith("find")) {
                try {
                    String query = userInput.substring(5);
                    query.trim();
                    System.out.println("query to find is " + query);
                    Ui.printMessage(query, Ui.CommandType.FIND);
                } catch (IndexOutOfBoundsException e) {
                    Ui.printMessage(Ui.CommandType.INDEXOUT);
                }
            } else if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                String[] command = userInput.split(" ");
                try {
                    TaskList.markValidTask(command);
                    if (command[0].equals("mark")) {
                        Integer taskIndex = Integer.parseInt(command[1]);
                        Ui.printMessage(TaskList.getTask(taskIndex), Ui.CommandType.MARK);
                    } else {
                        Integer taskIndex = Integer.parseInt(command[1]);
                        Ui.printMessage(TaskList.getTask(taskIndex), Ui.CommandType.UNMARK);
                    }
                } catch (IndexOutOfBoundsException e) {
                    Ui.printMessage(Ui.CommandType.INDEXOUT);
                }
            } else if (userInput.startsWith("delete")) {
                try {
                    TaskList.deleteTask(userInput);
                } catch (DukeException e) {
                    System.out.println(Ui.line);
                    System.out.println("OOPS... The description of a " + userInput + " cannot be empty.");
                    System.out.println(Ui.line);
                } catch (IndexOutOfBoundsException e) {
                    Ui.printMessage(Ui.CommandType.INDEXOUT);
                }
            } else {
                try {
                    TaskList.addTask(userInput);
                } catch (DukeException e) {
                    System.out.println(Ui.line);
                    System.out.println("OOPS... The description of a " + userInput + " cannot be empty.");
                    System.out.println(Ui.line);
                } catch (IndexOutOfBoundsException e) {
                    Ui.printMessage(Ui.CommandType.INDEXOUT);
                }
            }
            Storage.write();
            userInput = in.nextLine();
        }
        //f.main(inputList);
        in.close();
    }
}
