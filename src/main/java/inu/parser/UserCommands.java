package inu.parser;

import java.util.Scanner;

import inu.task.*;
import inu.tasklist.*;
import inu.exceptionhandling.*;
import inu.tasklist.TaskList;

public class UserCommands {

    public static Scanner input = new Scanner(System.in);

    public static String[] userString;

    public static String command;

    public static String entry;

    public static String task;

    public static String deadLine;

    public static String from;

    public static String to;

    public static final String TODO = "todo";

    public static final String DEADLINE = "deadline";

    public static final String EVENT = "event";

    public static final String MARK = "mark";

    public static final String UNMARK = "unmark";

    public static final String LIST = "list";

    public static final String BYE = "bye";

    public static final int INDEX_COMMAND = 0;

    public static final int INDEX_ENTRY = 1;

    public static final int USER_STRING_SPLIT_LIMIT = 2;

    public static String[] readCommand() {

        String userString;
        String[] userCommand;

        userString = input.nextLine();
        userCommand = userString.split(" ", USER_STRING_SPLIT_LIMIT);

        return userCommand;

    }

    public static void parseCommand(TaskList taskList) {

        do {

            userString = readCommand();
            command = userString[INDEX_COMMAND];

            switch (command) {

            case TODO:

                try {

                    entry = userString[INDEX_ENTRY];

                    ExceptionManager.checkEmptyUserInput(entry);

                    Todo todoTask = new Todo(entry);
                    taskList.addTask(todoTask);
                    Ui.printAcknowledgment(taskList);

                } catch (ArrayIndexOutOfBoundsException e) {

                    Ui.printPromptValidTask();

                } catch (EmptyUserInputException e) {

                    Ui.printPromptValidTaskEntry();

                }

                break;

            case DEADLINE:

                try {

                    entry = userString[INDEX_ENTRY];
                    task = Util.fetchTask(entry);
                    deadLine = Util.fetchDeadLine(entry);

                    ExceptionManager.checkEmptyUserInput(task, deadLine);

                    DeadLine deadLineTask = new DeadLine(task, deadLine);
                    taskList.addTask(deadLineTask);
                    Ui.printAcknowledgment(taskList);

                } catch (ArrayIndexOutOfBoundsException e) {

                    Ui.printPromptValidTask();

                } catch (EmptyUserInputException e) {

                    Ui.printPromptValidDeadLineEntry();

                } catch (StringIndexOutOfBoundsException e) {

                    Ui.printPromptValidDeadLine();

                }
                break;

            case EVENT:

                try {

                    entry = userString[INDEX_ENTRY];
                    task = Util.fetchTask(entry);
                    from = Util.fetchFrom(entry);
                    to = Util.fetchTo(entry);

                    ExceptionManager.checkEmptyUserInput(task, from, to);

                    Event eventTask = new Event(task, from, to);
                    taskList.addTask(eventTask);
                    Ui.printAcknowledgment(taskList);

                } catch (ArrayIndexOutOfBoundsException e) {

                    Ui.printPromptValidTask();

                } catch (EmptyUserInputException e) {

                    Ui.printPromptValidEventEntry();

                } catch (StringIndexOutOfBoundsException e) {

                    Ui.printPromptValidEvent();

                }
                break;

            case LIST:

                try {

                    ExceptionManager.checkEmptyTaskList(taskList);
                    Ui.printTaskList(taskList);

                } catch (EmptyTaskListException e) {

                    Ui.printPromptEmptyTaskList();

                }
                break;

            case MARK:

                try {

                    entry = userString[INDEX_ENTRY];

                    ExceptionManager.checkEmptyUserInput(entry);

                    int markIndex = Util.fetchMarkIndex(entry);
                    Util.markTask(taskList, markIndex);
                    Ui.printMark(taskList, markIndex);

                } catch (ArrayIndexOutOfBoundsException e) {

                    Ui.printPromptValidMarkAndUnMark();

                } catch (NullPointerException e) {

                    Ui.printPromptValidMarkAndUnMarkIndex();

                } catch (EmptyUserInputException e) {

                    Ui.printPromptValidMarkEntry();

                }
                break;

            case UNMARK:

                try {

                    entry = userString[INDEX_ENTRY];

                    ExceptionManager.checkEmptyUserInput(entry);

                    int unMarkIndex = Util.fetchUnMarkIndex(entry);
                    Util.unMarkTask(taskList, unMarkIndex);
                    Ui.printUnMark(taskList, unMarkIndex);

                } catch (ArrayIndexOutOfBoundsException e) {

                    Ui.printPromptValidMarkAndUnMark();

                } catch (NullPointerException e) {

                    Ui.printPromptValidMarkAndUnMarkIndex();

                } catch (EmptyUserInputException e) {

                    Ui.printPromptValidUnMarkEntry();

                }
                break;

            case BYE:

                break;

            default:

                Ui.printInvalid();
                break;

            }

        } while (!command.equals(BYE));

    }

}