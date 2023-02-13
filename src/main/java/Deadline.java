public class Deadline extends Task {
    String date;
    public Deadline(boolean isDone, String description, String date) {
        super(TypeOfTask.DEADLINE,isDone, description);
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
    @Override
    public void printTask() {
        System.out.println(formatString());
    }

    public String formatString() {
        return (".[" + this.getStatusForTypeOfTask() + "]" +
                "[" + this.getStatusIcon() + "] " +
                this.getDescription() +
                "(by: " + this.date + ")");
    }

    public String getDate() {
        return this.date;
    }

}
