/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 *
 * This is the Todo Class that accepts description
 *
 **/
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    /**
     * Overrides the print function of the class
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    /**
     * Overrides the getUpdate method that is used to save/update the input.txt file
     * @return
     */
    @Override
    public String getUpdate() {
        return "todo " + super.getUpdate();
    }
}
