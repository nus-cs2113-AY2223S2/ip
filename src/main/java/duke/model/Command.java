package duke.model;

/**
 * A class to represent the command type and the payload of the command
 */
public class Command {

    /**
     * Represents the type of the command
     */
    protected String type;
    /**
     * Represents the payload of the command
     */
    protected Payload payload;

    /**
     * Method to return the type of the command
     *
     * @return The type of the command
     */
    public String getType() {
        return this.type;
    }

    /**
     * Method to set the type of the command
     *
     * @param type The type of the command
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method to get the payload of the command
     *
     * @return The Payload object corresponding to the command object
     */
    public Payload getPayload() {
        return this.payload;
    }

    /**
     * Method to set the payload of the command
     *
     * @param payload The payload of the command
     */
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

}
