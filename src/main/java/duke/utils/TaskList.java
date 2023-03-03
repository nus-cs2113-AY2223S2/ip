package duke.utils;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

import static duke.utils.Ui.DEADLINE_TIME_ERROR_MESSAGE;
import static duke.utils.Ui.EVENT_TIME_ERROR_MESSAGE;
import static duke.utils.Ui.INVALID_NUM_ERROR_MESSAGE;
import static duke.utils.Ui.LINE;

/**
 * Represents all the task data in the application.
 * Contains all the methods for handling the data in task list.
 * Calls message from a Ui object to interact with user.
 */
public class TaskList {
    Ui ui = new Ui();
    public ArrayList<Task> list = new ArrayList<>();
    public ArrayList<String> taskDescriptions= new ArrayList<>();
    public ArrayList<Task> matchingList = new ArrayList<>();
    public int matchingTasksNum;

    public int currentTaskNum;

    /**
     * Prints all the current tasks in the order in which they were added.
     * Puts an index in front of the task data.
     *
     * @param currentTaskNum The current number of tasks in the task list.
     */
    public void printAllTasks(int currentTaskNum) {
        int currentPrintedTask = 0;
        int placeHolder = currentTaskNum;
        System.out.println(LINE);
        if (placeHolder == 0) {
            System.out.println("No Task!");
        } else {
            while (placeHolder > 0) {
                System.out.println(currentPrintedTask + 1 + ". " + list.get(currentPrintedTask).toString());
                currentPrintedTask++;
                placeHolder--;
            }
        }
        System.out.println(LINE);
    }

    public void findMatchingTasks(String keyword) {
        for (Task task : list) {
            taskDescriptions.add(task.description);
        }
        int i = 0;
        for (String taskDescription : taskDescriptions) {
            if (taskDescription.contains(keyword)) {
                matchingList.add(list.get(i));
            };
            i++;
        }
    }

    public void printMatchingTasks() {
        int index = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task matchingTask : matchingList) {
            System.out.println(index + ". " + matchingTask.toString());
            index++;
        }
        System.out.println(System.lineSeparator() + Ui.LINE);
        taskDescriptions.clear();
        matchingList.clear();
    }

    /**
     * Creates a new Event type object and adds the event to the task list.
     * Separates the task description with the event start and end time.
     * Prints error message if the content from user input is invalid.
     *
     * @param content The task description from user input.
     */
    public void addEvent(String content) {
        String[] phrases;
        phrases = content.split("/");
        if (phrases.length < 3) {
            ui.printErrorMessage(EVENT_TIME_ERROR_MESSAGE);
        } else {
            list.add(new Event(phrases[0], phrases[1], phrases[2]));
        }
    }

    /**
     * Creates a new Deadline type object and adds the object to the task list.
     * Separates the task description with the deadline.
     * Prints error message if the content from user input is invalid.
     *
     * @param content The task description from user input.
     */
    public void addDeadline(String content) {
        String[] phrases;
        phrases = content.split("/by ");
        if (phrases.length < 2) {
            ui.printErrorMessage(DEADLINE_TIME_ERROR_MESSAGE);
        } else {
            list.add(new Deadline(phrases[0], phrases[1]));
        }
    }

    /**
     * Creates a new Todo type object and adds the task to the task list.
     *
     * @param content The task description from user input.
     */
    public void addTodo(String content) {
        list.add(new Todo(content));
    }

    /**
     * Deletes a task according to the index user entered as a string.
     * Decreases the total number of tasks after deletion.
     *
     * @param content The string representation of the task index from user input.
     */
    public void deleteTask(String content) {
        list.remove(Integer.parseInt(content) - 1);
        currentTaskNum--;
    }

    /**
     * Toggles the status of a task and prints the updated task data.
     *
     * @param content A string representation of the task index.
     * @param shouldMarkAsDone A boolean representation of whether the task status
     *                         should be changed to "done" or "not done"
     * @throws DukeException If the index is invalid or out of bound.
     */
    public void toggleMark(String content, boolean shouldMarkAsDone) throws DukeException {
        int posOfMark = Integer.parseInt(content) - 1;
        if (!(posOfMark >= 0 && posOfMark <= currentTaskNum)) {
            ui.printErrorMessage(INVALID_NUM_ERROR_MESSAGE);
            throw new DukeException();
        } else {
            if (shouldMarkAsDone) {
                list.get(posOfMark).printSetDoneMessage();
            } else {
                list.get(posOfMark).printSetUndoneMessage();
            }
        }
    }

    public void printAddTaskMessage() {
        System.out.println(LINE + "Got it. I've added this task:\n"
                + "  "
                + this.list.get(currentTaskNum).toString()
                + System.lineSeparator());
        currentTaskNum++;
        printTotalNumOfTasks(currentTaskNum);
    }

    public void printDeleteTaskMessage(int taskNum) {
        System.out.println(LINE + "Got it. I've deleted this task:\n"
                + "  "
                + this.list.get(taskNum).toString()
                + System.lineSeparator());
    }

    /**
     * Prints the message containing the total number of tasks in the task list.
     *
     * @param currentTaskNum The current number of tasks in the task list.
     */
    public void printTotalNumOfTasks(int currentTaskNum) {
        if (currentTaskNum == 1) {
            System.out.println("Now you have " + currentTaskNum + " task in the list."
                    + System.lineSeparator() + LINE);
        } else {
            System.out.println("Now you have " + currentTaskNum + " tasks in the list."
                    + System.lineSeparator() + LINE);
        }
    }
}