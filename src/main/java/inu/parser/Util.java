package inu.parser;

import inu.task.Task;
import inu.task.Todo;
import inu.task.DeadLine;
import inu.task.Event;
import inu.tasklist.*;
import inu.exceptionhandling.EmptyUserInputException;
import inu.tasklist.TaskList;

public class Util {

    protected static final int INDEX_BEGIN = 0;

    protected static final int INDEX_OFFSET_IN_COMMAND = 1;

    protected static final int DECODED_TASK_TYPE = 0;

    protected static final int DECODED_MARK = 1;

    protected static final int DECODED_TASK = 2;

    protected static final int DECODED_DUE_DATE = 3;

    protected static final int DECODED_FROM_DATE = 3;

    protected static final int DECODED_TO_DATE = 4;

    protected static final String T = "T";

    protected static final String D = "D";

    protected static final String E = "E";

    protected static final String MARKED = "X";

    public static int fetchIndexFromString(String userString) {

        return Integer.parseInt(userString) - INDEX_OFFSET_IN_COMMAND;

    }

    public static void markTask(TaskList taskList, int taskIndex) {

        taskList.getTask(taskIndex).setDone();

    }

    public static void unMarkTask(TaskList taskList, int taskIndex) {

        taskList.getTask(taskIndex).resetDone();

    }

    public static String fetchTask(String userString) {

        int indexOfFirstSlash = userString.indexOf("/");
        return userString.substring(INDEX_BEGIN, indexOfFirstSlash);

    }

    public static String fetchDeadLine(String userString) {

        int firstSlashEntry = userString.indexOf("/");
        return userString.substring(firstSlashEntry + INDEX_OFFSET_IN_COMMAND);

    }

    public static String fetchFrom(String userString) throws EmptyUserInputException {

        int firstSlashEntry = userString.indexOf("/");
        int secondSlashEntry = userString.lastIndexOf("/");
        if (firstSlashEntry != secondSlashEntry) {
            return userString.substring(firstSlashEntry + INDEX_OFFSET_IN_COMMAND, secondSlashEntry);
        }
        throw new EmptyUserInputException();

    }

    public static String fetchTo(String userString) {

        int secondSlashEntry = userString.lastIndexOf("/");
        return userString.substring(secondSlashEntry + INDEX_OFFSET_IN_COMMAND);

    }

    public static Task decodeString(String fileString) {

        String[] fileStrings = fileString.split("//");
        String taskType = fileStrings[DECODED_TASK_TYPE];
        String task = fileStrings[DECODED_TASK];
        String markStatus = fileStrings[DECODED_MARK];

        switch (taskType) {

        case T:

            Todo todo = new Todo(task);
            if (markStatus.equals(MARKED)) {

                todo.setDone();

            }

            return todo;

        case D:

            String dueDate = fileStrings[DECODED_DUE_DATE];
            DeadLine deadLine = new DeadLine(task, dueDate);

            if (markStatus.equals(MARKED)) {

                deadLine.setDone();

            }

            return deadLine;

        case E:

            String fromDate = fileStrings[DECODED_FROM_DATE];
            String toDate = fileStrings[DECODED_TO_DATE];
            Event event = new Event(task, fromDate, toDate);

            if (markStatus.equals(MARKED)) {

                event.setDone();

            }

            return event;

        default:

            return null;

        }

    }

}