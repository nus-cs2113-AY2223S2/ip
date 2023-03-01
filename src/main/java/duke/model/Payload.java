package duke.model;

/**
 * A class to represent the payload or argument of a Command object
 */
public class Payload {

    /**
     * Represent the data of the payload
     */
    protected String[] data;

    /**
     * Constructor that indicates an empty payload
     */
    public Payload() {
        this.data = new String[0];
    }

    /**
     * Constructor that initializes the data of the payload
     *
     * @param data
     */
    public Payload(String[] data) {
        this.data = data;
    }

    /**
     * Method to return the data of the payload
     *
     * @return Array of string that represents multiple tokenized String
     */
    public String[] getData() {
        return data;
    }

}
