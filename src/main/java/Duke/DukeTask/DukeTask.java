package Duke.DukeTask;

import java.time.LocalDate;

public class DukeTask {
    protected String taskName;
    protected boolean isDone;

    /**
     * Constructor for DukeTask
     * @param taskName Name of the task
     */
    public DukeTask(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * @return the task status icon, with X if done, else with space.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks the particular task as done.
     */
    public void markAsDone() {
        if(this.isDone) {
            System.out.println("[Warning] This task is already done!");
        }
        this.isDone = true;
    }

    /**
     * Unmarks the particular task as done (withdrawing the done status).
     */
    public void unmarkAsDone() {
        if(!this.isDone) {
            System.out.println("[Warning] This task is not done yet!");
        }
        this.isDone = false;
    }

    /**
     * Prints the task in the format of [Task Type] [Status Icon] Task Name.
     */
    public void printTask(){
        System.out.println("[T] [" +
                this.getStatusIcon() + "] " + this.taskName);
    }

    /**
     * Prints the task in the format of id. [Task Type] [Status Icon] Task Name.
     * @param id the id of the task in the list.
     */
    public void printTask(int id) {
        System.out.println((id+1) + ".[T] [" +
                this.getStatusIcon() + "] " + this.taskName);
    }

    /**
     * Returns the String in the format of [Task Type] | [Status Icon] | Task Name,
     * which is used to save the task in the hard disk.
     * @return the string representation of the task to be saved in the file.
     */
    public String saveTask() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.taskName + '\n';
    }

    /**
     * Checks whether the task is a date match with the given date. (in the format of yyyy-mm-dd)
     * @param date the date to be checked.
     * @return false, since this is a task without date.
     */
    public boolean isDateMatch(LocalDate date) {
        return false;
    }

    /**
     * Checks whether the task is a keyword match with the given keyword.
     * @param keyword the keyword to be searched.
     * @return true if the task name contains the keyword, else false.
     */
    public boolean containsName(String keyword) {
        return this.taskName.contains(keyword);
    }
}
