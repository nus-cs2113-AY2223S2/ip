public class Deadlines extends Task {
    protected String deadline;
    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    public String getIcon(){
        return "D";
    }
    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String newDeadline) {
        deadline = newDeadline;
    }
}
