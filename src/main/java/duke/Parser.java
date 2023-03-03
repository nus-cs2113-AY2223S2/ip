package duke;

import duke.exceptions.TaskNumberOutOfRange;
import duke.exceptions.LackOfTaskDetail;

import duke.commands.Datetime;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Parser {
    protected static String splittedCommand[];

    public Parser() {}

    public static String parseCommand(String com) {
        splittedCommand = com.split(" ", 2);
        return splittedCommand[0];
    }

    private static Boolean hasInfo() {
        if (splittedCommand.length == 1 || splittedCommand[1].equals(""))
            return false;
        return true;
    }

    public static int getTaskIndex(int taskSize) throws TaskNumberOutOfRange {
        if (!hasInfo()) {
            throw new TaskNumberOutOfRange("    > no task number!" + System.lineSeparator() + ": ");
        }

        int index = Integer.parseInt(splittedCommand[1]) - 1;
        if (index < 0 || index > taskSize) {
            throw new TaskNumberOutOfRange("    > task index out of range!" + System.lineSeparator() + ": ");
        } else {
            return index;
        }
    }

    public static String getToDoDescription() throws LackOfTaskDetail {
        if (!hasInfo()) {
            throw new LackOfTaskDetail("    > no task detail!" + System.lineSeparator() + ": ");
        }
        return splittedCommand[1];
    }

    private static String[] splitTime(String tasktype) throws LackOfTaskDetail {
        String splittedDiscription[];
        if (!hasInfo()) {
            throw new LackOfTaskDetail("    > no task detail!" + System.lineSeparator() + ": ");
        }

        if (tasktype.equals("deadline")) {
            splittedDiscription = splittedCommand[1].split(" /by ", 2);
        } else {
            splittedDiscription = splittedCommand[1].split(" /at ", 2);
        }
        return splittedDiscription;
    }

    public static String getTaskDetail(String tasktype) throws LackOfTaskDetail {
        try {
            String splittedDiscription[] = splitTime(tasktype);
            return splittedDiscription[0];
        } catch (LackOfTaskDetail e) {
            throw new LackOfTaskDetail(e.getMessage());
        }
    }

    public static Datetime getTaskTime(String tasktype) throws LackOfTaskDetail {
        try {
            String splittedDiscription[] = splitTime(tasktype);
            if (splittedDiscription.length == 1 || splittedDiscription[1].equals("")) {
                throw new LackOfTaskDetail("    > wrong task format!" + System.lineSeparator() + ": ");
            }
            String taskTime[] = splittedDiscription[1].split(" ");

            if (taskTime.length > 1) {
                return new Datetime(LocalDate.parse(taskTime[0]), LocalTime.parse(taskTime[1]));
            } else {
                System.out.println(taskTime[0]);
                return new Datetime(LocalDate.parse(taskTime[0]));
            }

        } catch (LackOfTaskDetail e) {
            throw new LackOfTaskDetail(e.getMessage());
        }
    }

    public static String getKeyword() throws LackOfTaskDetail {
        if (!hasInfo()) {
            throw new LackOfTaskDetail("    > no keyword to search!" + System.lineSeparator() + ":");
        }
        return splittedCommand[1];
    }

    public static boolean parseOriginalCommand(String command, TaskList tasks, String path) {
        String commandType = Parser.parseCommand(command);
        boolean isEnd = false;

        switch (commandType) {
        case "list":
            Ui.listTasks(tasks.fullList());
            break;
        case "mark":
            try {
                int idx = getTaskIndex(tasks.getSize());
                tasks.markThisTask(idx);
                Ui.showMark(tasks.getDescription(idx));
            } catch (TaskNumberOutOfRange e) {
                System.out.print(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("   > Please enter a valid NUMBER!");
            }
            try {
                Storage.autoSave(tasks.fullList(), path);
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
            break;
        case "unmark":
            try {
                int idx = getTaskIndex(tasks.getSize());
                tasks.unMarkThisTask(idx);
                Ui.showUnmark(tasks.getDescription(idx));
            } catch (TaskNumberOutOfRange e) {
                System.out.print(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("   > Please enter a valid NUMBER!");
            }
            try {
                Storage.autoSave(tasks.fullList(), path);
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
            break;
        case "bye":
            isEnd = true;
            break;
        case "todo":
            try {
                String todoDetail = getToDoDescription();
                tasks.addToDo(todoDetail);
                Ui.showAddTask(tasks.latesttask(), tasks.getSize());
            } catch (LackOfTaskDetail e) {
                System.out.print(e.getMessage());
            }
            try {
                Storage.autoSave(tasks.fullList(), path);
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
            break;
        case "deadline":
        case "event": {
            try {
                tasks.addTaskWithTime(getTaskDetail(commandType), getTaskTime(commandType), commandType);
                Ui.showAddTask(tasks.latesttask(), tasks.getSize());
            } catch (DateTimeParseException e) {
                System.out.println("Time format wrong!");
                System.out.println("correct format is yyyy-MM-dd hh:mm (time can be ommitted)");
            } catch (LackOfTaskDetail e) {
                System.out.print(e.getMessage());
            }
            try {
                Storage.autoSave(tasks.fullList(), path);
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
            break;
        case "delete":
            try {
                int idx = getTaskIndex(tasks.getSize());
                Ui.showDelete(tasks.getDescription(idx), tasks.getSize());
                tasks.deleteThisTask(idx);
            } catch (TaskNumberOutOfRange e) {
                System.out.print(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("   > Please enter a valid NUMBER!");
            }
            try {
                Storage.autoSave(tasks.fullList(), path);
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
            break;
        case "find":
            try {
                String keyword = getKeyword();
                Ui.listTasks(tasks.searchRelaventTask(keyword));
            } catch (LackOfTaskDetail e) {
                System.out.println(e.getMessage());
            }
            break;
        default:
            Ui.printNoCommand();
            break;
        }

        return isEnd;
    }
}
