package helpers;

import corefunctionalities.TaskList;
import exceptions.*;

/**
 * This class generates the conditions during which the exceptions are invoked and passes to the {@link Command} Class
 * to be further passed down to the {@link corefunctionalities.ExceptionHandler} class.
 *
 * @author Muthya Narayanchary Akhil
 */
public class ExceptionGenerator {
    protected Parser parser = new Parser();

    /**
     * An empty constructor to initialize the ExceptionGenerator Class
     */
    public ExceptionGenerator () {}

    /**
     * Generates the potential Exceptions for a {@link dataypes.Todos} Object
     *
     * @param holder The split user input which can be processed further
     * @throws EmptyTodo In the case the description for the Todo is empty
     */
    public void todoExceptionGenerator(String [] holder) throws EmptyTodo {
        if(holder.length<2)
        {
            throw new EmptyTodo();
        }
    }

    /**
     * Generates the potential Exceptions for a {@link dataypes.Deadlines} Object
     *
     * @param deadlineAndDescription The split user input which can be processed further
     * @param userInput The raw user input which can be processed further
     * @throws EmptyDeadline In the case the description for the Deadline is empty
     * @throws DeadlineMissingPhrase In the case the /by phrase is missing
     * @throws DeadlineIsBlank In the case that the deadline is empty.
     */
    public void deadlineExceptionGenerator(String[]  deadlineAndDescription, String userInput) throws EmptyDeadline, DeadlineMissingPhrase, DeadlineIsBlank{
        if(!userInput.contains("/by ") && userInput.split(" ").length>1) {
            throw new DeadlineMissingPhrase();
        } else if(deadlineAndDescription.length==1) {
            throw new EmptyDeadline();
        } else if(deadlineAndDescription[1].isBlank()) {
            throw new DeadlineIsBlank();
        }
    }

    /**
     * Generates the potential Exceptions for a {@link dataypes.Events} Object
     *
     * @param eventDescription The split user input which can be processed further
     * @param userInput The raw user input which can be processed further
     * @throws EmptyEvent In the case the description for the Event is empty
     * @throws EventMissingBothPhrases In the case that both /from and /to are missing from the userInput
     * @throws EventMissingToPhrase In the case that /to phrase is missing from the userInput
     * @throws EventMissingFromPhrase In the case that /from phrase is missing from the userInput
     * @throws EventFromIsBlank In the case the /from date is missing
     * @throws EventToIsBlank In the case the /to date is missing
     */

    public void eventExceptionGenerator(String [] eventDescription, String userInput) throws EmptyEvent, EventMissingBothPhrases, EventMissingToPhrase, EventMissingFromPhrase, EventFromIsBlank, EventToIsBlank {
        if(!userInput.contains("/from") && userInput.split(" ").length>1) {
            throw new EventMissingFromPhrase();
        } else if(!userInput.contains("/to") && userInput.split(" ").length>1) {
            throw new EventMissingToPhrase();
        } else if(!(userInput.contains("/from") || userInput.contains("/to")) && userInput.split(" ").length>1) {
            throw new EventMissingFromPhrase();
        } else if(!(userInput.contains("/from") || !userInput.contains("/to")) && userInput.split(" ").length>1) {
            throw new EventMissingBothPhrases();
        }  else if (eventDescription.length==1) {
            throw new EmptyEvent();
        }else if(eventDescription[1].isBlank()) {
            throw new EventFromIsBlank();
        } else if(eventDescription[2].isBlank()) {
            throw new EventToIsBlank();
        }
    }

    /**
     * This generates the exceptions when a {@link dataypes.Task} is being marked
     *
     * @param userInput The serial number of the {@link dataypes.Task} which needs to be marked
     * @param taskList The {@link java.util.ArrayList<dataypes.Task>} containing all the <code>Tasks</code>
     * @throws MarkQualityException In the case the serial number of the task to be marked lies outside the valid range.
     * @throws NumberFormatException In the case the serial number to be marked is not a number
     * @throws TaskMarked If the Task has already been marked
     */
    public void markExceptionGenerator(String userInput, TaskList taskList) throws MarkQualityException, NumberFormatException, TaskMarked {
        if(parser.isInRange(userInput, taskList)==false) {
            throw new MarkQualityException();
        } else if(taskList.getTask(Integer.parseInt(userInput.split(" ")[1]) - 1).getStatus()==true) {
            throw new TaskMarked();
        }
    }

    /**
     * This generates the exceptions when a {@link dataypes.Task} is being unnmarked
     *
     * @param userInput The serial number of the {@link dataypes.Task} which needs to be unmarked
     * @param taskList The {@link java.util.ArrayList<dataypes.Task>} containing all the <code>Tasks</code>
     * @throws UnmarkQualityException In the case the serial number of the task to be unmarked lies outside the valid range.
     * @throws NumberFormatException In the case the serial number to be unmarked is not a number
     * @throws TaskUnMarked If the Task has already been unmarked
     */
    public void unMarkExceptionGenerator(String userInput, TaskList taskList) throws UnmarkQualityException, NumberFormatException, TaskUnMarked {
        if(parser.isInRange(userInput, taskList)==false) {
            throw new UnmarkQualityException();
        } else if(taskList.getTask(Integer.parseInt(userInput.split(" ")[1]) - 1).getStatus()==false) {
            throw new TaskUnMarked();
        }
    }
}
