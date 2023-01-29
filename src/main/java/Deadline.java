public class Deadline extends Task {
    String date;
    public Deadline(String description, int index, String date) {
        super(description, index, TypeOfTask.DEADLINE);
        this.date = date;
    }

    @Override
    public void printTask() {
        System.out.println((this.index + 1) +
                            ".[" + this.getStatusForTypeOfTask() + "]" +
                            "[" + this.getStatusIcon() + "] " +
                            this.getDescription() +
                            "(by: " + this.date + ")");
    }

}
