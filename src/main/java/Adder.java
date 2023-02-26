import java.util.MissingFormatArgumentException;

public class Adder {
    /**
     * Checks the validity of the event command.
     * adds the event to the list if command has the right syntax.
     * otherwise, it informs the user that it has a wrong syntax.
     *
     * @param line String entered by the user.
     * @throws MissingFormatArgumentException When the user fails to key in all the arguments.
     */
    static void addEvent(String line) throws MissingFormatArgumentException{
        int indexOfStartDate = line.indexOf(Constants.EVENT_START_FROM);
        int indexOfEndDate = line.indexOf(Constants.EVENT_END_TO);
        boolean isOutOfBounds = indexOfStartDate == Constants.OUT_OF_BOUNDS || indexOfEndDate == Constants.OUT_OF_BOUNDS;
        boolean isMissingDescription = indexOfStartDate <= Constants.EVENT_COMMAND.length() + Constants.COMMAND_BUFFER;

        if (!isOutOfBounds && !isMissingDescription) {
            // +6 for "/from " length
            String startOfDate = line.substring(indexOfStartDate + Constants.EVENT_START_FROM.length() + Constants.COMMAND_BUFFER, indexOfEndDate);
            // +5 for "/to " length
            String endOfDate = line.substring(indexOfEndDate + Constants.EVENT_END_TO.length() + Constants.COMMAND_BUFFER);
            // +7 for "event " length
            String descOfTask = line.substring(Constants.EVENT_COMMAND.length() + Constants.COMMAND_BUFFER, indexOfStartDate);
            addToList(descOfTask, TypeOfTask.EVENT, startOfDate, endOfDate);
        } else if (isOutOfBounds) {
            throw new IndexOutOfBoundsException();
        } else if (isMissingDescription) {
            throw new MissingFormatArgumentException("Missing description");
        }
    }

    /**
     * Checks the validity of the deadline command.
     * adds the deadline to the list if command has the right syntax.
     * otherwise, it informs the user that it has a wrong syntax.
     *
     * @param line String entered by the user.
     * @throws IndexOutOfBoundsException When the user keys an index that is out of bounds.
     * @throws MissingFormatArgumentException When the user fails to key in all the arguments.
     */
    static void addDeadline(String line) throws IndexOutOfBoundsException, MissingFormatArgumentException {
        int indexOfDate = line.indexOf(Constants.DEADLINE_BY);
        boolean isOutOfBounds = indexOfDate == Constants.OUT_OF_BOUNDS;
        boolean isMissingDescription = indexOfDate <= Constants.DEADLINE_COMMAND.length() + Constants.COMMAND_BUFFER;

        if (!isOutOfBounds && !isMissingDescription) {
            // +4 for "/by " length
            String startOfDate = line.substring(indexOfDate + Constants.DEADLINE_BY.length() + Constants.COMMAND_BUFFER);
            // +10 for "deadline " length
            String descriptionOfTask = line.substring(Constants.DEADLINE_COMMAND.length() + Constants.COMMAND_BUFFER, indexOfDate);
            addToList(descriptionOfTask, TypeOfTask.DEADLINE, startOfDate, null);
        } else if (isOutOfBounds) {
            throw new IndexOutOfBoundsException();
        } else if (isMissingDescription) {
            throw new MissingFormatArgumentException("Missing description");
        }
    }

    /**
     * Checks the validity of the todo command.
     * adds the todo task to the list if command has the right syntax.
     * otherwise, it informs the user that it has a wrong syntax.
     *
     * @param line String entered by the user.
     */
    static void addTodo(String line) {
        // +5 for "todo " length
        String desc = line.substring(Constants.TODO_COMMAND.length() + Constants.COMMAND_BUFFER);
        addToList(desc, TypeOfTask.TODO, null, null);
    }

    /**
     * Adds the entered task to the list of tasks.
     * if the list of tasks is full, informs the user that the task list is full and no new tasks can be added.
     *
     * @param line String entered by user.
     * @param typeOfTask Type of tasks (TODO, DEADLINE, EVENT).
     * @param startDate startDate for EVENT, Deadline date for DEADLINE, null for TODO.
     * @param endDate endDate for EVENT, null for DEADLINE, null for TODO.
     */
    public static void addToList(String line, TypeOfTask typeOfTask, String startDate, String endDate) {
        if (typeOfTask.equals(TypeOfTask.TODO)) {
            Todo item = new Todo(false, line);
            Duke.tasks.add(item);
        } else if (typeOfTask.equals(TypeOfTask.DEADLINE)) {
            Deadline item = new Deadline(false, line, startDate);
            Duke.tasks.add(item);
        } else if (typeOfTask.equals(TypeOfTask.EVENT))  {
            Event item = new Event(false, line, startDate, endDate);
            Duke.tasks.add(item);
        }
        Greeting.sayAddToList(Duke.isSinglish);
        Printer.printWholeTask(Duke.tasks.get(Duke.tasks.size() - 1), Duke.tasks.size());
        Greeting.printHorizontalLines(Duke.isSinglish);
    }
}
