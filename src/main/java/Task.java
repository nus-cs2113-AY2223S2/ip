public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected static int taskCount = 0;
    /**
     * Initialization of the object
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void deleteTask() {
        --taskCount;
        System.out.println("Noted. I've removed this task: \n" + "  " + statusMessage() + "\n" +
                "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * @return String that corresponding to the status of the Task
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * To change the boolean of the object to true, meaning that is has been done
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * To change the boolean of the object to false, meaning that is has NOT been done
     */
    public void unmarkDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
    }

//    public abstract String statusMessage()  {return getStatusIcon() + " " + description;}
    public abstract String statusMessage();

    public abstract String addedMessage();

}
