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

public class TaskList {
    private final ArrayList<Task> TASK_LIST;

    public TaskList(ArrayList<Task> TASK_LIST) {
        this.TASK_LIST = TASK_LIST;
    }

    int getTaskListSize() {
        return TASK_LIST.size();
    }

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

    String markAsDone(String taskNumber) {
        Task taskToBeMarked = TASK_LIST.get(Integer.parseInt(taskNumber) - 1);
        taskToBeMarked.toggleDone();
        return taskToBeMarked.toString();
    }

    String markAsNotDone(String taskNumber) {
        Task taskToBeUnmarked = TASK_LIST.get(Integer.parseInt(taskNumber) - 1);
        taskToBeUnmarked.toggleDone();
        return taskToBeUnmarked.toString();
    }

    String deleteTask(String taskNumber) {
        Task taskToBeRemoved = TASK_LIST.get(Integer.parseInt(taskNumber) - 1);
        TASK_LIST.remove(Integer.parseInt(taskNumber) - 1);
        return taskToBeRemoved.toString();
    }
    ArrayList<Task> getTASK_LIST(){
        return TASK_LIST;
    }
}
