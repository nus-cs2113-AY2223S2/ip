package duke.task;
import duke.exception.IllegalCommandException;

public class Task {
    private String description;
    private boolean isDone;

    public String getInitCommand() {
        return initCommand;
    }

    public void setInitCommand(String initCommand) {
        this.initCommand = initCommand;
    }
    public String getSaveString(){
        return initCommand + " " + ( isDone() ? "1" : "0") + System.lineSeparator();
    }

    private String initCommand;
    public Task(){
        this.isDone = false;
        this.description = "";
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone() ? "X" : " ");
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) throws IllegalCommandException {
        if(this.isDone == isDone){
            throw new IllegalCommandException("No change in done state");
        }
        this.isDone = isDone;
    }
    @Override
    public String toString(){
        return description;
    }
}
