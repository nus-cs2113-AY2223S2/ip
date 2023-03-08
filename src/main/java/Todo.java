public class Todo extends Task {

    /**
     * constructs a Todo item
     *
     * @param description String containing description of task added
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * formats a String to contains the task code, status and description
     *
     * @return String containing task code, status and description
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    /**
     * formats a String to be saved to the file
     *
     * @return String containing task code, status and description
     */
    @Override
    public String toSerialisedString() {
        String isDoneStr = isDone ? "1" : "0";
        return ("T|" + isDoneStr + "|" + description);
    }
}