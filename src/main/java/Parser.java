public class Parser {
    final static int ZERO_INDEX = 0;
    final static int ONE_INDEX = 1;
    final static int ERROR_NEGATIVE_ONE_RETURNED = -1;

    /**
     * Returns the task name for the Todo task type.
     *
     * @param taskString Task description extracted from the userinput excluding the user command keyword.
     * @return Task name of Todo task.
     * @throws DukeException If taskString is an empty string.
     */
    public static String getTodoTaskName(String taskString) throws DukeException {
        if (taskString.isEmpty()) {
            throw new DukeException();
        } else {
            return taskString;
        }
    }

    /**
     * Returns the task name for the Todo task type.
     *
     * @param userInput Line of String containing the user input.
     * @param userCommandKeyword Command keyword given by user, etc. mark, unmark, find etc.
     * @return Task name of Todo task
     * @throws DukeException Thrown by getTaskString or getTodoTaskName.
     * getTaskString: If the task description extracted is empty, length of userInput <= length of userCommand.
     * getTodoTaskName: If taskString is an empty string.
     */
    public static Todo getNewTodoTask (String userInput, String userCommandKeyword) throws DukeException {
        String taskString = getTaskString(userInput, userCommandKeyword);
        String taskName = getTodoTaskName(taskString);
        Todo newTodoTask = new Todo(taskName);
        return newTodoTask;
    }

    /**
     * Returns the task name for the Deadline task type.
     * String for task name is trimmed using .trim() function.
     *
     * @param taskString Task description extracted from the userinput excluding the user command keyword.
     * @return Task name of Deadline task.
     * @throws DukeException If taskString does not contain "/by" format keyword OR If task name extracted is empty string
     */
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

    /**
     * Returns the task name for the Event task type.
     * String for task name is trimmed using .trim() function.
     *
     * @param taskString Task description extracted from the userinput excluding the user command keyword.
     * @return Task name of Event task.
     * @throws DukeException If taskString does not contain "/from" format keyword OR If task name extracted is empty string
     */
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

    /**
     * Returns the start date extracted from a task description for the Event task type.
     *
     * @param taskString Task description extracted from the userinput excluding the user command keyword.
     * @return start date of Event task in String form.
     * @throws DukeException If taskString is missing /from or /to (detected from looking at length of String[] taskStringPartsSplitByTo)
     * OR If start date extracted is empty
     */
    public static String getEventStartDate(String taskString) throws DukeException {
        String[] taskStringPartsSplitByFrom = taskString.split("/from");
        if (taskStringPartsSplitByFrom.length <= 1) {
            System.out.println("Invalid Event String formatting");
            throw new DukeException();
        }
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

    /**
     * Returns the end date extracted from a task description for the Event task type.
     *
     * @param taskString Task description extracted from the userinput excluding the user command keyword.
     * @return end date of Event task in String form.
     * @throws DukeException If taskString is missing /from or /to (detected from looking at length of String[] taskStringPartsSplitByTo)
     * OR If end date extracted is empty.
     */
    public static String getEventEndDate(String taskString) throws DukeException {
        String[] taskStringPartsSplitByFrom = taskString.split("/from");
        if (taskStringPartsSplitByFrom.length <= 1) {
            System.out.println("Invalid Event String formatting");
            throw new DukeException();
        }
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

    /**
     * Returns the deadline date extracted from a task description for the Deadline task type.
     *
     * @param taskString Task description extracted from the userinput excluding the user command keyword.
     * @return deadline date of Deadline task in a form of a string.
     * @throws DukeException If taskString is missing /by (detected from looking at length of String[] taskStringParts)
     */
    public static String getDeadlineDueDateString(String taskString) throws DukeException {
        String[] taskStringParts = taskString.split("/by");
        if (taskStringParts.length != 2) {
            System.out.println("Invalid Deadline String formatting: Description after /by is missing");
            throw new DukeException();
        }
        return taskStringParts[ONE_INDEX].trim();
    }

    /**
     * Returns Task description, extracted from userInput with the knowledge of knowing the user command keyword.
     *
     * @param userInput Line inputted from user.
     * @param userCommand user command keyword inputted from user.
     * @return taskString Task description extracted from the userinput excluding the user command keyword.
     * @throws DukeException If the task description extracted is empty, length of userInput <= length of userCommand
     * Note that the case of user input not containing a valid command is handled by UI
     */
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
