import exceptions.DukeException;

import java.util.ArrayList;

import static messages.ErrorMessages.MESSAGE_MISSING_INFO_ERROR;
import static messages.newTaskMessages.MESSAGE_CREATE_NEW_DEADLINE;
import static messages.newTaskMessages.MESSAGE_CREATE_NEW_EVENT;
import static messages.newTaskMessages.MESSAGE_CREATE_NEW_TODO;

public class TaskList extends ArrayList<Task> {
    public static ArrayList<Task> items = new ArrayList<>();

    /**
     * creates a new Todo task
     *
     * @param arguments String containing description of task to be added
     * @return a Todo task
     */
    public static Task createNewTodo(String arguments) {
        System.out.println(MESSAGE_CREATE_NEW_TODO + arguments);
        return new Todo(arguments);
    }

    /**
     * creates a new Event task
     *
     * @param arguments String containing description, start and end date and/or time of task to be added
     * @return an Event task
     * @throws DukeException if there are arguments missing
     */
    public static Task createNewEvent(String arguments) throws DukeException {
        try {
            String[] eventInfo = arguments.split("/from ");
            if (eventInfo.length < 2) {
                throw new DukeException(MESSAGE_MISSING_INFO_ERROR);
            }
            String[] eventDuration = eventInfo[1].split("/to ");
            if (eventDuration.length < 2) {
                throw new DukeException(MESSAGE_MISSING_INFO_ERROR);
            }
            System.out.println(MESSAGE_CREATE_NEW_EVENT + eventInfo[0] + "(from: " + eventDuration[0] + " to: " + eventDuration[1] + ")");
            return new Event(eventInfo[0], eventDuration[0], eventDuration[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_MISSING_INFO_ERROR);
        }
    }

    /**
     * creates a new Deadline task
     *
     * @param arguments String containing description, and due date and/or time of task to be added
     * @return a Deadline task
     * @throws DukeException if there are arguments missing
     */
    public static Task createNewDeadline(String arguments) throws DukeException {
        String[] deadlineInfo = arguments.split("/by ");
        if (deadlineInfo.length < 2) {
            throw new DukeException(MESSAGE_MISSING_INFO_ERROR);
        }
        System.out.println(MESSAGE_CREATE_NEW_DEADLINE + deadlineInfo[0] + "(by: " + deadlineInfo[1] + ")");
        return new Deadline(deadlineInfo[0], deadlineInfo[1]);
    }
}