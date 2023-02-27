
package DukeFunctions;

import Exceptions.DukeError;
import Exceptions.MissingInputException;

/**
 * Represents a deadline task with a description and a deadline.
 * Inherits from the {@link Todo} class.
 */
public class Deadline extends Todo {
    protected String by;
    String deliverable;

    /**
     * Constructs a new Deadline object with the given input contents.
     *
     * @param inputContents the input contents of the deadline task, which includes a description and a deadline
     * @throws MissingInputException if there are insufficient inputs
     */
    public Deadline(String inputContents) throws DukeError {
        super(inputContents);
        String[] parts = inputContents.split("/by");


        if (parts.length > 1) {
            String deliverable = parts[0];
            this.by = parts[1].trim();
        } else {
            throw new DukeError("Missing inputs. Syntax for deadline: deadline <description> /by <time> ");
        }
        //this.by = (parts.length > 1) ? parts[1].trim() : throw new MissingInputException();
        this.deliverable = deliverable;
        this.type = "D";

    }

    /**
     * Sets the deadline of the deadline task.
     *
     * @param by the deadline of the deadline task
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Gets the deadline of the deadline task.
     *
     * @return string the deadline of the deadline task
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return string string in the format "description (by: deadline)"
     */
    @Override
    public String toString() {
        return deliverable + " (by: " + by + ")";
    }
}
