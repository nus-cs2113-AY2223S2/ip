package chronos.tasktype;
import chronos.savehandler.*;

public class Task {
    private boolean isDone;
    private String description;

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public Task(String description){
        if (description == null){
            throw new IllegalArgumentException("This field cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone(){
        return isDone;
    }

    public char setCheckMark() {
        char icon = '□';
        if (this.isDone == true) {
            icon = '√';
        }
        return icon;
    }

    public void toggleDone(){
        this.isDone = !this.isDone;
    }

    @Override
    public String toString(){
        return String.format("%c %s", setCheckMark(), getDescription());
    }

    public Save toSave(String taskType) {
        return new Save(taskType, isDone, description, "", "", "");
    }
}
