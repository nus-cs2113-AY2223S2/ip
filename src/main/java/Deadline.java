/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 *
 * This is the Deadline Class that accepts description and /by ('due date')
 *
 **/
public class Deadline extends Task{

    protected String day;
    public Deadline(String description, String dateAndTime) {
        super(description);
        this.day = dateAndTime;
    }
    /**
     * Overrides the print function of the class
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + day + ")";
    }
    /**
     * Overrides the getUpdate method that is used to save/update the input.txt file
     * @return
     */
    @Override
    public String getUpdate() {
        return "deadline " + super.description + "/by " + day;
    }
}
