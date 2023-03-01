public class Deadline extends Task{

    protected String day;

    /**
    * Initializer for deadline class
    *
    * @param day the day that the deadline is due
    * @param description the name of the deadline
    */
    public Deadline(String description, String day) {
        super(description);
        this.day = day;
    }

    /**
    * Method to get task icon
    *
    * @return "D" because stands for deadline
    */
    @Override
    public String getTaskIcon() {
        return "D";
    }

    /**
    * Method to get day that deadline is by
    *
    * @return day, day that deadline is by
    */
    @Override
    public String getBy() {
        return day;
    }

    /**
    * Method to print the deadline task
    *
    * @return the description and the day it is due
    */
    @Override
    public String printTask() {
        return super.printTask() + "(by: " + day + ")";
    }

}
