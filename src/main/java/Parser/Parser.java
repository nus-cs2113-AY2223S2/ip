package Parser;

import Task.Task;

public class Parser {

    public static final String ERROR_MESSAGE = "Invalid input.";

    public static String[] processInput(String input) {
        String[] inputs = input.split(" ", 2);
        return inputs;
    }

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
