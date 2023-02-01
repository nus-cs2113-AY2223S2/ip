public class DeadLine extends Task
{
    private String deadLineBy;
    public DeadLine (String taskName, String deadLineBy)
    {
        super (taskName);
        this.deadLineBy = deadLineBy;
    }

    @Override
    public String getTaskStatus ()
    {
        return super.getTaskStatus () + "(by: " + deadLineBy + ")";
    }
}
