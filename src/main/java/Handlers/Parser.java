package Handlers;

import Exceptions.InvalidTypeException;
import Exceptions.TaskManagerException;
import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;

public abstract class Parser {

    /**
     * Extracts the first word of the user input
     * 
     * @param line
     *            the user input
     * @return the first word of the user input
     */
    public static String getFirstWord(String line) {
        String[] words = line.split(" ");
        return words[0];
    }

    /**
     * Extracts the second word of the user input
     * 
     * @param line
     *            the user input
     * @return String the second word of the user input
     * @throws TaskManagerException
     *             if the user input does not contain a second word
     */
    public static String getSecondWord(String line) throws TaskManagerException {
        String[] words = line.split("\\s+");
        if (isLessThanTwo(words)) {
            throw new TaskManagerException();
        }
        int index = line.indexOf(" ");
        String sub = line.substring(index + 1);
        return sub;
    }

    private static boolean isLessThanTwo(String[] words) {
        return words.length < 2;
    }

    /**
     * Gets the task number from the user input.
     * 
     * @param line
     *            the user input
     * @return int the task number
     */
    public static int getTaskNumber(String line) {
        int index = 0;
        try {
            index = Integer.parseInt(getSecondWord(line));
            isTaskNumberValid(index);
        } catch (TaskManagerException e) {
            Ui.taskNumberMissingMessage();
        } catch (NumberFormatException e) {
            Ui.taskNumberInvalidTypeMessage();
        } catch (InvalidTypeException e) {
            Ui.taskNumberOutofBoundsMessage();
        }
        return index;
    }

    /**
     * Gets the task description or task title from the user input.
     * 
     * @param line
     *            the user input
     * @return String the task description or task title
     */
    public static String getTaskDescription(String line) {
        String description = "";
        try {
            description = getSecondWord(line);
        } catch (TaskManagerException e) {
            Ui.taskDescriptionEmptyMessage();
        }
        return description;
    }

    /**
     * Checks if the task number is between 1 and the number of tasks in the task
     * list.
     * 
     * @param taskNumber
     *            the task number taken from user input
     * @throws InvalidTypeException
     *             if the task number does not meet above conditions
     */
    public static void isTaskNumberValid(int taskNumber) throws InvalidTypeException {
        if (isTaskNumberOutOfBounds(taskNumber)) {
            throw new InvalidTypeException();
        }
    }

    private static boolean isTaskNumberOutOfBounds(int taskNumber) {
        return taskNumber < 1 || taskNumber > TaskManager.getTaskCount();
    }

    /**
     * Gets the todo description from the user input, creates a todo object
     * then adds the event to the task list.
     * 
     * @param line the user input
     */
    public static void addTodoTask(String line) {
        String todoLine = "";
        try {
            todoLine = getSecondWord(line);
            Todo todoTask = new Todo(todoLine);
            TaskManager.addTask(todoTask);
            Ui.addedTodoMessage(todoTask);
        } catch (TaskManagerException e) {
            Ui.todoDescriptionMissingMessage();
        }
    }

    /**
     * Gets the deadline description and deadline date from the user input, creates a
     * deadline object then adds the event to the task list. 
     * 
     * @param line the user input
     */
    public static void addDeadlineTask(String line) {
        String deadlineLine = "";
        try {
            deadlineLine = getSecondWord(line);
            Deadline deadlineTask = getDeadlineObject(deadlineLine);
            TaskManager.addTask(deadlineTask);
            Ui.addedDeadlineMessage(deadlineTask);
        } catch (TaskManagerException e) {
            Ui.deadlineDescriptionMissingMessage();
        }
    }
    
    /** 
     * Takes in a string containing the deadline description and deadline date and
     * returns a Deadline object.
     * 
     * @param deadlineLine the deadline description and deadline date 
     * @return Deadline the Deadline object
     */
    private static Deadline getDeadlineObject(String deadlineLine) {
        String[] deadlineDetails = deadlineLine.split("/by");
        String deadlineDescription = deadlineDetails[0].trim();
        String deadlineDate = deadlineDetails[1].trim();
        Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
        return deadlineTask;
    }

    /**
     * Gets the event description, event start date and event end date from the user
     * input, creates an event object then adds the event to the task list.
     * 
     * @param line the user input
     */
    public static void addEventTask(String line) {
        String eventLine = "";
        try {
            eventLine = getSecondWord(line);
            Event eventTask = getEventObject(eventLine);
            TaskManager.addTask(eventTask);
            Ui.addedEventMessage(eventTask);
        } catch (TaskManagerException e) {
            Ui.eventDescriptionMissingMessage();
        }
    }
    
    /** 
     * Takes in a string containing the event description, event start date and event
     * end date and returns an Event object.
     * 
     * @param eventLine the event description, event start date and event end date
     * @return Event the Event object
     */
    private static Event getEventObject(String eventLine) {
        String[] eventDetails = eventLine.split("/from");
        String[] eventTiming = eventDetails[1].split("/to");
        String eventDescription = eventDetails[0].trim();
        String eventFrom = eventTiming[0].trim();
        String eventTo = eventTiming[1].trim();
        Event eventTask = new Event(eventDescription, eventFrom, eventTo);
        return eventTask;
    }
}
