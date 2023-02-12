public class Deadline extends Task {
    String date;
    public Deadline(String description, String date) {
        super(description, TypeOfTask.DEADLINE);
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
        System.out.println(".[" + this.getStatusForTypeOfTask() + "]" +
                            "[" + this.getStatusIcon() + "] " +
                            this.getDescription() +
                            "(by: " + this.date + ")");
    }

}
