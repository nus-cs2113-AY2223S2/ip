package buddy.tasks;

import buddy.Buddy;
import buddy.messages.Messages;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class
     *
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the task
     *
     * @return Description of task
     */
    public String getTaskName(){
        return this.description;
    }

    /**
     * Gets the type of the task
     *
     * @return String with type of task based on the Override
     */
    public String getType(){
        return "";
    }

    /**
     * Gets the status icon of the task (whether done or not)
     *
     * @return "X" if isDone is true and " " if isDone is false
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets isDone
     *
     * @param isDone True if task is done and false if task is not done
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Marks task as done with an "X" if task was not done previously
     * Else tells user that task has already been completed
     */
    public void markAsDone(){
        System.out.println(Messages.DIVIDER);
        if (!this.isDone){
            this.isDone = true;
            System.out.println("Great work on completing this task! Marked as done! :)");
        }
        else{
            System.out.println("This task had already been marked as done previously!");
        }
        System.out.println(this);
        System.out.println(Messages.DIVIDER);

    }

    /**
     * Mark task  with a " " if task was completed previously (Marks as not done)
     * Else tells user that the task was not even completed previously
     */
    public void markAsUndone(){
        System.out.println(Messages.DIVIDER);
        if (this.isDone){
            this.isDone = false;
            System.out.println("Come on, don't procrastinate! Marked as undone!");
        }
        else{

            System.out.println("This task was previously not completed! Please do it properly!");
        }
        System.out.println(this);
        System.out.println(Messages.DIVIDER);
    }

    /**
     * @return String to be printed out when each task is printed
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Prints message to user after adding a task
     */
    public void printAfterAddingTask(){
        if (Buddy.taskCount == 1){
            System.out.println("Got it! I have added this task!: \n" + this + "\n" + "Now you have " + Buddy.taskCount + " task remaining! Almost there, buddy!");
        }
        else{
            System.out.println("Got it! I have added this task! \n" + this + "\n" + "Now you have " + Buddy.taskCount + " tasks remaining! Let's finish them faster and relax!");

        }

    }

    /**
     * Prints message to user after deleting a task
     */
    public void printAfterDeletingTask() {
        if (Buddy.taskCount == 0) {
            System.out.println( "OK I have deleted this task!: \n" + this + "\n" + "CONGRATS BUDDY ON FINISHING ALL YOUR TASKS! TIME TO RELAX WITH YOUR FRIENDS AND FAMILY! :)");
        } else if (Buddy.taskCount == 1) {
            System.out.println("YAY ONE LESS TO GO! I have deleted this task!:  \n" + this + "\n" + "Now you have JUST " + Buddy.taskCount + " task remaining! CHOP CHOP FINISH IT");
        } else{
            System.out.println("YAY ONE LESS TO GO! I have deleted this task!:  \n" + this + "\n" + "Now you have " + Buddy.taskCount + " tasks remaining! Type list to see remaining tasks");

        }
    }

}
