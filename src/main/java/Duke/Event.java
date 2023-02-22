package Duke;

/**
 * Contains information related to the Event class which is a subclass of Deadline class
 */

public final class Event extends Deadline {
    public String startTime;

    /**
     * sets the start time of the Event based on the user input
     * @param startTime start time of the event
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the start time of an Object of Type Event
     * @return start time of the event
     */
    public String getStartTime() {
        return this.startTime;
    }


    public Event(String description) {
        super(description);
    }

    /**
     * Prints to user interface a message that tells the user a new event has successfully been created
     * and shows the task name , start and end time
     */
    public void newEventAction() {
        System.out.println("Got it. I've added \"" + this.taskName +"\"");
        System.out.println("with a start time of: " + this.getStartTime());
        System.out.println("and an ending time of: " + this.getEndTime());


    }

    /**
     * Prints to user interface the completion status, name, start and end time of an event
     */
    @Override
    public void printTask() {
        if (this.isCompleted) {
            System.out.println(".[" + getTaskType(this) + "][X]" + this.taskName + "(from: " + this.getStartTime() + " to: " + this.getEndTime() + ")");
        } else {
            System.out.println(".[" + getTaskType(this) + "][ ]" + this.taskName + "(from: " + this.getStartTime() + " to: " + this.getEndTime() + ")");
        }
    }

    public void setAsEvent(){
        this.taskType = "E";
    }
    @Override
    public String getTaskType (Task task){
        return this.taskType;
    }

}
