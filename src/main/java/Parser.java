public class Parser {
    final static int ZERO_INDEX = 0;
    final static int ONE_INDEX = 1;
    final static int ERROR_NEGATIVE_ONE_RETURNED = -1;

    public static String getTodoTaskName(String taskString) {
        return taskString;
    }
    public static Todo getNewTodoTask (String userInput, String userCommand) throws DukeException {
        String taskString = getTaskString(userInput, userCommand);
        String taskName = getTodoTaskName(taskString);
        Todo newTodoTask = new Todo(taskName);
        return newTodoTask;
    }

    public static String getDeadlineTaskName(String taskString) throws DukeException {
        int slashIndex = taskString.indexOf("/by");
        if (slashIndex == ERROR_NEGATIVE_ONE_RETURNED) {
            System.out.println("Invalid Deadline String formatting: /by is missing");
            throw new DukeException();
        }
        String taskName = taskString.substring(ZERO_INDEX, slashIndex);
        if (taskName.isEmpty()) {
            System.out.println("Task needs to have a name!!!");
            throw new DukeException();
        }
        taskName = taskName.trim();
        return taskName;
    }

    public static String getEventTaskName(String taskString) throws DukeException {
        int slashIndex = taskString.indexOf("/from");
        if (slashIndex == ERROR_NEGATIVE_ONE_RETURNED) {
            System.out.println("Invalid Event String formatting: /from is missing");
            throw new DukeException();
        }
        String taskName = taskString.substring(ZERO_INDEX, slashIndex);
        if (taskName.isEmpty()) {
            System.out.println("Task needs to have a name!!!");
            throw new DukeException();
        }
        taskName = taskName.trim();
        return taskName;
    }

    public static String getEventFromDate(String taskString) throws DukeException {
        String[] taskStringPartsSplitByFrom = taskString.split("/from");
        String[] taskStringPartsSplitByTo = taskStringPartsSplitByFrom[ONE_INDEX].split("/to");
        if (taskStringPartsSplitByTo.length <= 1) {
            System.out.println("Invalid Event String formatting");
            throw new DukeException();
        }
        String eventFromDate = taskStringPartsSplitByTo[ZERO_INDEX].trim();
        if (eventFromDate.isEmpty()) {
            System.out.println("Invalid Event String formatting");
            throw new DukeException();
        }
        return eventFromDate;
    }

    public static String getEventToDate (String taskString) throws DukeException {
        String[] taskStringPartsSplitByFrom = taskString.split("/from");
        String[] taskStringPartsSplitByTo = taskStringPartsSplitByFrom[ONE_INDEX].split("/to");
        if (taskStringPartsSplitByTo.length <= 1) {
            System.out.println("Invalid Event String formatting: Either /to is missing or no description after /to");
            throw new DukeException();
        }
        String eventDueDate = taskStringPartsSplitByTo[ONE_INDEX].trim();
        if (eventDueDate.isEmpty()) {
            System.out.println("Invalid Event String formatting");
            throw new DukeException();
        }
        return eventDueDate;
    }

    // Comment: When this function is called, we assumed that /by is found and exists
    public static String getDeadlineDueDateString(String taskString) throws DukeException {
        String[] taskStringParts = taskString.split("/by");
        if (taskStringParts.length != 2) {
            System.out.println("Invalid Deadline String formatting: Description after /by is missing");
            throw new DukeException();
        }
        return taskStringParts[ONE_INDEX].trim();
    }
    public static String getTaskString(String userInput, String userCommand) throws DukeException {
        int userInputLength = userInput.trim().length();
        int userCommandLength = userCommand.length();
        if (userInputLength <= userCommandLength) {
            System.out.println("Task Description cannot be empty!!!");
            throw new DukeException();
        }
        return userInput.substring(userCommand.length() + ONE_INDEX);
    }
}
