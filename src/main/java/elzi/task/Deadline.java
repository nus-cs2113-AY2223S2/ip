package elzi.task;

import elzi.ElziException;

/**
 * @author : Steven A. O. Waskito
 * Class deadline that inherits task and has attributes
 * Description, isDone, and By
 **/
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for deadline object with attribute: by
     * @param description deadline description
     * @param by deadline by attribute
     * @throws ElziException if by is blank
     */
    public Deadline(String description, String by) throws ElziException {
        super(description);
        if (by.isBlank()) {
            throw new ElziException("Missing /by in deadline");
        }
        this.by = by;
    }

    public String getStatus() {
        return ("[D][" + (isDone ? "X" : " ") + "]");
    }
    public String getBy() {
        return ("by: " + by);
    }
    @Override
    public String toString() {
        return this.getStatus() + " " + description + " (" + getBy() + ")";
    }
    /**
     * Method encoding the given task to save
     */
    @Override
    public String encode() {
        return ("D_" + (isDone ? "1_" : "0_")
                + description + "_"
                + by);
    }
    @Override
    public String getType() {
        return "D";
    }
}
