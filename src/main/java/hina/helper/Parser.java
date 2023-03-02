package hina.helper;

import hina.exceptions.HinaException;

import java.io.IOException;
import java.util.Scanner;

public class Parser {
    public static void readCommand() throws HinaException {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine().trim();
        if (line.equalsIgnoreCase("bye")) {
            Ui.showExitMessage();
            System.exit(0);
        } else if (line.equalsIgnoreCase("list")) {
            TaskList.listTasks();
        } else if (line.split(" ")[0].equalsIgnoreCase("mark")) {
            int taskIndex = Integer.parseInt(line.split(" ")[1]);
            TaskList.markTask(taskIndex);
        } else if (line.split(" ")[0].equalsIgnoreCase("unmark")) {
            int taskIndex = Integer.parseInt(line.split(" ")[1]);
            TaskList.unmarkTask(taskIndex);
        } else if (line.split(" ")[0].equalsIgnoreCase("todo")) {
            TaskList.addTask(line.substring(5));
            try {
                Storage.writeToFile();
            } catch (IOException ioexception) {
                Ui.couldNotSaveMessage();
            }
        } else if (line.split(" ")[0].equalsIgnoreCase("deadline")) {
            TaskList.addDeadline(line.substring(9));
            try {
                Storage.writeToFile();
            } catch (IOException ioexception) {
                Ui.couldNotSaveMessage();
            }
        } else if (line.split(" ")[0].equalsIgnoreCase("event")) {
            TaskList.addEvent(line.substring(6));
            try {
                Storage.writeToFile();
            } catch (IOException ioexception) {
                Ui.couldNotSaveMessage();
            }
        } else if (line.split(" ")[0].equalsIgnoreCase("delete")) {
            int taskIndex = Integer.parseInt(line.split(" ")[1]);
            TaskList.deleteTask(taskIndex);
            try {
                Storage.writeToFile();
            } catch (IOException ioexception) {
                Ui.couldNotSaveMessage();
            }
        } else if (line.split(" ")[0].equalsIgnoreCase("find")) {
            TaskList.findTask(line);
        } else {
            throw new HinaException();
        }
    }
}
