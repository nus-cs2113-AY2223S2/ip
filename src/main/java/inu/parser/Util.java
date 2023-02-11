package inu.parser;

import inu.exceptionhandling.EmptyTaskListException;
import inu.exceptionhandling.ExceptionManager;
import inu.exceptionhandling.EmptyUserInputException;
import inu.task.Task;
import inu.task.Todo;
import inu.task.DeadLine;
import inu.task.Event;
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

    public static void runTodo(TaskList taskList, String entry) {

        try {

            ExceptionManager.checkEmptyUserInput(entry);

            Todo todoTask = new Todo(entry);
            taskList.addTask(todoTask);
            Ui.printAcknowledgment(taskList);

        } catch (EmptyUserInputException e) {

            Ui.printPromptValidTaskEntry();

        }

    }

    public static void runDeadLine(TaskList taskList, String entry) {

        try {

            String task = fetchTask(entry);
            String deadLine = fetchDeadLine(entry);

            ExceptionManager.checkEmptyUserInput(task, deadLine);

            DeadLine deadLineTask = new DeadLine(task, deadLine);
            taskList.addTask(deadLineTask);
            Ui.printAcknowledgment(taskList);

        } catch (EmptyUserInputException e) {

            Ui.printPromptValidByDateEntryAfterSlash();

        } catch (StringIndexOutOfBoundsException e) {

            Ui.printPromptValidDeadLine();

        }

    }

    public static void runEvent(TaskList taskList, String entry) {

        try {

            String task = fetchTask(entry);
            String from = fetchFrom(entry);
            String to = fetchTo(entry);

            ExceptionManager.checkEmptyUserInput(task, from, to);

            Event eventTask = new Event(task, from, to);
            taskList.addTask(eventTask);
            Ui.printAcknowledgment(taskList);

        } catch (EmptyUserInputException e) {

            Ui.printPromptValidFromAndToEntryAfterSlash();

        } catch (StringIndexOutOfBoundsException e) {

            Ui.printPromptValidEvent();

        }

    }

    public static void runDelete(TaskList taskList, String entry) {

        try {

            ExceptionManager.checkEmptyUserInput(entry);

            int deleteTaskNumber = fetchIndexFromString(entry);
            Ui.printDelete(taskList, deleteTaskNumber);
            taskList.deleteTask(deleteTaskNumber);

        } catch (IndexOutOfBoundsException e) {

            Ui.printPromptValidTaskIndex();

        } catch (EmptyUserInputException e) {

            Ui.printPromptValidDeleteEntry();

        }

    }

    public static void runList(TaskList taskList) {

        try {

            ExceptionManager.checkEmptyTaskList(taskList);
            Ui.printTaskList(taskList);

        } catch (EmptyTaskListException e) {

            Ui.printPromptEmptyTaskList();

        }

    }

    public static void runMark(TaskList taskList, String entry) {

        try {

            ExceptionManager.checkEmptyUserInput(entry);

            int markIndex = fetchIndexFromString(entry);
            markTask(taskList, markIndex);

            Ui.printMark(taskList, markIndex);

        } catch (IndexOutOfBoundsException e) {

            Ui.printPromptValidTaskIndex();

        } catch (EmptyUserInputException e) {

            Ui.printPromptValidMarkEntry();

        }

    }

    public static void runUnMark(TaskList taskList, String entry) {

        try {

            ExceptionManager.checkEmptyUserInput(entry);

            int unMarkIndex = fetchIndexFromString(entry);
            unMarkTask(taskList, unMarkIndex);

            Ui.printUnMark(taskList, unMarkIndex);

        } catch (ArrayIndexOutOfBoundsException e) {

            Ui.printPromptValidTaskIndexEntry();

        } catch (IndexOutOfBoundsException e) {

            Ui.printPromptValidTaskIndex();

        } catch (EmptyUserInputException e) {

            Ui.printPromptValidUnMarkEntry();

        }

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