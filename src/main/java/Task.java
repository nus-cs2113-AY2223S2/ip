public class Task {

    protected String description;
    protected boolean isDone;

    /**
    * initializer of Task class
    *
    * @param description description of task
    */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
    * method to get the description of task
    *
    * @return the description
    */
    public String getTask(){
        return this.description;
    }

    /**
    * method to print the task
    *
    * @return the description, status icon and the task icon
    */
    public String printTask() {
        return ("[" + this.getTaskIcon() + "] " + "[" + this.getStatusIcon() + "] " + this.getTask() + " ");
    }

    /**
    * method to get the status icon of task
    *
    * @return the status
    */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    /**
    * method to get the task icon 
    *
    * @return the icon
    */
    public String getTaskIcon() {
        return " ";
    }

    /**
    * method to mark the status of task as done
    */
    public void MarkStatusDone() {

        this.isDone = true;
    }

    /**
    * method to mark the status of task as not done
    */
    public void MarkStatusUndone() {
        this.isDone = false;
    }

    /**
    * Method to get day that deadline is by
    *
    * @return day
    */
    public String getBy() {
        return " ";
    }

    /**
    * Method to get day that event is up to
    *
    * @return to
    */
    public String getTo() {
        return " ";
    }

    /**
    * Method to get day that event is from
    *
    * @return from
    */
    public String getFrom() {
        return " ";
    }
}