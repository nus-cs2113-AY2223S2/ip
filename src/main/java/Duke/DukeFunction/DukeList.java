package Duke.DukeFunction;

import Duke.DukeCommandLine.DukeException;
import Duke.DukeTask.DukeTask;

import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class DukeList {
    private static final ArrayList<DukeTask> taskList = new ArrayList<>();
    private static final String FILE_PATH = "data/list.txt";
    private static final String INVALID_ID_MESSAGE = "Sorry, the id is invalid!";
    private static final String WRONG_DATE_FORMAT_MESSAGE = "Please use the format: yyyy-mm-dd";
    private static final String TASK_ADDED_INFORMATION = "Got it. I've added this task:";
    private static final String TASK_LIST_INFORMATION = "Got it. I've added this task:";
    private static final String TASK_LIST_MATCHING_INFORMATION = "Here are the matching tasks in your list:";
    private static final String TASK_DELETED_INFORMATION = "Noted. I've removed this task:";
    private static final String TASK_MARK_INFORMATION = "Nice! I've marked this task as done:";
    private static final String TASK_UNMARK_INFORMATION = "Nice! I've marked this task as undone:";
    private static final String MARK_FLAG = "[mark] ";
    private static final String UNMARK_FLAG = "[unmark] ";
    private static final String DATE_FLAG = "[date] ";
    private static final String DELETE_FLAG = "[delete] ";

    private static void isValidID(int id) throws DukeException {
        boolean isIDInValid = id < 0 || id >= taskList.size();
        if(isIDInValid) {
            throw new DukeException(INVALID_ID_MESSAGE);
        }
    }
    /**
     * Adds a task to the task list.
     * @param task the task to be added.
     * @throws DukeException if the task cannot be added (when the arraylist is full).
     */
    public void addTask(DukeTask task) throws DukeException {
        try {
            taskList.add(task);
            System.out.println(TASK_ADDED_INFORMATION);
            task.printTask();
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Lists all the tasks in the task list.
     */
    public void listTask() {
        System.out.println(TASK_LIST_INFORMATION);
        for(int i = 0; i < taskList.size(); i++) {
            taskList.get(i).printTask(i);
        }
    }

    /**
     * Marks a task as done with the given id.
     * @param id the id of the task to be marked as done.
     * @throws DukeException if the id is invalid (when the id is out of bound).
     */
    public void markDone(int id) throws DukeException {
        try {
            isValidID(id);
            taskList.get(id).markAsDone();
            System.out.println(TASK_MARK_INFORMATION);
            taskList.get(id).printTask();
        } catch (DukeException e) {
            throw new DukeException(MARK_FLAG + e.getMessage());
        }
    }

    /**
     * Unmarks a task as done with the given id.
     * @param id the id of the task to be undone;
     * @throws DukeException if the id is invalid (when the id is out of bound).
     */
    public void unmarkDone(int id) throws DukeException {
        try {
            isValidID(id);
            taskList.get(id).unmarkAsDone();
            System.out.println(TASK_UNMARK_INFORMATION);
            taskList.get(id).printTask();
        } catch (DukeException e) {
            throw new DukeException(UNMARK_FLAG + e.getMessage());
        }
    }

    /**
     * Deletes a task from the task list with the given id.
     * @param id the id of the task to be deleted.
     * @throws DukeException if the id is invalid (when the id is out of bound).
     */
    public void deleteTask(int id) throws DukeException {
        try {
            isValidID(id);
            System.out.println(TASK_DELETED_INFORMATION);
            taskList.get(id).printTask();
            taskList.remove(id);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (DukeException e) {
            throw new DukeException(DELETE_FLAG + e.getMessage());
        }
    }

    /**
     * Stores the task list by using a file writer.
     * @param fileWriter the file writer to write the task list to the file.
     * @throws IOException if the file cannot be written.
     */
    public void storeTask(FileWriter fileWriter) throws IOException {
        try {
            for (int i = 0; i < taskList.size(); i++) {
                fileWriter.write(taskList.get(i).saveTask());
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Lists the tasks which are due on the given date,
     * or the events which are happening on the given date.
     * @param date Date that the user wants to find for the event/deadline.
     * @throws DukeException if the date cannot be parsed.
     */
    public void listTaskByDate(String date) throws DukeException {
        try {
            LocalDate localDate = LocalDate.parse(date);
            System.out.println(TASK_LIST_MATCHING_INFORMATION);
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).isDateMatch(localDate)) {
                    taskList.get(i).printTask(i);
                }
            }
        } catch (Exception e) {
            throw new DukeException(DATE_FLAG + e.getMessage() +
                    WRONG_DATE_FORMAT_MESSAGE);
        }
    }

    /**
     * Finds and Prints the tasks which contains the given keyword.
     * @param keyword the keyword to be searched.
     */
    public void findTask(String keyword) {
        System.out.println(TASK_LIST_MATCHING_INFORMATION);
        for(int i = 0; i < taskList.size(); i++) {
            if(taskList.get(i).containsName(keyword)) {
                taskList.get(i).printTask(i);
            }
        }
    }
}
