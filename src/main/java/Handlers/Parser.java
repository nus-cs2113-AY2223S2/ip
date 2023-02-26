package Handlers;

import Exceptions.InvalidTypeException;
import Exceptions.TaskManagerException;
import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;

public class Parser {

    
    /** 
     * Extracts the first word of the user input
     * 
     * @param line the user input
     * @return the first word of the user input
     */
    public static String getFirstWord(String line) {
        String[] words = line.split(" ");
        return words[0];
    }

    
    /** 
     * Extracts the second word of the user input
     * 
     * @param line the user input
     * @return String the second word of the user input
     * @throws TaskManagerException if the user input does not contain a second word
     */
    public static String getSecondWord(String line) throws TaskManagerException {
        String[] words = line.split("\\s+");
        if (words.length < 2) {
            throw new TaskManagerException();
        }
        int index = line.indexOf(" ");
        String sub = line.substring(index + 1);
        return sub;
    }

    
    /** 
     * Gets the task number from the user input.
     * 
     * @param line the user input
     * @return int the task number
     */
    public static int getTaskNumber(String line) {
        int index = 0;
        try {
            index = Integer.parseInt(getSecondWord(line));
            isTaskNumberValid(index);
        } catch (TaskManagerException e) {
            System.out.println("task number must be stated.");
        } catch (NumberFormatException e) {
            System.out.println("task number must be a number.");
        } catch (InvalidTypeException e) {
            System.out.println("task number does not exist.");
        }
        return index;
    }

    
    /** 
     * Gets the task description or task title from the user input.
     * 
     * @param line the user input
     * @return String the task description or task title
     */
    public static String getTaskDescription(String line) {
        String description = "";
        try {
            description = getSecondWord(line);
        } catch (TaskManagerException e) {
            System.out.println("task description for find function cannot be empty.");
        }
        return description;
    }

    
    /** 
     * Checks if the task number is between 1 and the number of tasks in the task list.
     * 
     * @param taskNumber the task number taken from user input
     * @throws InvalidTypeException if the task number does not meet above conditions
     */
    public static void isTaskNumberValid(int taskNumber) throws InvalidTypeException {
        if (taskNumber < 1 || taskNumber > TaskManager.getTaskCount()) {
            throw new InvalidTypeException();
        }
    }

    
    /** 
     * Gets the todo description from the user input then adds the event to the task list.
     * 
     * @param line the user input
     */
    public static void getTodoDetails(String line) {
        String todoLine = "";
        try {
            todoLine = getSecondWord(line);
            Todo todoTask = new Todo(todoLine);
            TaskManager.addTask(todoTask);
            System.out.println("Got it. I've added this task:\n" + todoTask.describeTask());
            System.out.println("\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.\n");
        } catch (TaskManagerException e) {
            System.out.println("description for todo cannot be empty.");
        }
    }

    
    /** 
     * Gets the deadline description and deadline date from the user input then adds the event to the task list.
     * 
     * @param line the user input
     */
    public static void getDeadlineDetails(String line) {
        String deadlineLine = "";
        try {
            deadlineLine = getSecondWord(line);
            String[] deadlineDetails = deadlineLine.split("/by");
            String deadlineDescription = deadlineDetails[0].trim();
            String deadlineDate = deadlineDetails[1].trim();
            Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
            TaskManager.addTask(deadlineTask);
            System.out.println("Got it. I've added this task:\n" + deadlineTask.describeTask());
            System.out.println("\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.\n");
        } catch (TaskManagerException e) {
            System.out.println("description for deadline cannot be empty.");
        }
    }

    
    /** 
     * Gets the event description, event start date and event end date from the user input then adds the event to the task list.
     * 
     * @param line the user input
     */
    public static void getEventDetails(String line) {
        String eventLine = "";
        try {
            eventLine = getSecondWord(line);
            String[] eventDetails = eventLine.split("/from");
            String[] eventTiming = eventDetails[1].split("/to");
            String eventDescription = eventDetails[0].trim();
            String eventFrom = eventTiming[0].trim();
            String eventTo = eventTiming[1].trim();
            Event eventTask = new Event(eventDescription, eventFrom, eventTo);
            TaskManager.addTask(eventTask);
            System.out.println("Got it. I've added this task:\n" + eventTask.describeTask());
            System.out.println("\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.\n");
        } catch (TaskManagerException e) {
            System.out.println("description for event cannot be empty.");
        }
    }

}
