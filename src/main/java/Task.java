/**
 * Task class used to have a text (description) and isDone attributes
 */
public class Task {

    protected String description;
    protected boolean isDone;

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String descriptionNoSlash = (description.indexOf("/") == -1) ? description : (description.substring(0, description.indexOf("/")));
        return "[" + ((this.isDone) ? "X] " : " ] ") + descriptionNoSlash;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
