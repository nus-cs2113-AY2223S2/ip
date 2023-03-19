package ErrorHandling;

import java.util.Objects;

public class ErrorHandler {
    /**
     * parses the input string to see if a valid command is given with valid error handling.
     *
     * @param input - string which is the user input
     * @return - boolean response of True when valid input and False when it's not valid
     */
    public static Boolean isInputValid(String input, int todoLen) {

        if (input.startsWith("event")) {
            return hasValidItem(input) && eventErrorHandler(input);
        }
        if (input.startsWith("deadline")) {
            return hasValidItem(input) && deadlineErrorHandler(input);
        }
        if (input.startsWith("todo")) {
            if (input.length() < 6) {
                System.out.println("Todo cannot be empty, please try again!");
                return false;
            }
        }
        if (input.startsWith("delete")) {
            int itemToDel = -1;
            if(hasValidNum(input, "delete")) {
                itemToDel = Integer.parseInt(input.split(" ")[1]);
            }
            if (itemToDel > todoLen || itemToDel < 0) {
                System.out.println("Item to delete doesn't exist");
                return false;
            }
        }
        if (input.startsWith("mark")) {
            int itemToMark = -1;
            if(hasValidNum(input, "Mark")) {
                itemToMark = Integer.parseInt(input.split(" ")[1]);
            }
            if (itemToMark > todoLen || itemToMark <= 0) {
                System.out.println("Item to Mark doesn't exist");
                return false;
            }
        }
        if (input.startsWith("unmark")) {
            int itemToUnMark = -1;
            if(hasValidNum(input, "Mark")) {
                itemToUnMark = Integer.parseInt(input.split(" ")[1]);
            }
            if (itemToUnMark > todoLen || itemToUnMark < 0) {
                System.out.println("Item to unmark doesn't exist");
                return false;
            }
        }
        return true;
    }

    /**
     * checks if the input string conforms to the appropriate format for adding an event.
     * @param inputStr - containing the user's input
     * @return Boolean response indicating true for correct format and false otherwise
     */
    public static Boolean eventErrorHandler(String inputStr) {

        if (inputStr.contains("/from") && inputStr.contains("/to")) {
            int fromIndex = inputStr.indexOf("/from") + "/from".length() + 1;
            int toIndex = inputStr.indexOf("/to") + "/to".length() + 1;
            if(toIndex < fromIndex) {
                System.out.println("You have flipped order of /to and /from");
                return false;
            }
            String fromValue = inputStr.substring(fromIndex, inputStr.indexOf("/to"));
            String toValue = inputStr.substring(toIndex-1);

            if (!fromValue.isEmpty() && !toValue.isEmpty()) {
                return true;
            } else {
                System.out.println("At least one of the values is empty.");
                return false;
            }
        }
        System.out.println("Input string is missing /from or /to keyword.");
        return false;
    }

    /**
     * checks if the input string conforms to the appropriate format for adding a Deadline.
     * @param inputStr - containing the user's input
     * @return Boolean response indicating true for correct format and false otherwise
     */
    public static Boolean deadlineErrorHandler(String inputStr) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        if (inputStr.contains("/by")) {
            int byIndex = inputStr.indexOf("/by") + "/by".length() + 1;
            if(byIndex > inputStr.length()) {
                System.out.println("Please enter a valid Date for the deadline");
                return false;
            }
            String dateString = inputStr.substring(byIndex).trim();
            if (dateString.matches(regex)) {
                System.out.println("Date is in the correct format.");
                return true;
            } else {
                System.out.println("Date is not in the correct format.");
                return false;
            }
        }
        System.out.println("Input string is missing /by keyword.");
        return false;
    }

    public static Boolean hasValidItem(String inputStr) {
        if (inputStr.split(" ").length < 2) {
            System.out.println("Please include a Valid Event or Deadline detail and try again");
            return false;
        }
        if (inputStr.split(" ")[1].equals("/from") || inputStr.equals("/to") || inputStr.split(" ")[1].equals("/by")) {
            System.out.println("Please include a Valid Event or Deadline detail and try again");
            return false;
        }
        return true;
    }

    public static Boolean hasValidNum(String inputStr, String task) {
        if (inputStr.split(" ").length < 2) {
            System.out.println("Please include the relevant entry number to " + task);
            return false;
        }
        try {
            Integer.parseInt(inputStr.split(" ")[1]);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a Valid numerical index");
            return false;
        }
        return true;
    }
}


