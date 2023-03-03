package Parser;

import Task.Task;

public class Parser {
    // Error String
    public static final String ERROR_MESSAGE = "Invalid input.";

    /**
     * This method splits the input with spaces.
     * Maximum length is two.
     * 
     * @param input the input string
     * @return the splitted string in a string array
     */
    public static String[] processInput(String input) {
        String[] inputs = input.split(" ", 2);
        return inputs;
    }

    /**
     * This method checks the validity of input.
     *
     * @param input the input string array
     * @return the index of the tasks desired, else -1 if invalid
     */
    public static int checkActionInputValidity(String[] input) {
        try {
            if (input.length == 1) {
                System.out.println(ERROR_MESSAGE);
                return -1;
            }
            int taskIndex = Integer.parseInt(input[1]);
            if (taskIndex > Task.numberOfTasks || taskIndex < 1) {
                throw new IndexOutOfBoundsException(ERROR_MESSAGE);
            }
            return taskIndex - 1;
        } catch (NumberFormatException nonIntegerIndex) {
            System.out.println(ERROR_MESSAGE);
            return -1;
        } catch (IndexOutOfBoundsException outOfBoundsIndex) {
            System.out.println(ERROR_MESSAGE);
            return -1;
        }
    }

    /**
     * This method checks the validity of find input.
     *
     * @param input the input string
     * @return the keyword, else an empty string if invalid
     */
    public static String processFindString(String input) {
        try {
            String findString = input.split("find")[1].trim();
            if (findString.equals("")) {
                System.out.println(ERROR_MESSAGE);
                return findString;
            }
            return findString;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ERROR_MESSAGE);
            return "";
        }
    }

    /**
     * This method checks the validity of todo input.
     *
     * @param input the input string
     * @return the todo task, else an empty string if invalid
     */
    public static String processTodoString(String input) {
        try {
            String task = input.split("todo")[1].trim();
            if (task.equals("")) {
                System.out.println(ERROR_MESSAGE);
                return task;
            }
            return task;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ERROR_MESSAGE);
            return "";
        }
    }

    /**
     * This method checks the validity of deadline input.
     *
     * @param input the input string
     * @return the deadline info in string array, else an empty string if invalid
     */
    public static String[] processDeadlineString(String input) {
        try {
            String deadlineDesc = input.split("/")[0].split("deadline")[1].trim();
            String deadlineDay = input.split("/")[1].trim();

            String[] deadlineArray = { deadlineDesc, deadlineDay };

            for (int i = 0; i < 2; i++) {
                deadlineArray[i] = deadlineArray[i].trim();
                if (deadlineArray[i].equals("")) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return deadlineArray;
        } catch (IndexOutOfBoundsException outOfBoundsException) {
            System.out.println(ERROR_MESSAGE);
            return new String[0];
        }
    }

    /**
     * This method checks the validity of event input.
     *
     * @param input the input string
     * @return the event info in string array, else an empty string if invalid
     */
    public static String[] processEventString(String input) {
        try {
            String eventDesc = input.split("/")[0].split("event")[1].trim();
            String start = input.split("/")[1].trim();
            String end = input.split("/")[2].trim();

            String[] eventArray = { eventDesc, start, end };

            for (int i = 0; i < 3; i++) {
                eventArray[i] = eventArray[i].trim();
                if (eventArray[i].equals("")) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return eventArray;
        } catch (IndexOutOfBoundsException outOfBoundsException) {
            System.out.println(ERROR_MESSAGE);
            return new String[0];
        }

    }

}
