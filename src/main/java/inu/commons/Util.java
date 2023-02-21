package inu.commons;

import inu.task.TaskList;

public class Util {

    public static final int INDEX_BEGIN = 0;

    public static final int INDEX_OFFSET_IN_COMMAND = 1;

    public static final String EMPTY_STRING = "";

    public static int fetchIndexFromString(TaskList taskList, String userString) throws IndexOutOfBoundsException {
        int actualIndex = Integer.parseInt(userString) - INDEX_OFFSET_IN_COMMAND;
        int taskListSize = taskList.getTaskListSize();
        if (actualIndex >= INDEX_BEGIN && actualIndex < taskListSize) {
            return actualIndex;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public static String fetchTask(String userString) {
        int indexOfFirstSlash = userString.indexOf("/");
        String task = userString.substring(INDEX_BEGIN, indexOfFirstSlash);
        return task;
    }

    public static String fetchBy(String userString) {
        int firstSlashEntry = userString.indexOf("/");
        String by = userString.substring(firstSlashEntry + INDEX_OFFSET_IN_COMMAND);
        return by;
    }

    public static String fetchFrom(String userString) {
        int firstSlashEntry = userString.indexOf("/");
        int secondSlashEntry = userString.lastIndexOf("/");
        if (firstSlashEntry != secondSlashEntry) {
            String from = userString.substring(firstSlashEntry + INDEX_OFFSET_IN_COMMAND, secondSlashEntry);
            return from;
        } else {
            return EMPTY_STRING;
        }
    }

    public static String fetchTo(String userString) {
        int secondSlashEntry = userString.lastIndexOf("/");
        String to = userString.substring(secondSlashEntry + INDEX_OFFSET_IN_COMMAND);
        return to;
    }

}