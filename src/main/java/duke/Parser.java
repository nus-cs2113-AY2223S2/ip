package duke;

import java.io.IOException;

public class Parser {

    public static void handleCmd(String userCmd, TaskManager listOfItems, Ui ui) throws IOException {
        switch (firstWord(userCmd)) {
            case "todo":
                executeAddTodo(userCmd, listOfItems, ui);
                ui.printHorizontalLine();
                Storage.saveFile(listOfItems);
                break;
            case "deadline":
                executeAddDeadline(userCmd, listOfItems, ui);
                ui.printHorizontalLine();
                Storage.saveFile(listOfItems);
                break;
            case "event":
                executeAddEvent(userCmd, listOfItems, ui);
                ui.printHorizontalLine();
                Storage.saveFile(listOfItems);
                break;
            case "list":
                listOfItems.listTask();
                ui.printHorizontalLine();
                Storage.saveFile(listOfItems);
                break;
            case "mark":
                String markId[] = userCmd.split(" ");
                listOfItems.markTask(Integer.parseInt(markId[1]) - 1);
                ui.printHorizontalLine();
                Storage.saveFile(listOfItems);
                break;
            case "unmark":
                String unmarkId[] = userCmd.split(" ");
                listOfItems.unmarkTask(Integer.parseInt(unmarkId[1]) - 1);
                ui.printHorizontalLine();
                Storage.saveFile(listOfItems);
                break;
            case "delete":
                String deleteId[] = userCmd.split(" ");
                listOfItems.deleteTask(Integer.parseInt(deleteId[1]) - 1);
                ui.printHorizontalLine();
                Storage.saveFile(listOfItems);
                break;
            case "clear":
                listOfItems.clearData();
                Storage.saveFile(listOfItems);
                ui.printHorizontalLine();
                break;
            case "find":
                listOfItems.findItem(userCmd.substring(5, userCmd.length()));
                ui.printHorizontalLine();
                break;
            default:
                ui.printfalseInput();
                ui.printHorizontalLine();
        }
    }

    public static String firstWord(String s) {
        String a[] = s.split(" ");
        return a[0];
    }

    public static void executeAddTodo(String s, TaskManager listOfItems, Ui ui) {
        try {
            s = s.substring("todo ".length(), s.length());
            listOfItems.addTask(s, false);
            System.out.println("Roger. The following todo has been added:");
            System.out.println("[T][ ] " + s);
            System.out.println("You now have " + listOfItems.getSize() + " item in the list");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Missing todo item. Please read instructions again");
            ui.printInstructions();
        }
    }

    public static void executeAddDeadline(String s, TaskManager listOfItems, Ui ui) {
        try {
            s = s.substring("deadline ".length(), s.length());
            String[] cmd = s.split(" /by ");
            if (cmd.length > 2) {
                System.out.println("Extra deadline found! Please try again!");
                ui.printInstructions();
                return;
            }
            listOfItems.addDeadline(cmd[0], cmd[1], false);
            System.out.println("Roger. The following deadline has been added:");
            System.out.println("[D][ ] " + cmd[0] + " (by: " + cmd[1] + ")");
            System.out.println("You now have " + listOfItems.getSize() + " item in the list");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Your task name is missing please try again!");
            ui.printInstructions();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Your deadline is missing please try again!");
            ui.printInstructions();
        }

    }

    public static void executeAddEvent(String s, TaskManager listOfItems, Ui ui) {
        try {
            s = s.substring("event ".length(), s.length());
            String[] cmd = s.split(" /from ");
            String startTime = cmd[1].split(" /to ")[0];
            String endTime = cmd[1].split(" /to ")[1];
            listOfItems.addEvent(cmd[0], startTime, endTime, false);
            System.out.println("Roger. The following event has been added:");
            System.out.println("[E][ ] " + cmd[0] + " (from: " + startTime + " to: " + endTime + ")");
            System.out.println("You now have " + listOfItems.getSize() + " item in the list");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Your task name is missing please try again!");
            ui.printInstructions();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Something is wrong with your event's start time or finish time please try again!");
            ui.printInstructions();
        }
    }

}
