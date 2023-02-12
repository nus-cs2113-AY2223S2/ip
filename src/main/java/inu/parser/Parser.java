package inu.parser;

import inu.exceptionhandling.EmptyTaskListException;
import inu.exceptionhandling.EmptyUserInputException;
import inu.exceptionhandling.ExceptionManager;
import inu.task.DeadLine;
import inu.task.Event;
import inu.task.Task;
import inu.task.Todo;
import inu.task.TaskList;
import inu.commons.Ui;
import inu.commons.Util;


public class Parser {

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

            String task = Util.fetchTask(entry);
            String deadLine = Util.fetchDeadLine(entry);
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

            String task = Util.fetchTask(entry);
            String from = Util.fetchFrom(entry);
            String to = Util.fetchTo(entry);
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
            int deleteTaskNumber = Util.fetchIndexFromString(entry);
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
            int markIndex = Util.fetchIndexFromString(entry);
            Util.markTask(taskList, markIndex);
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
            int unMarkIndex = Util.fetchIndexFromString(entry);
            Util.unMarkTask(taskList, unMarkIndex);
            Ui.printUnMark(taskList, unMarkIndex);

        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printPromptValidTaskIndexEntry();
        } catch (IndexOutOfBoundsException e) {
            Ui.printPromptValidTaskIndex();
        } catch (EmptyUserInputException e) {
            Ui.printPromptValidUnMarkEntry();
        }
    }

    public static void runInvalid() {
        Ui.printInvalid();
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
