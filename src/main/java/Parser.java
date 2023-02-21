import exceptions.MarkOutOfBounds;
import exceptions.UnmarkOutOfBounds;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    // Functions in this file deals with making sense of the user command

    /***
     * The fixed constants used to determine the commands by the user.
     */
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";
    public static final String DELETE = "delete";
    public static final String TODO = "todo";
    public static final String FIND = "find";

    /***
     * Prior to adding events with command deadline to the list, processDeadline separates the
     * important details within the input for data storage.
     * @param storedValues List of tasks from user inputs.
     * @param taskNum The existing position in the list.
     * @param line User input to be processed.
     * @return Updated position in the list.
     */
    static int processDeadline(ArrayList<Task> storedValues, int taskNum, String line) {
        int forwardSlash = line.indexOf('/');
        int endOfTask = forwardSlash - 1;
        int dates = forwardSlash + Duke.REMOVE_BY_NUM;
        Deadline deadlineInput = new Deadline(line.substring(9, endOfTask), line.substring(dates));
        taskNum = TaskList.addTask(storedValues, taskNum, deadlineInput);
        return taskNum;
    }

    /***
     * Prior to adding events with command toDo to the list, processtoDo separates the
     * important details within the input for data storage.
     * @param storedValues List of tasks from user inputs.
     * @param taskNum The existing position in the list.
     * @param line User input to be processed.
     * @return Updated position in the list.
     */
    static int processToDo(ArrayList<Task> storedValues, int taskNum, String line) {
        String removeCommand = line.substring(Duke.REMOVE_TODO_NUM);
        Todo todoInput = new Todo(removeCommand);
        taskNum = TaskList.addTask(storedValues, taskNum, todoInput);
        return taskNum;
    }

    /***
     * Prior to adding events to the list, processEvent separates the important details
     * within the input for further data storage.
     * @param storedValues List of tasks from user inputs.
     * @param taskNum The existing position in the list.
     * @param line User input to be processed.
     * @return Updated position in the list.
     */
    static int processEvent(ArrayList<Task> storedValues, int taskNum, String line) {
        int firstForwardSlash = line.indexOf('/');
        String taskName = line.substring(Duke.REMOVE_EVENT_NUM, firstForwardSlash - 1);
        String duration = line.substring(firstForwardSlash + Duke.REMOVE_FROM_NUM);

        int secondForwardSlash = duration.indexOf('/');
        String startingTime = duration.substring(0, secondForwardSlash - 1);
        String endingTime = duration.substring(secondForwardSlash + Duke.REMOVE_TO_NUM);
        Event eventInput = new Event(taskName, startingTime, endingTime);

        taskNum = TaskList.addTask(storedValues, taskNum, eventInput);
        return taskNum;
    }

    /***
     * Prior to searching the keyword that the user has inputted, processFind extracts the keyword for
     * further comparison.
     * @param storedValues The existing task list saved by the user.
     * @param line The whole command typed in by the user.
     */
    static void processFind(ArrayList<Task> storedValues, String line) {
        String wordToFind = line.substring(Duke.REMOVE_FIND_NUM);
        TaskList.findTasks(storedValues, wordToFind);
    }

    /***
     * Detects the user input and matches the command with the desired output.
     * @param line User input to be processed.
     * @param storedValues List of inputs stored by the user.
     * @return False if bye command is not called.
     */
    static boolean hasProcessedAllInputs(String line, ArrayList<Task> storedValues) {

        String splitInputs[] = line.split(" ", 2);
        String command = splitInputs[0];
        switch (command) {
        case BYE:
            return true;
        case LIST:
            String textToPrint = "Here are the tasks in your list:";
            TaskList.printList(storedValues, textToPrint);
            break;
        case MARK:
            try {
                TaskList.markItem(storedValues, line, Duke.taskNum);
            } catch (MarkOutOfBounds e) {
                System.out.println("Item to mark is not in list!");
            } catch (IOException e) {
                System.out.println("File is not found!");
            }
            break;
        case UNMARK:
            try {
                TaskList.unmarkItem(storedValues, line);
            } catch (UnmarkOutOfBounds e) {
                System.out.println("Item to unmark is not in list!");
            } catch (IOException e) {
                System.out.println("File is not found!");
            }
            break;
        case DEADLINE:
            try {
                Duke.taskNum = processDeadline(storedValues, Duke.taskNum, line);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Deadline is not added in the correct format! \n" +
                        "deadline <description> /by <date> \n");
            }
            break;
        case TODO:
            try {
                Duke.taskNum = processToDo(storedValues, Duke.taskNum, line);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("The description after todo cannot be empty!");
            }
            break;
        case EVENT:
            try {
                Duke.taskNum = processEvent(storedValues, Duke.taskNum, line);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Event is not added in the correct format! \n" +
                        "event <description> /from <date> /to <date> \n");
            }
            break;
        case DELETE:
            try {
                TaskList.deleteTask(line, storedValues);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Item to delete is not in the list!");
            } catch (IOException e) {
                System.out.println("Error!");
            }
            break;
        case FIND:
            processFind(storedValues, line);
            break;
        default:
            // Commands that are not listed above
            System.out.println("Invalid command, try again! \n");
        }
        return false;
    }
}
