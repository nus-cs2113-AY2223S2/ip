package task;

public class Todo extends Task {

    /**
     * A constructor that accepts the description specified by the user.
     *
     * @param description The description of the deadline.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Set the toString parameter to print out a readable string.
     *
     * @return The readable string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the string into a savable string.
     *
     * @return A string that can be saved into a file.
     */
    @Override
    public String savableString() {
        return "T|" + super.savableString();
    }


}
