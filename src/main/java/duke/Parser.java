package duke;

import duke.exceptions.TaskNumberOutOfRange;

import java.io.IOException;

import duke.exceptions.LackOfTaskDetail;

public class Parser {
    protected static String splittedCommand[];

    public Parser() {}

    public static String parseCommand(String com) {
        splittedCommand = com.split(" ", 2);
        return splittedCommand[0];
    }

    public static int getTaskIndex(int taskSize) throws TaskNumberOutOfRange {
        if (splittedCommand.length == 1 || splittedCommand[1].equals("")) {
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
        if (splittedCommand.length == 1 || splittedCommand[1].equals("")) {
            System.out.println("yes");
            throw new LackOfTaskDetail("    > no task detail!" + System.lineSeparator() + ": ");
        }
        return splittedCommand[1];
    }

    public static String[] getTaskWithTime(String taskType) throws LackOfTaskDetail {
        String splittedDiscription[];
        if (splittedCommand.length == 1 || splittedCommand[1].equals("")) {
            throw new LackOfTaskDetail("    > no task detail!" + System.lineSeparator() + ": ");
        }

        if (taskType.equals("deadline")) {
            splittedDiscription = splittedCommand[1].split(" /by ", 2);
        } else {
            splittedDiscription = splittedCommand[1].split(" /at ", 2);
        }

        if (splittedDiscription.length == 1 || splittedDiscription[1].equals("")) {
            throw new LackOfTaskDetail(
                    "    > please enter with compelete event/deadline (description /at(or /by) time)"
                            + System.lineSeparator() + ": ");
        }

        return splittedDiscription;
    }

    public static boolean ParseCommand(String command, TaskList tasks, String path) {
        String commandType = Parser.parseCommand(command);
        boolean isEnd = false;

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
                Storage.autoSave(tasks.fullList(), path);
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
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
                String tododetail = Parser.getToDoDescription();
                tasks.addToDo(tododetail);
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
            String[] taskDetail = {};
            try {
                taskDetail = Parser.getTaskWithTime(commandType);
                tasks.addTaskWithTime(taskDetail, commandType);
                Ui.showAddTask(tasks.latesttask(), tasks.getSize());
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
                int index = Parser.getTaskIndex(tasks.getSize());
                Ui.showDelete(tasks.getDescription(index), tasks.getSize());
                tasks.deleteThisTask(index);
            } catch (TaskNumberOutOfRange e) {
                System.out.print(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.print("   > Please enter a valid NUMBER!");
            }
            try {
                Storage.autoSave(tasks.fullList(), path);
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
            break;
        default:
            Ui.printNoCommand();
            break;
        }

        return isEnd;
    }
}
