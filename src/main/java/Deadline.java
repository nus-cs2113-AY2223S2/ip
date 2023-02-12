public class Deadline extends Task {
    String date;
    public Deadline(String description, int index, String date) {
        super(description, index, TypeOfTask.DEADLINE);
        this.date = date;
    }

    /**
     * Returns the symbol for DEADLINE task
     * @return D
     */
    @Override
    public String getStatusForTypeOfTask() {
        return "D";
    }

    /**
     * Prints out the deadline according to a format
     */

    public String formatString() {
        return (".[" + this.getStatusForTypeOfTask() + "]" +
                "[" + this.getStatusIcon() + "] " +
                this.getDescription() +
                "(by: " + this.date + ")");
    }

}
