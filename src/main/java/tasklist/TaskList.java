package tasklist;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import exceptions.EventTimingException;
import common.Messages;
import parser.Parser;


import java.util.ArrayList;

public class TaskList {
    private static final Integer DEADLINE_LEN = 9;
    private static final Integer TODO_LEN = 5;
    private static final Integer EVENT_LEN = 6;
    private static final Integer _FROM_LEN = 6;
    private static final Integer _TO_LEN = 4;

    private ArrayList<Task> tasks;
    private Parser parser = new Parser();

    /**
     * Constructor of TaskList.
     */
    public TaskList() {
    }

    /**
     * Constructor of TaskList when an ArrayList of Task is passed in.
     * @param tasks ArrayList of Task already initialised.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Print out the list of tasks stored in the ArrayList of Task in sequence.
     */
    public void printTaskList() {
        System.out.println(Messages.PRINT_TASK_HEADING);
        for (int i = 0; i < tasks.size(); i++) {
            int taskIndex = i + 1; // change to one-based indexing
            System.out.println(taskIndex + ". " + tasks.get(i).printTask());
        }
        System.out.println(Messages.LINE);
    }

    /**
     * Check if index is valid.
     *
     * @param taskIndex index to be checked, as Integer
     * @return True if index is within the available tasks range. False if out of range.
     */
    boolean isValidIndex(int taskIndex) {
        return taskIndex >= 1 && taskIndex <= tasks.size();
    }

    /**
     * Marks task as done.
     *
     * @param input User input as String.
     * @throws NumberFormatException if task index is not specified
     */
    public void markTask(String input) throws NumberFormatException {
        int taskIndex = parser.markTaskIndex(input);
        if (isValidIndex(taskIndex)) {
            tasks.get(taskIndex - 1).markAsDone();
            System.out.println(Messages.LINE + "Task " + taskIndex + " marked as done:\n" +
                    tasks.get(taskIndex - 1).printTask() + '\n' + Messages.LINE);
        } else {
            System.out.println("Task " + taskIndex + " not found. Please try again.\n" + Messages.LINE);
        }
    }

    /**
     * Marks task as not done.
     *
     * @param input user input as String
     * @throws NumberFormatException if task index is not specified
     */
    public void unmarkTask(String input) throws NumberFormatException {
        int taskIndex = parser.unmarkTaskIndex(input);
        if (isValidIndex(taskIndex)) {
            tasks.get(taskIndex - 1).markAsUndone();
            System.out.println(Messages.LINE + "Task " + taskIndex + " marked as not done yet:\n" +
                    tasks.get(taskIndex - 1).printTask() + '\n' + Messages.LINE);
        } else {
            System.out.println("Task " + taskIndex + " not found. Please try again.\n" + Messages.LINE);
        }
    }

    /**
     * Creates ToDo task and adds it to the tasks list.
     *
     * @param input user input as String
     * @throws IndexOutOfBoundsException if insufficient information is given to create ToDo task.
     */
    public void createTodo(String input) throws IndexOutOfBoundsException {
        ToDo todoTask = new ToDo(input.substring(TODO_LEN));
        tasks.add(todoTask);
        System.out.println(Messages.LINE + "Great! I've added this task:\n" + "   " + todoTask.printTask());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.\n" + Messages.LINE);
    }

    /**
     * Checks if date/time for deadline is specified.
     *
     * @param input user input as String
     * @return true if no date/time provided, false if provided
     */
    boolean isNotValidDeadlineInput(String input) {
        boolean isKeywordFound = input.contains("/by");
        boolean isDateFound = input.indexOf("/by") + 4 <= input.length();
        return !isKeywordFound || !isDateFound;
    }

