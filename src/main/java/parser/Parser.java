package parser;

import java.util.Scanner;

public class Parser {

    //Error Strings
    public static final String ERROR_NON_INTEGER_INDEX = "Invalid Input. Give Just an Integer!";
    public static final String ERROR_NON_EXISTENT_TASK = "That task doesn't exist...";
    public static final String ERROR_NO_INDEX_PROVIDED = "Do you want me to edit the whole list?";
    public static final String ERROR_MISSING_OR_EMPTY_FIELDS = "1 or more Missing/Empty Fields found!";
    public static final String ERROR_MISSING_TASK = "Why is your task empty?";
    public static final String ERROR_BAD_FORMATTING = "That's some terrible formatting.";

    public static String[] processInputMessage(Scanner in) {
        String input = in.nextLine();
        return input.split(" ", 2);
    }

    public static int checkActionInputValidity(String[] input, int indexLimit) {
        try {
            if (input.length == 1) {
                throw new NullPointerException();
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
        } catch (NullPointerException noIndex) {
            System.out.println(ERROR_NO_INDEX_PROVIDED);
            return -1;
        }
    }

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

