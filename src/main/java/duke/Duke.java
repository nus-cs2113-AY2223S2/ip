package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.exceptions.TaskNumberOutOfRange;
import duke.exceptions.LackOfTaskDetail;

public class Duke {
    public static void main(String[] args) {
        TaskList tasks = new TaskList();
        Scanner in = new Scanner(System.in);
        boolean isEnd = false;

        Ui.printHello();

        while (!isEnd) {
            String command = in.nextLine();
            String commandType = Parser.parseCommand(command);

            switch (commandType) {
            case "list":
                Ui.listTasks(tasks.fullList());
                break;
            case "mark":
                try {
                    int index = Parser.getTaskIndex(tasks.getSize());
                    tasks.markThisTask(index);
                    Ui.showMark(tasks.getDescription(index));
                } catch (TaskNumberOutOfRange e) {
                    System.out.print(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("   > Please enter a valid NUMBER!");
                }
                try {
                    Storage.autoSave(tasks.fullList());
                } catch (IOException e) {
                    System.out.print(e.getMessage());
                }
                Ui.showMark(tasks.getDescription(index));
                break;
            case "unmark":
                try {
                    int index = Parser.getTaskIndex(tasks.getSize());
                    tasks.unMarkThisTask(index);
                    Ui.showUnmark(tasks.getDescription(index));
                } catch (TaskNumberOutOfRange e) {
                    System.out.print(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("   > Please enter a valid NUMBER!");
                }
                try {
                    Storage.autoSave(tasks.fullList());
                } catch (IOException e) {
                    System.out.print(e.getMessage());
                }
                break;
            case "bye":
                isEnd = true;
                break;
            case "todo":
                try {
                    String tododetail = Parser.getToDoDescription();
                    tasks.addToDo(tododetail);
                    Ui.showAddTask(tasks.latesttask(),tasks.getSize());
                } catch (LackOfTaskDetail e) {
                    System.out.print(e.getMessage());
                }
                try {
                    Storage.autoSave(tasks.fullList());
                } catch (IOException e) {
                    System.out.print(e.getMessage());
                }
                break;
            case "deadline":
            case "event": {
                String[] taskDetail = {};
                try {
                    taskDetail = Parser.getTaskWithTime(commandType);
                    tasks.addTaskWithTime(taskDetail, commandType);
                    Ui.showAddTask(tasks.latesttask(), tasks.getSize());
                } catch (LackOfTaskDetail e) {
                    System.out.print(e.getMessage());
                }
                try {
                    Storage.autoSave(tasks.fullList());
                } catch (IOException e) {
                    System.out.print(e.getMessage());
                }
            }
                break;
            case "delete":
                try {
                    int index = Parser.getTaskIndex(tasks.getSize());
                    Ui.showDelete(tasks.getDescription(index), tasks.getSize());
                    tasks.deleteThisTask(index);
                } catch (TaskNumberOutOfRange e) {
                    System.out.print(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.print("   > Please enter a valid NUMBER!");
                }
                try {
                    Storage.autoSave(tasks.fullList());
                } catch (IOException e) {
                    System.out.print(e.getMessage());
                }
                break;
            default:
                Ui.printNoCommand();
                break;
            }
        }

        Ui.printBye();
        in.close();
    }
}
