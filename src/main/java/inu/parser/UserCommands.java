package inu.parser;

import java.util.Scanner;

import inu.tasklist.TaskList;

public class UserCommands {

    public static Scanner input = new Scanner(System.in);

    public static String[] userString;

    public static String command;

    public static String entry;

    public static final String TODO = "todo";

    public static final String DEADLINE = "deadline";

    public static final String EVENT = "event";

    public static final String DELETE = "delete";

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
                    Util.runTodo(taskList, entry);

                } catch (ArrayIndexOutOfBoundsException e) {

                    Ui.printPromptValidInput();

                }

                break;

            case DEADLINE:

                try {

                    entry = userString[INDEX_ENTRY];
                    Util.runDeadLine(taskList, entry);

                } catch (ArrayIndexOutOfBoundsException e) {

                    Ui.printPromptValidInput();

                }

                break;

            case EVENT:

                try {

                    entry = userString[INDEX_ENTRY];
                    Util.runEvent(taskList, entry);

                } catch (ArrayIndexOutOfBoundsException e) {

                    Ui.printPromptValidInput();

                }

                break;

            case DELETE:

                try {

                    entry = userString[INDEX_ENTRY];
                    Util.runDelete(taskList, entry);

                } catch (ArrayIndexOutOfBoundsException e) {

                    Ui.printPromptValidTaskIndexEntry();

                }

                break;

            case LIST:

                Util.runList(taskList);
                break;

            case MARK:

                try {

                    entry = userString[INDEX_ENTRY];
                    Util.runMark(taskList, entry);

                } catch (ArrayIndexOutOfBoundsException e) {

                    Ui.printPromptValidTaskIndexEntry();

                }

                break;

            case UNMARK:

                try {

                    entry = userString[INDEX_ENTRY];
                    Util.runUnMark(taskList, entry);

                } catch (ArrayIndexOutOfBoundsException e) {

                    Ui.printPromptValidTaskIndexEntry();

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