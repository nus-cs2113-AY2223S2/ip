public class Deadlines extends Task {
    protected String deadline;
    public Deadlines(String description, String newDeadline) {
        super(description);
        deadline = newDeadline;
    }
    public String getIcon() {
        return StrIntLib.deadlineIcon;
    }
    public String getDeadline() {
        return deadline;
    }

}
