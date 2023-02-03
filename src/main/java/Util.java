public class Util {

    protected static final int INDEX_MARK = 5;
    protected static final int INDEX_UNMARK = 7;
    protected static final int INDEX_TODO = 5;
    protected static final int INDEX_DEADLINE = 9;
    protected static final int INDEX_EVENT = 6;
    protected static final int INDEX_TASK_INFORMATION = 0;
    protected static final int INDEX_OFFSET_IN_COMMAND = 1;


    public static int fetchMarkIndex(String userString) {
        String index = userString.substring(INDEX_MARK);
        return Integer.parseInt(index) - INDEX_OFFSET_IN_COMMAND;
    }

    public static int fetchUnMarkIndex(String userString) {
        String index = userString.substring(INDEX_UNMARK);
        return Integer.parseInt(index) - INDEX_OFFSET_IN_COMMAND;
    }

    public static void markTask(TaskList taskList, int taskIndex) {
        taskList.getTask(taskIndex).setDone();
    }

    public static void unMarkTask(TaskList taskList, int taskIndex) {
        taskList.getTask(taskIndex).resetDone();
    }

    public static String fetchToDo(String userString) {
        return userString.substring(INDEX_TODO);
    }

    public static String[] fetchDeadLine(String userString, int indexOfStartOfDeadLine) {
        final int INDEX_DEADLINE_INFORMATION = 1;
        String[] deadLine = new String[2];
        deadLine[INDEX_TASK_INFORMATION] = userString.substring(INDEX_DEADLINE, indexOfStartOfDeadLine);
        deadLine[INDEX_DEADLINE_INFORMATION] = userString.substring(indexOfStartOfDeadLine
                + INDEX_OFFSET_IN_COMMAND);
        return deadLine;
    }

    public static String[] fetchEvent(String userString, int indexOfStartOfFrom, int indexOfStartOfTo) {
        final int INDEX_EVENT_FROM_INFORMATION = 1;
        final int INDEX_EVENT_TO_INFORMATION = 2;
        String[] event = new String[3];
        event[INDEX_TASK_INFORMATION] = userString.substring(INDEX_EVENT, indexOfStartOfFrom);
        event[INDEX_EVENT_FROM_INFORMATION] = userString.substring(indexOfStartOfFrom
                + INDEX_OFFSET_IN_COMMAND, indexOfStartOfTo);
        event[INDEX_EVENT_TO_INFORMATION] = userString.substring(indexOfStartOfTo
                + INDEX_OFFSET_IN_COMMAND);
        return event;
    }
}
