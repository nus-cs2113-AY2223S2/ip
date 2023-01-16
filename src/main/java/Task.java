public class Task {
    protected String description;
    protected boolean done;

    public Task(String descriptionInput)
    {
        this.description = descriptionInput;
        this.done = false;
    }

    public String getStatusIcon()
    {
        return (done ? "X":" ");
    }

}
