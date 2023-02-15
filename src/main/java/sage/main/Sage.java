package sage.main;

import sage.utility.Command;
import sage.utility.Display;
import sage.utility.FileMgmt;
import sage.utility.TaskList;

import java.util.Scanner;

public class Sage {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        FileMgmt fm = new FileMgmt();
        Display ui = new Display();
        Scanner input = new Scanner(System.in);
        ui.printWelcomeUser();
        fm.recoverData(taskList);
        while (input.hasNextLine()) {
            Command command = new Command(input.nextLine());
            switch (command.getTaskType()) {
            case "bye":
                ui.printGoodByeUser();
                return;
            case "list":
                taskList.listTask();
                break;
            case "unmark":
                taskList.markingTask(command.getTaskDescription(), false);
                break;
            case "mark":
                taskList.markingTask(command.getTaskDescription(), true);
                break;
            case "delete":
                taskList.deleteTask(command.getTaskDescription());
                break;
            case "todo":
                taskList.addTask(command.getTaskDescription(), false, false);
                break;
            case "deadline":
                taskList.addTask(command.getTaskDescription(), command.getBy(), false, false);
                break;
            case "event":
                taskList.addTask(command.getTaskDescription(), command.getFrom(), command.getTo(), false, false);
                break;
            case "write":
                taskList.update(fm);
            default:
                ui.printUnknownInput();
                break;
            }
        }
    }
}
