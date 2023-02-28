package Duke.DukeFunction;

import Duke.DukeCommandLine.DukeException;
import Duke.DukeTask.DukeTask;

import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DukeList {
    private static final ArrayList<DukeTask> taskList = new ArrayList<>();
    private static final String FILE_PATH = "data/list.txt";
    private static void isValidID(int id) throws DukeException {
        boolean isIDInValid = id < 0 || id >= taskList.size();
        if(isIDInValid) {
            throw new DukeException("Sorry, the id is invalid!");
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
            System.out.println("Got it. I've added this task:");
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
        System.out.println("Here are the tasks in your list:");
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
            System.out.println("Nice! I've marked this task as done:");
            taskList.get(id).printTask();
        } catch (DukeException e) {
            throw new DukeException("[mark] " + e.getMessage());
        }
    }

    /**
     * Unmarks a task as done with the given id.
     * @param id the id of the task to be unmaked as done.
     * @throws DukeException if the id is invalid (when the id is out of bound).
     */
    public void unmarkDone(int id) throws DukeException {
        try {
            isValidID(id);
            taskList.get(id).unmarkAsDone();
            System.out.println("Nice! I've unmarked this task as done:");
            taskList.get(id).printTask();
        } catch (DukeException e) {
            throw new DukeException("[unmark] " + e.getMessage());
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
            System.out.println("Noted. I've removed this task:");
            taskList.get(id).printTask();
            taskList.remove(id);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (DukeException e) {
            throw new DukeException("[delete] " + e.getMessage());
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
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).isDateMatch(localDate)) {
                    taskList.get(i).printTask(i);
                }
            }
        } catch (Exception e) {
            throw new DukeException("[date] " + e.getMessage() +
                    "\nPlease use the format: yyyy-mm-dd");
        }
    }

    /**
     * Finds and Prints the tasks which contains the given keyword.
     * @param keyword the keyword to be searched.
     */
    public void findTask(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            if(taskList.get(i).containsName(keyword)) {
                taskList.get(i).printTask(i);
            }
        }
    }
}
