package duke;

import duke.exceptions.TaskNumberOutOfRange;
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
                    "    > please enter with a time of deadline/event" + System.lineSeparator() + ": ");
        }

        return splittedDiscription;
    }
}