    /**
     * Creates Deadline task and adds it to the tasks list.
     *
     * @param input user input as String
     * @throws IndexOutOfBoundsException if insufficient information is given to create Deadline task.
     */
    public void createDeadline(String input) throws IndexOutOfBoundsException {
        if (isNotValidDeadlineInput(input)) {
            throw new StringIndexOutOfBoundsException();
        }
        String deadlineDate = parser.deadlineDate(input);
        Deadline deadlineTask =
                new Deadline(input.substring(DEADLINE_LEN, input.indexOf("/by")), deadlineDate);
        tasks.add(deadlineTask);
        System.out.println(Messages.LINE + "Great! I've added this task:\n" + "   " + deadlineTask.printTask());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.\n" + Messages.LINE);
    }

    /**
     * Checks if starting and ending time of an event are specified.
     *
     * @param input user input as String
     * @return true if no starting or ending time is specified, false if specified
     */
    boolean isNotValidEventInput(String input) {
        boolean isFromFound = input.contains("/from");
        boolean isToFound = input.contains("/to");
        boolean isStartingDateFound = input.indexOf("/from") + _FROM_LEN <= input.length();
        boolean isEndingDateFound = input.indexOf("/to") + _TO_LEN <= input.length();
        return !isFromFound || !isToFound || !isStartingDateFound || !isEndingDateFound;
    }

    /**
     * Checks if starting time is specified before ending time.
     *
     * @param input user input as String
     * @return true if ending time specified before starting time, false otherwise
     */
    boolean isStartEndWrongOrder(String input) {
        return input.indexOf("/from") > input.indexOf("/to");
    }

    /**
     * Creates Event task and adds it to the tasks list
     *
     * @param input user input as String
     * @throws IndexOutOfBoundsException if insufficient information is given to create an event task
     * @throws EventTimingException if ending time is specified before starting time
     */
    public void createEvent(String input) throws IndexOutOfBoundsException, EventTimingException {
        if (isNotValidEventInput(input)) {
            throw new StringIndexOutOfBoundsException();
        } else if (isStartEndWrongOrder(input)) {
            throw new EventTimingException();
        }
        String startTime = parser.eventStartTime(input);
        String endTime = parser.eventEndTime(input);
        Event eventTask = new Event(input.substring(EVENT_LEN, input.indexOf("/from")), startTime, endTime);
        tasks.add(eventTask);
        System.out.println(Messages.LINE + "Great! I've added this task:\n" + "   " + eventTask.printTask());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.\n" + Messages.LINE);
    }

    /**
     * Deletes a task based on index.
     *
     * @param input user input as String
     * @throws NumberFormatException if no task index is specified
     */
    public void deleteTask(String input) throws NumberFormatException {
        int index = parser.deleteTaskIndex(input);
        int zeroBaseIndex = index - 1;
        if (isValidIndex(index)) {
            System.out.println(Messages.LINE + "I've removed task "
                    + index + ":\n" + "   " + tasks.get(index - 1).printTask());
            tasks.remove(zeroBaseIndex);
            System.out.println("Now you have " + tasks.size() + " task(s) in the list.\n" + Messages.LINE);
        } else {
            System.out.println("Task " + index + " not found. Please try again.\n" + Messages.LINE);
        }
    }

    /**
     * Finds all available tasks containing the keyword specified.
     *
     * @param input user input as String
     * @throws IndexOutOfBoundsException if no keyword is provided
     */
    public void findTask(String input) throws IndexOutOfBoundsException {
        String keyWord = parser.searchKeyWord(input);
        if (keyWord.equals("")) {
            System.out.println("No keyword found.\n" + Messages.LINE);
        } else {
            int taskIndex = 1;
            int zeroBaseIndex = 0;
            System.out.println(Messages.LINE + "Here are the matching tasks in your list:\n");
            for (Task t : tasks) {
                if (t.getDescription().contains(keyWord)) {
                    System.out.println(taskIndex + ". " + tasks.get(zeroBaseIndex).printTask());
                }
                taskIndex++;
                zeroBaseIndex++;
            }
            System.out.println(Messages.LINE);
        }
    }


}
