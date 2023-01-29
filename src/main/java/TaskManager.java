//mark tasks as done/undone

/**
 * The TaskManager class is used to manage tasks, allowing them to be marked as done or undone.
 * <p>
 * It contains a String variable to store the task description and a boolean variable to keep track of whether the task is done or not.
 * <p>
 * The class has methods to mark a task as done or undone, get the task description,
 * <p>
 * set a checkmark icon (√ or □) based on the task status, and print the task with its index and status.
 */

public class TaskManager {
    private String aboutTask;
    private boolean isDone;

    /**
     * The constructor TaskManager creates a new instance of the class and initializes
     * the task description and sets isDone as false by default.
     *
     * @param aboutTask A string containing the task description.
     */
    public TaskManager(String aboutTask) {
        this.aboutTask = aboutTask;
        //unless explicitly marked by user with Mark command, false by default
        this.isDone = false;
    }

    /**
     * The getAboutTask() method returns the task description.
     *
     * @return A string containing the task description.
     */
    public String getAboutTask() {
        return aboutTask;
    }

    /**
     * The markAsDone() method marks the task as done.
     *
     * @return A boolean value indicating whether the task was marked as done successfully.
     * Returns false if the task is already marked as done.
     */
    public boolean markAsDone() {
        //if it has already been marked once, don't remark it again. it makes the program's output strange
        if (this.isDone == true) {
            return false;
        } else {
            this.isDone = true;
            return true;
        }
    }

    /**
     * The markAsUndone() method marks the task as undone.
     *
     * @return A boolean value indicating whether the task was marked as undone successfully.
     * Returns false if the task is already marked as undone.
     */
    public boolean markAsUndone() {
        if (this.isDone == false) {
            return false;
        }
        this.isDone = false;
        return true;
    }

    /**
     * The setCheckMark() method sets a checkmark icon (√ or □) based on the task status.
     *
     * @return A character representing the checkmark icon (√ or □).
     */
    public char setCheckMark() {
        //false is empty box, too lazy to do the [X]
        char icon = '□';
        if (this.isDone == true) {
            icon = '√';
        }
        return icon;
    }

    /**
     * The printTaskAndStatus() method prints the task with its index and status.
     * Acknowledgement: https://www.baeldung.com/java-printstream-printf
     *
     * @param index An integer representing the index of the task.
     */

    public void printTaskAndStatus(int index) {
        System.out.printf("%d: %c %s %n", index + 1, setCheckMark(), getAboutTask());

    }
}
