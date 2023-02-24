public class Event extends Task{

    protected String from;
    protected String to;

    /**
    * Initializer for Event class
    *
    * @param from the day that the event starts from
    * @param to the day that the event is up to
    * @param description the name of the event
    */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
    * Method to get task icon
    *
    * @return "E" because stands for event
    */
    @Override
    public String getTaskIcon() {
        return "E";
    }

    /**
    * Method to get day that event is from
    *
    * @return from
    */
    @Override
    public String getFrom() {
        return from;
    }

    /**
    * Method to get day that event is up to
    *
    * @return to
    */
    @Override
    public String getTo() {
        return to;
    }

    /**
    * Method to print the event task
    *
    * @return the description, the day it is from and up to
    */
    @Override
    public String printTask() {
        return super.printTask() + "(from: " + from + " to: " + to + ")";
    }
    
}
