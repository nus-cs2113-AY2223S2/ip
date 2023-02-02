public class Deadlines extends Task{
    protected String deadline;
    Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + this.getStatusAndDescription());
    }
    public String getDeadline() {
        return this.deadline;
    }
//    public void printAddedDeadline() {
//        System.out.println("\tGot it. I've added this task:\n");
//        System.out.println("\t\t" + this.getStatusAndDescription());
//    }

    @Override
    public String getStatusAndDescription() {
        return "[D]" + super.getStatusAndDescription() + "(by: " + this.getDeadline() + ")";
    }
}
