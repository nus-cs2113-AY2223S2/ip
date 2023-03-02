package parser;

import java.util.Scanner;

public class Parser {

    //Error Strings
    public static final String ERROR_NON_INTEGER_INDEX = "Invalid Input. Give Just an Integer!";
    public static final String ERROR_NON_EXISTENT_TASK = "That task doesn't exist...";
    public static final String ERROR_NO_INDEX_PROVIDED = "Do you want me to edit the whole list?";
    public static final String ERROR_NO_QUERY_PROVIDED = "Do you want me to print the whole list?";
    public static final String ERROR_MISSING_OR_EMPTY_FIELDS = "1 or more Missing/Empty Fields found!";
    public static final String ERROR_MISSING_TASK = "Why is your task empty?";
    public static final String ERROR_BAD_FORMATTING = "That's some terrible formatting.";

    /**
     * Takes the original input and separates the first word.
     * Used to determine the intended command by the user
     *
     * @param in the Scanner used to obtain the input
     * @return an array of String of maximum size 2, containing the user input
     * The first element in the array is the command, while the second element are the details of the input
     */
    public static String[] processInputMessage(Scanner in) {
        String input = in.nextLine();
        input = input.trim();
        return input.split(" ", 2);
    }

    /**
     * Checks if the queried index of the TaskList exists.
     * Used for Commands: "mark, unmark, delete"
     *
     * @param input The user input. input[1] is expected to be a valid integer.
     * @param indexLimit the size of the TaskList
     * @return the index if it is valid, else -1 if invalid.
     */
    public static int checkActionInputValidity(String[] input, int indexLimit) {
        try {
            if (input.length == 1) {
                System.out.println(ERROR_NO_INDEX_PROVIDED);
                return -1;
            }
            int taskIndex = Integer.parseInt(input[1]);
            if (taskIndex > indexLimit || taskIndex < 1) {
                throw new IndexOutOfBoundsException();
            }
            return taskIndex - 1;
        } catch (NumberFormatException nonIntegerIndex) {
            System.out.println(ERROR_NON_INTEGER_INDEX);
            return -1;
        } catch (IndexOutOfBoundsException outOfBoundsIndex) {
            System.out.println(ERROR_NON_EXISTENT_TASK);
            return -1;
        }
    }

    /**
     * Checks if the queried string for the TaskList is correctly inputted
     * Used for Commands: "find"
     *
     * @param input The user input. input[1] is expected to be a valid String
     * @return a String containing the query, else an empty string if incorrectly inputted
     */
    public static String processQuery(String[] input) {
        if (input.length == 1) {
            System.out.println(ERROR_NO_QUERY_PROVIDED);
            return "";
        }
        return input[1];
    }

    /**
     * Checks if the new Event Task for the TaskList is correctly inputted
     * Used for Commands: "event"
     *
     * @param input The user input. input[1] is expected to be a valid String
     * @return a String array of size 3, containing the details for the Event Task.
     * Else, the first element of the String array is empty if incorrectly inputted
     */
    public static String[] processEventMessage(String[] input) {
        String[] eventArray = new String[3];
        try {
            String[] inputArray = input[1].split(" /from ");
            if (inputArray.length != 2) {
                System.out.println(ERROR_BAD_FORMATTING);
                return new String[0];
            }
            eventArray[0] = inputArray[0];
            inputArray = inputArray[1].split(" /to ");
            if (inputArray.length != 2) {
                System.out.println(ERROR_BAD_FORMATTING);
                return new String[0];
            }
            eventArray[1] = inputArray[0];
            eventArray[2] = inputArray[1];
            for (int i = 0; i < 2; i += 1) {
                if (eventArray[i].trim().equals("")) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return eventArray;
        } catch (IndexOutOfBoundsException outOfBoundsIndex) {
            System.out.println(ERROR_MISSING_OR_EMPTY_FIELDS);
            return new String[0];
        }
    }

    /**
     * Checks if the new Deadline Task for the TaskList is correctly inputted
     * Used for Commands: "deadline"
     *
     * @param input The user input. input[1] is expected to be a valid String
     * @return a String array of size 2, containing the details for the Deadline Task.
     * Else, the first element of the String array is empty if incorrectly inputted
     */
    public static String[] processDeadlineMessage(String[] input) {
        String[] deadlineArray;
        try {
            deadlineArray = input[1].split(" /by ");
            if (deadlineArray.length != 2) {
                System.out.println(ERROR_BAD_FORMATTING);
                return new String[0];
            }
            for (int i = 0; i < 2; i++) {
                if (deadlineArray[i].trim().equals("")) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return deadlineArray;
        } catch (IndexOutOfBoundsException outOfBoundsIndex) {
            System.out.println(ERROR_MISSING_OR_EMPTY_FIELDS);
            return new String[0];
        }
    }

    /**
     * Checks if the new Todo Task for the TaskList is correctly inputted
     * Used for Commands: "todo"
     *
     * @param input The user input. input[1] is expected to be a valid String
     * @return a String containing the details for the Todo Task, else an empty string if incorrectly inputted
     */
    public static String processToDoMessage(String[] input) {
        try {
            String task = input[1].trim();
            if (task.equals("")) {
                System.out.println(ERROR_MISSING_TASK);
            }
            return task;
        } catch (IndexOutOfBoundsException BadFormatting) {
            System.out.println(ERROR_MISSING_TASK);
            return "";
        }
    }
}

