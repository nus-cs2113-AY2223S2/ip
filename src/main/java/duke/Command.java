package duke;

public abstract class Command {

    private Boolean isExit = false;
    protected void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    public void setExit() {
        isExit = true;
    }

    public Boolean getExit() {
        return isExit;
    }
}
