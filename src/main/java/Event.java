/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 *
 * This is the Event Class that accepts description, /from and /to, ('time range')
 *
 **/
public class Event extends Task{
    protected String startTime;
    protected String endTime;
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime  = startTime;
        this.endTime    = endTime;
    }
    /**
     * Overrides the print function of the class
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + "(from: " + startTime + " to: " + endTime + ")");
    }
    /**
     * Overrides the getUpdate method that is used to save/update the input.txt file
     * @return
     */
    @Override
    public String getUpdate() {
        return "event " + super.description + "/from " + startTime + "/to " + endTime;
    }
}
