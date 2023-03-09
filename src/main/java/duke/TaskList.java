package duke;

import duke.exceptions.DukeException;
import duke.exceptions.UserInputException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

import static duke.Ui.*;
import static duke.exceptions.UserInputException.inputExceptionType.INVALID_TASK_TYPE;

/**
 * Represents the task list for user,
 * TaskList object contains a array of tasks with their description and time/ deadline if any.
 */
public class TaskList {
    private final ArrayList<Task> TASK_LIST;

    public TaskList(ArrayList<Task> TASK_LIST) {
        this.TASK_LIST = TASK_LIST;
    }

    int getTaskListSize() {
        return TASK_LIST.size();
    }

    /**
     * Returns added task in string format,
     * and add the new task to task list.
     * @param newTaskType Type of new task
     * @param newTaskInfo description and time/ deadline of new task
     * @return added task in string format
     * @throws DukeException if there is invalid input
     */
    String addTask(String newTaskType, String newTaskInfo) throws DukeException {
        Task newTask;
        switch (newTaskType) {
            case COMMAND_ADD_TODO_WORD:
                newTask = new Todo(newTaskInfo);
                break;
            case COMMAND_ADD_DEADLINE_WORD:
                newTask = new Deadline(newTaskInfo);
                break;
            case COMMAND_ADD_EVENT_WORD:
                newTask = new Event(newTaskInfo);
                break;
            default:
                throw new UserInputException(INVALID_TASK_TYPE);
        }
        TASK_LIST.add(newTask);
        return newTask.toString();
    }

    /**
     * Mark task specified to be done, return the task marked in string format
     * @param taskNumber index of the task to be marked
     * @return task marked in string format
     */
    String markAsDone(String taskNumber) {
        Task taskToBeMarked = TASK_LIST.get(Integer.parseInt(taskNumber) - 1);
        taskToBeMarked.toggleDone();
        return taskToBeMarked.toString();
    }

    /**
     * Mark task specified to be not done, return the task unmarked in string format
     * @param taskNumber index of the task to be unmarked
     * @return task unmarked in string format
     */
    String markAsNotDone(String taskNumber) {
        Task taskToBeUnmarked = TASK_LIST.get(Integer.parseInt(taskNumber) - 1);
        taskToBeUnmarked.toggleDone();
        return taskToBeUnmarked.toString();
    }

    /**
     * Delete the task specified by index, return the task deleted in string format
     * @param taskNumber index of the task to be deleted
     * @return task deleted in string format
     */
    String deleteTask(String taskNumber) {
        Task taskToBeRemoved = TASK_LIST.get(Integer.parseInt(taskNumber) - 1);
        TASK_LIST.remove(Integer.parseInt(taskNumber) - 1);
        return taskToBeRemoved.toString();
    }
    ArrayList<Task> getTASK_LIST(){
        return TASK_LIST;
    }
}
