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
            int idx = 0;

            switch (commandType) {
            case "list":
                Ui.listTasks(tasks);
                break;
            case "mark":
                try {
                    idx = Parser.getTaskIndex(tasks.getSize());
                } catch (TaskNumberOutOfRange e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("   > Please enter a valid NUMBER!");
                }
                tasks.markThisTask(idx);
                try {
                    Storage.autoSave(tasks.fullList());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                Ui.showMark(tasks.getDescription(idx));
                break;
            case "unmark":
                try {
                    idx = Parser.getTaskIndex(tasks.getSize());
                } catch (TaskNumberOutOfRange e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("   > Please enter a valid NUMBER!");
                }
                tasks.unMarkThisTask(idx);
                try {
                    Storage.autoSave(tasks.fullList());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                Ui.showUnmark(tasks.getDescription(idx));
                break;
            case "bye":
                isEnd = true;
                break;
            case "todo":
                String todoDetail = "";
                try {
                    todoDetail = Parser.getToDoDescription();
                } catch (LackOfTaskDetail e) {
                    System.out.println(e.getMessage());
                }
                tasks.addToDo(todoDetail);
                Ui.showAddTask(tasks.latesttask(), tasks.getSize());
                try {
                    Storage.autoSave(tasks.fullList());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "deadline":
            case "event": {
                String[] taskDetail = {};
                try {
                    taskDetail = Parser.getTaskWithTime(commandType);
                } catch (LackOfTaskDetail e) {
                    System.out.println(e.getMessage());
                }
                tasks.addTaskWithTime(taskDetail, commandType);
                Ui.showAddTask(tasks.latesttask(), tasks.getSize());
                try {
                    Storage.autoSave(tasks.fullList());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
                break;
            case "delete":
                try {
                    idx = Parser.getTaskIndex(tasks.getSize());
                } catch (TaskNumberOutOfRange e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("   > Please enter a valid NUMBER!");
                }
                Ui.showDelete(tasks.getDescription(idx), tasks.getSize());
                tasks.deleteThisTask(idx);
                try {
                    Storage.autoSave(tasks.fullList());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
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
