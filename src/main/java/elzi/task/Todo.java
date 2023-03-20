package elzi.task;

import elzi.ElziException;

/**
 * @author : Steven A. O. Waskito
 * Class Todo that inherits Task and has attributes
 * Description, isDone
 **/
public class Todo extends Task {

    /**
     * Constructor for todo object
     * @param description todo description
     * @throws ElziException if description is empty
     */
    public Todo(String description) throws ElziException {
        super(description);
    }

    public String getStatus() {
        return ("[T][" + (isDone ? "X" : " ") + "]");
    }
    @Override
    public String toString() {
        return this.getStatus() + " " + description;
    }
    /**
     * Method encoding the given task to save
     */
    @Override
    public String encode() {
        return ("T_" + (isDone ? "1_" : "0_")
                + description);
    }
    @Override
    public String getType() {
        return "T";
    }
}
